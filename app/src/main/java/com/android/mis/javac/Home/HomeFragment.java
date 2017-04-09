package com.android.mis.javac.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.controllers.ViewPagerAdapter;
import com.android.mis.javac.ViewDetails.ViewDetails;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class HomeFragment extends Fragment implements Callback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private View mProgressView;
    private View mErrorView;
    private Button refreshOnError;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    String[] tabTitle={"NOTICES","CIRCULARS","MEETINGS"};
    int[] unreadCount={0,5,0};


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //Initializing viewPager
        viewPager = (ViewPager)rootView.findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);

        //Initializing the tablayout
        tabLayout = (TabLayout)rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        mProgressView = rootView.findViewById(R.id.loader);
        mErrorView = rootView.findViewById(R.id.err);
        refreshOnError = (Button)mErrorView.findViewById(R.id.refresh_button);

        refreshOnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDetails();
            }
        });

        fetchDetails();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position,false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;

    }

    private void fetchDetails(){
        HashMap<String,String> params = new HashMap<>();
        SessionManagement session = new SessionManagement(getActivity().getApplicationContext());
        if(session.isLoggedIn())
        {
            params = session.getSessionDetails();
        }
        tabLayout.setVisibility(View.GONE);
        NetworkRequest nr = new NetworkRequest(getActivity(),mProgressView,mErrorView,this,"get", Urls.server_protocol,Urls.post_details_url,params,false,true,0);
        nr.setSnackbar_message(Urls.error_connection_message);
        nr.initiateRequest();
    }

    private void setupViewPager(ViewPager viewPager,JSONObject json) throws JSONException {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new HomeTabsFragment("notice",json.getJSONArray("notices")), "NOTICES");
        adapter.addFrag(new HomeTabsFragment("circular",json.getJSONArray("circulars")), "CIRCULARS");
        adapter.addFrag(new HomeTabsFragment("meeting",json.getJSONArray("minutes")), "MEETINGS");
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


    private View prepareTabView(int pos) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_tab,null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_title.setText(tabTitle[pos]);
        if(unreadCount[pos]>0)
        {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(""+unreadCount[pos]);
        }
        else
            tv_count.setVisibility(View.GONE);


        return view;
    }

    private void setupTabIcons()
    {

        for(int i=0;i<tabTitle.length;i++)
        {
            tabLayout.getTabAt(i).setCustomView(prepareTabView(i));
        }
    }

    @Override
    public void performAction(String result, int tag) {
        try{
            JSONObject json = new JSONObject(result);
            if(json.getBoolean("success") == true)
            {
                tabLayout.setVisibility(View.VISIBLE);
                unreadCount[0] = json.getInt("new_notice_count");
                unreadCount[1] = json.getInt("new_circular_count");
                unreadCount[2] = json.getInt("new_minutes_count");
                setupViewPager(viewPager,json);
            }
            else{
                Util.viewSnackbar(getActivity().findViewById(android.R.id.content),json.getString("err_msg"));
            }
        }catch (Exception e){
            Log.e("Exception",e.toString());
            Util.viewSnackbar(getActivity().findViewById(android.R.id.content),Urls.parsing_error_message);
        }
    }
}
