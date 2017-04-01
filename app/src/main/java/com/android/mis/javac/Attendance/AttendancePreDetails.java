package com.android.mis.javac.Attendance;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.mis.R;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AttendancePreDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Callback {
    private Spinner session_spinner, session_year_spinner, semester_spinner;
    private Button submit_button;
    private ArrayList<String> session_year_list, session_list, semester_list;
    private View mProgressView;
    private View mErrorView;
    private Button refreshOnError;
    private CardView card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_pre_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mProgressView = findViewById(R.id.loader);
        mErrorView = findViewById(R.id.err);

        refreshOnError = (Button) mErrorView.findViewById(R.id.refresh_button);
        refreshOnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDetails(0);
            }
        });
        card = (CardView) findViewById(R.id.card_view);


        session_spinner = (Spinner) findViewById(R.id.session_spinner);
        session_year_spinner = (Spinner) findViewById(R.id.session_year_spinner);
        semester_spinner = (Spinner) findViewById(R.id.semester_spinner);

        submit_button = (Button) findViewById(R.id.submit_button);
        session_year_list = new ArrayList<>();
        session_list = new ArrayList<>();
        semester_list = new ArrayList<>();

        session_list.add("Select");
        session_list.add("Monsoon");
        session_list.add("Winter");
        session_list.add("Summer");

        fetchDetails(0);


        session_spinner.setOnItemSelectedListener(this);ArrayAdapter<String> sessionAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, session_list);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, semester_list);

        session_spinner.setAdapter(sessionAdapter);
        semester_spinner.setAdapter(semesterAdapter);


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNotSelected(session_spinner) || isNotSelected(session_year_spinner) || isNotSelected(semester_spinner)) {
                    Util.viewSnackbar(findViewById(android.R.id.content), Urls.empty_message);
                } else {
                    Bundle extras = new Bundle();
                    extras.putString("session_year", session_year_spinner.getSelectedItem().toString());
                    extras.putString("session", session_spinner.getSelectedItem().toString());
                    extras.putString("semester", semester_spinner.getSelectedItem().toString());
                    Util.moveToActivity(AttendancePreDetails.this, ViewAttendance.class, extras);
                }
            }
        });
    }

    private boolean isNotSelected(Spinner spinner) {
        if (spinner.getSelectedItem().toString().toLowerCase().contentEquals("select"))
            return true;
        return false;
    }

    private void fetchDetails(int tag) {
        switch (tag) {
            case 0:
                card.setVisibility(View.GONE);
                session_year_list.clear();
                session_year_list.add("Select");
                HashMap params = new HashMap();
                SessionManagement session = new SessionManagement(getApplicationContext());
                if (session.isLoggedIn()) {
                    params = session.getSessionDetails();
                }
                NetworkRequest nr = new NetworkRequest(AttendancePreDetails.this, mProgressView, mErrorView, this, "get", Urls.server_protocol, Urls.session_year_url, params, false, true, 0);
                nr.setSnackbar_message(Urls.error_connection_message);
                nr.initiateRequest();
                break;
            case 1:
                semester_list.clear();
                semester_list.add("Select");

                HashMap param = new HashMap();
                SessionManagement sessin = new SessionManagement(getApplicationContext());
                if (sessin.isLoggedIn()) {
                    param = sessin.getSessionDetails();
                }
                param.put("sessionyear", session_year_spinner.getSelectedItem().toString());
                param.put("session", session_spinner.getSelectedItem().toString());
                NetworkRequest netr = new NetworkRequest(AttendancePreDetails.this, mProgressView, mErrorView, this, "get", Urls.server_protocol, Urls.semester_url, param, false, false, 1);
                netr.setSnackbar_message(Urls.error_connection_message);
                netr.initiateRequest();
                break;
        }
    }

    @Override
    public void performAction(String result, int tag) {
        switch (tag) {
            case 0:
                try {
                    JSONObject json = new JSONObject(result);
                    if (json.getBoolean("success") == true) {
                        card.setVisibility(View.VISIBLE);
                        JSONArray details = json.getJSONArray("session_year");
                        for (int i = 0; i < details.length(); i++) {
                            JSONObject sy = details.getJSONObject(i);
                            session_year_list.add(sy.getString("session_year"));
                        }
                        session_year_spinner.setOnItemSelectedListener(this);
                        ArrayAdapter<String> sessionYearAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, session_year_list);
                        session_year_spinner.setAdapter(sessionYearAdapter);
                    } else {
                        Util.viewSnackbar(findViewById(android.R.id.content), json.getString("err_msg"));
                    }
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    Util.viewSnackbar(findViewById(android.R.id.content), Urls.parsing_error_message);
                }
                break;


            case 1:

                try {
                    JSONObject json = new JSONObject(result);
                    if (json.getBoolean("success") == true) {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        JSONArray details = json.getJSONArray("semester");
                        for (int i = 0; i < details.length(); i++) {
                            JSONObject sy = details.getJSONObject(i);
                            semester_list.add(sy.getString("semster"));
                        }
                        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, semester_list);
                        semester_spinner.setAdapter(semesterAdapter);
                    } else {
                        Util.viewSnackbar(findViewById(android.R.id.content), json.getString("err_msg"));
                    }
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    Util.viewSnackbar(findViewById(android.R.id.content), Urls.parsing_error_message);
                }

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        switch (spinner.getId()) {
            case R.id.session_year_spinner:
                if (session_year_list.get(position).toLowerCase().contentEquals("select"))
                    return;
                else {
                    fetchDetails(1);
                }
                break;

            case R.id.session_spinner:
                if (session_list.get(position).toLowerCase().contentEquals("select"))
                    return;
                else {
                    fetchDetails(1);
                }
                break;

            case R.id.semester_spinner:
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
