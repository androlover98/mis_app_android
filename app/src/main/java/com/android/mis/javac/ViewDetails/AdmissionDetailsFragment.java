package com.android.mis.javac.ViewDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mis.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdmissionDetailsFragment extends Fragment {

    private JSONObject admissionDetails;

    public AdmissionDetailsFragment() {
        // Required empty public constructor
    }

    public AdmissionDetailsFragment(JSONObject admissionDetails)
    {
        this.admissionDetails = admissionDetails;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_admission_details, container, false);

        try {
            TextView mc = (TextView)rootView.findViewById(R.id.mc);
            mc.setText(admissionDetails.getString("migration_certificate"));

            TextView doa = (TextView)rootView.findViewById(R.id.doa);
            doa.setText(admissionDetails.getString("date_of_admission"));

            TextView iit_jee_rank = (TextView)rootView.findViewById(R.id.ijgr);
            iit_jee_rank.setText(admissionDetails.getString("iit_jee_rank"));

            TextView gs = (TextView)rootView.findViewById(R.id.gs);
            gs.setText(admissionDetails.getString("gate_score"));

            TextView rn = (TextView)rootView.findViewById(R.id.rn);
            rn.setText(admissionDetails.getString("roll_no"));

            TextView abo = (TextView)rootView.findViewById(R.id.abo);
            abo.setText(admissionDetails.getString("admn_based_on"));

            TextView ijcr = (TextView)rootView.findViewById(R.id.ijcr);
            ijcr.setText(admissionDetails.getString("category_rank"));

            TextView cs = (TextView)rootView.findViewById(R.id.cs);
            cs.setText(admissionDetails.getString("cat_score"));

            TextView ps = (TextView)rootView.findViewById(R.id.ps);
            ps.setText(admissionDetails.getString("current_semester"));

            TextView dept = (TextView)rootView.findViewById(R.id.dept);
            dept.setText(admissionDetails.getString("department"));

            TextView course = (TextView)rootView.findViewById(R.id.course);
            course.setText(admissionDetails.getString("course"));

            TextView branch = (TextView)rootView.findViewById(R.id.branch);
            branch.setText(admissionDetails.getString("branch"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

}
