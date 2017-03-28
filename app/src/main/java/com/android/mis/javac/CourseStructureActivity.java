package com.android.mis.javac;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.mis.R;
import com.android.mis.controllers.CoursesAdapter;
import com.android.mis.controllers.SpinnerAdapter;
import com.android.mis.controllers.StayDetailsAdapter;
import com.android.mis.javac.ViewDetails.ViewDetails;
import com.android.mis.models.Course;
import com.android.mis.models.Department;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseStructureActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,Callback {

    private Spinner spinner;
    private ArrayList<Department> departments;
    private View mProgressView;
    private View mErrorView;
    private View mCourseStructure;
    private Button refreshOnError;
    private View spinnerLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_structure);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mProgressView = findViewById(R.id.loader);
        mErrorView = findViewById(R.id.err);
        mCourseStructure = findViewById(R.id.content_course_structure);
        refreshOnError = (Button)mErrorView.findViewById(R.id.refresh_button);

        refreshOnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDepartmentDetails();
            }
        });

        spinner = (Spinner)findViewById(R.id.department_spinner);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        spinnerLayout = (View)findViewById(R.id.spinner_layout);

        departments = new ArrayList<>();
        departments.add(new Department("Select Department","dept_id","dept_type"));

        fetchDepartmentDetails();
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fetchDepartmentDetails(){
        HashMap<String,String> params = new HashMap<>();
        SessionManagement session = new SessionManagement(getApplicationContext());
        if(session.isLoggedIn())
        {
            params = session.getSessionDetails();
        }
        spinnerLayout.setVisibility(View.GONE);
        spinner.setVisibility(View.GONE);
        NetworkRequest nr = new NetworkRequest(CourseStructureActivity.this,mProgressView,mErrorView,this,"get", Urls.server_protocol,Urls.departments_url,params,false,true,0);
        nr.setSnackbar_message(Urls.error_connection_message);
        nr.initiateRequest();
    }

    private void fetchCoursesOfferedByDepartment(String dept_id)
    {
        HashMap<String,String> params = new HashMap<>();
        SessionManagement session = new SessionManagement(getApplicationContext());
        if(session.isLoggedIn())
        {
            params = session.getSessionDetails();
        }

        params.put("dept_id",dept_id);
        NetworkRequest nr = new NetworkRequest(CourseStructureActivity.this,mProgressView,mErrorView,this,"get", Urls.server_protocol,Urls.courses_url,params,false,false,1);
        nr.setSnackbar_message(Urls.error_connection_message);
        nr.initiateRequest();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(departments.get(position).getDeptName().contentEquals("Select Department"))
        {
            return;
        }
        else{
            fetchCoursesOfferedByDepartment(departments.get(position).getDeptId());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void performAction(String result, int tag) {
        switch (tag)
        {
            case 0 :
                try{
                    JSONObject json = new JSONObject(result);
                    if(json.getBoolean("success") == true)
                    {
                        spinnerLayout.setVisibility(View.VISIBLE);
                        spinner.setVisibility(View.VISIBLE);
                        JSONArray departments_array = json.getJSONArray("departments");
                        for(int i=0;i<departments_array.length();i++)
                        {
                            JSONObject dept = departments_array.getJSONObject(i);
                            departments.add(new Department(dept.getString("name"),dept.getString("id"),dept.getString("type")));
                        }
                        spinner.setOnItemSelectedListener(this);
                        SpinnerAdapter dataAdapter = new SpinnerAdapter(this,R.layout.spinner_department_item,departments);
                        spinner.setAdapter(dataAdapter);
                    }
                    else{
                        Util.viewSnackbar(findViewById(android.R.id.content),json.getString("err_msg"));
                    }
                }catch (Exception e){
                    Log.e("Exception",e.toString());
                    Util.viewSnackbar(findViewById(android.R.id.content),Urls.parsing_error_message);
                }
                break;
            case 1:
                try{
                    ArrayList courses;
                    courses = new ArrayList<>();

                    JSONObject json = new JSONObject(result);
                    if(json.getBoolean("success") == true)
                    {
                        JSONArray course_array = json.getJSONArray("course_details");
                        for(int i=0;i<course_array.length();i++)
                        {
                            JSONObject course = course_array.getJSONObject(i);
                            ArrayList<String> semList = new ArrayList<>();
                            semList.add("All");
                            JSONArray semArray = course.getJSONArray("sem_list");
                            for(int j=0;j<semArray.length();j++)
                            {
                                semList.add(String.valueOf(semArray.getInt(j)));
                            }
                            courses.add(new Course(course.getString("id"),course.getString("dept_name"),course.getString("duration"),course.getString("course_id"),course.getString("branch_id"),course.getString("branch_name"),course.getString("start"),course.getString("end"),course.getString("course_name"),semList,i));
                        }
                        CoursesAdapter mAdapter = new CoursesAdapter(courses,getApplicationContext(),CourseStructureActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);
                    }
                    else{
                        Util.viewSnackbar(findViewById(android.R.id.content),json.getString("err_msg"));
                    }
                }catch (Exception e){
                    Log.e("Exception",e.toString());
                    Util.viewSnackbar(findViewById(android.R.id.content),e.toString());
                }
                break;
        }
    }
}
