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
public class StuFamilyDetailsFragment extends Fragment {

    JSONObject familyDetails;

    public StuFamilyDetailsFragment(JSONObject familyDetails) {
        // Required empty public constructor
        this.familyDetails = familyDetails;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stu_family_details, container, false);

        TextView f_name = (TextView)rootView.findViewById(R.id.f_name);
        TextView f_occupation = (TextView)rootView.findViewById(R.id.f_occupation);
        TextView fgi = (TextView)rootView.findViewById(R.id.fgi);
        TextView fmo = (TextView)rootView.findViewById(R.id.pmn);
        TextView m_name = (TextView)rootView.findViewById(R.id.m_name);
        TextView m_occupation = (TextView)rootView.findViewById(R.id.m_occupation);
        TextView mgi = (TextView)rootView.findViewById(R.id.mgi);
        TextView pln = (TextView)rootView.findViewById(R.id.pln);

        try {
            f_name.setText(familyDetails.getString("father_name"));
            f_occupation.setText(familyDetails.getString("father_occupation"));
            fgi.setText(familyDetails.getString("father_gross_income"));
            fmo.setText(familyDetails.getString("parent_mobile_no"));
            m_name.setText(familyDetails.getString("mother_name"));
            m_occupation.setText(familyDetails.getString("mother_occupation"));
            mgi.setText(familyDetails.getString("mother_gross_income"));
            pln.setText(familyDetails.getString("parent_landline"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

}
