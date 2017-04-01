package com.android.mis.javac.CourseStructure;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mis.R;
import com.android.mis.controllers.ViewPagerAdapter;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ViewCourse extends AppCompatActivity implements Callback {

    private ViewPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private ArrayList<String> tabTitle;
    private View mProgressView;
    private View mErrorView;
    private Button refreshOnError;
    private String start_sem,end_sem;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        extras = getIntent().getExtras();
        if(extras != null){
            start_sem = extras.getString("start_sem");
            end_sem = extras.getString("end_sem");
        }

        mProgressView = findViewById(R.id.loader);
        mErrorView = findViewById(R.id.err);
        refreshOnError = (Button)mErrorView.findViewById(R.id.refresh_button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager = (ViewPager) findViewById(R.id.container);

        //Initializing the tablayout
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        tabTitle = new ArrayList<>();


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position,false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        refreshOnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDetails();
            }
        });

        fetchDetails();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, start_sem+" "+end_sem, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager, ArrayList<String> list, JSONObject json) throws JSONException {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for(int i=0;i<list.size();i++)
        {
            tabTitle.add(list.get(i));
            switch (list.get(i)){
                default:
                    adapter.addFrag(new ViewCourseFragment(json,list.get(i)),list.get(i));
            }
        }
        viewPager.setAdapter(adapter);

        try
        {
            setupTabIcons();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void fetchDetails(){
        HashMap<String,String> params = new HashMap<>();
        SessionManagement session = new SessionManagement(getApplicationContext());
        if(session.isLoggedIn())
        {
            params = session.getSessionDetails();
        }
        params.put("session",extras.getString("session"));
        params.put("semester",extras.getString("semester"));
        params.put("branch_id",extras.getString("branch_id"));
        params.put("course",extras.getString("course"));
        tabLayout.setVisibility(View.GONE);
        NetworkRequest nr = new NetworkRequest(ViewCourse.this,mProgressView,mErrorView,this,"get", Urls.server_protocol,Urls.view_course_url,params,false,true,0);
        nr.setSnackbar_message(Urls.error_connection_message);
        nr.initiateRequest();
    }

    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.custom_tab,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
        String parts[] = tabTitle.get(pos).split("_");
        if(parts.length > 1)
            tv_title.setText("Semester : "+parts[0]+'('+"group"+parts[1]+')');
        else
        tv_title.setText("Semester : "+tabTitle.get(pos));
        tv_count.setVisibility(View.GONE);
        return view;
    }

    private void setupTabIcons()
    {
        for(int i=0;i<tabTitle.size();i++)
        {
            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void performAction(String result, int tag) {
        try{
            JSONObject json = new JSONObject(result);
            if(json.getBoolean("success") == true)
            {
                tabLayout.setVisibility(View.VISIBLE);
                JSONObject details = json.getJSONObject("course_details");
                JSONObject course = details.getJSONObject("course");
                JSONObject subjects = course.getJSONObject("subjects");
                JSONObject subjects_details = subjects.getJSONObject("group_details");
                Iterator keysToCopyIterator = subjects_details.keys();
                ArrayList<String> keysList = new ArrayList<String>();
                while(keysToCopyIterator.hasNext()) {
                    String key = (String) keysToCopyIterator.next();
                    keysList.add(key.toUpperCase());
                }
                setupViewPager(mViewPager,keysList,details);
            }
            else{
                Util.viewSnackbar(findViewById(android.R.id.content),json.getString("err_msg"));
            }
        }catch (Exception e){
            Log.e("Exception",e.toString());
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            Util.viewSnackbar(findViewById(android.R.id.content),Urls.parsing_error_message);
        }
    }
}
