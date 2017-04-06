package com.android.mis.javac.Attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.mis.R;
import com.android.mis.controllers.Attendance.ViewAttendanceAdapter;
import com.android.mis.controllers.ViewDetails.StayDetailsAdapter;
import com.android.mis.javac.Login.LoginActivity;
import com.android.mis.models.Attendance.SubjectAttendanceItem;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewAttendance extends AppCompatActivity implements Callback{
    private RecyclerView recyclerView;
    private ArrayList<SubjectAttendanceItem> subjectAttendanceItemsList;
    private View mProgressView;
    private View mErrorView;
    private Button refreshOnError;
    private Bundle extras;
    private HashMap<String,String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extras = getIntent().getExtras();
        params = new HashMap<>();
        subjectAttendanceItemsList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mProgressView = findViewById(R.id.loader);
        mErrorView = findViewById(R.id.err);

        fetchDetails();
        refreshOnError = (Button)mErrorView.findViewById(R.id.refresh_button);
        refreshOnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDetails();
            }
        });
    }

    private void fetchDetails()
    {
        SessionManagement session = new SessionManagement(getApplicationContext());
        if(session.isLoggedIn())
        {
            params = session.getSessionDetails();
        }
        if(extras!=null)
        {
            params.put("session",extras.getString("session"));
            params.put("sessionyear",extras.getString("session_year"));
            params.put("semester",extras.getString("semester"));
        }

        NetworkRequest nr = new NetworkRequest(ViewAttendance.this,mProgressView,mErrorView,this,"get", Urls.server_protocol,Urls.view_attendance_url,params,false,true,0);
        nr.setSnackbar_message(Urls.error_connection_message);
        nr.initiateRequest();
    }

    @Override
    public void performAction(String result, int tag) {
        try {
            JSONObject json = new JSONObject(result);
            if (json.getBoolean("success") == true) {
                JSONObject attendance = json.getJSONObject("attendance");
                int map_id = attendance.getInt("map_id");
                JSONArray subject_array = attendance.getJSONArray("subjects");
                for(int i=0;i<subject_array.length();i++)
                {
                    JSONObject subject_attendance = subject_array.getJSONObject(i);
                  //  Toast.makeText(getApplicationContext(),subject_attendance.getString("subject_id"),Toast.LENGTH_LONG).show();
                    subjectAttendanceItemsList.add(new SubjectAttendanceItem(subject_attendance.getString("subject_id"),subject_attendance.getString("name"),subject_attendance.getString("id"),subject_attendance.getInt("total_absent"),subject_attendance.getInt("total_class"),i,map_id));
                }

                ViewAttendanceAdapter mAdapter = new ViewAttendanceAdapter(subjectAttendanceItemsList,getApplicationContext(),ViewAttendance.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            } else {
                Util.viewSnackbar(findViewById(android.R.id.content), json.getString("err_msg"));
            }
        } catch (Exception e) {
            Log.d("Result",result);
            Log.e("Exception", e.toString());
       //     Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            Util.viewSnackbar(findViewById(android.R.id.content), Urls.parsing_error_message);
        }
    }
}
