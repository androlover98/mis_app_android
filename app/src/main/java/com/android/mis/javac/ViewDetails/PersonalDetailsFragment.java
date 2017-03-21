package com.android.mis.javac.ViewDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mis.R;
import com.android.mis.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalDetailsFragment extends Fragment {


    private JSONObject personalDetails ;
    private String auth;

    public PersonalDetailsFragment() {
        // Required empty public constructor
    }

    public PersonalDetailsFragment(JSONObject details,String auth){
        this.personalDetails = details;
        this.auth = auth;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        try {
            if(auth.contentEquals("emp"))
            {
                rootView = inflater.inflate(R.layout.emp_personal_details, container, false);
                TextView name = (TextView)rootView.findViewById(R.id.name);
                name.setText(personalDetails.getString("salutation")+" "+personalDetails.getString("first_name")+" "+personalDetails.getString("middle_name")+" "+personalDetails.getString("last_name"));

                TextView gender = (TextView)rootView.findViewById(R.id.gender);
                if(personalDetails.getString("sex").contentEquals("m"))
                    gender.setText("Male");
                else
                    gender.setText("Female");

                TextView cat = (TextView)rootView.findViewById(R.id.cat);
                cat.setText(personalDetails.getString("category"));

                TextView dob = (TextView)rootView.findViewById(R.id.dob);
                dob.setText(personalDetails.getString("dob"));

                TextView email = (TextView)rootView.findViewById(R.id.email);
                email.setText(personalDetails.getString("email"));

                TextView ms = (TextView)rootView.findViewById(R.id.ms);
                ms.setText(personalDetails.getString("marital_status"));

                TextView pc = (TextView)rootView.findViewById(R.id.pc);
                pc.setText(personalDetails.getString("physically_challenged"));

                TextView dept = (TextView)rootView.findViewById(R.id.dept);
                dept.setText(personalDetails.getString("dept_id"));

                TextView religion = (TextView)rootView.findViewById(R.id.religion);
                religion.setText(personalDetails.getString("religion"));

                TextView ki = (TextView)rootView.findViewById(R.id.ki);
                ki.setText(personalDetails.getString("kashmiri_immigrant"));

                TextView nationality = (TextView)rootView.findViewById(R.id.nationality);
                nationality.setText(personalDetails.getString("nationality"));

                TextView pob = (TextView)rootView.findViewById(R.id.pob);
                pob.setText(personalDetails.getString("birth_place"));

                TextView mob_no = (TextView)rootView.findViewById(R.id.mobile_no);
                mob_no.setText(personalDetails.getString("mobile_no"));

                TextView father_name = (TextView)rootView.findViewById(R.id.father_name);
                father_name.setText(personalDetails.getString("father_name"));

                TextView mother_name = (TextView)rootView.findViewById(R.id.mother_name);
                mother_name.setText(personalDetails.getString("mother_name"));

                TextView bank_name = (TextView)rootView.findViewById(R.id.bank_name);
                bank_name.setText(personalDetails.getString("bank_name"));

                TextView bank_accno = (TextView)rootView.findViewById(R.id.bank_acc_no);
                bank_accno.setText(personalDetails.getString("bank_accno"));

                return rootView;
            }else{
                rootView = inflater.inflate(R.layout.stu_personal_details, container, false);
                TextView name = (TextView)rootView.findViewById(R.id.name);
                name.setText(personalDetails.getString("name"));

                TextView gender = (TextView)rootView.findViewById(R.id.gender);
                if(personalDetails.getString("gender").contentEquals("m"))
                    gender.setText("Male");
                else
                    gender.setText("Female");

                TextView pob = (TextView)rootView.findViewById(R.id.pob);
                pob.setText(personalDetails.getString("birth_place"));

                TextView blood_group = (TextView)rootView.findViewById(R.id.bg);
                blood_group.setText(personalDetails.getString("blood_group"));

                TextView marital_status = (TextView)rootView.findViewById(R.id.ms);
                marital_status.setText(personalDetails.getString("marital_status"));

                TextView religion = (TextView)rootView.findViewById(R.id.religion);
                religion.setText(personalDetails.getString("religion"));

                TextView aadhar_card_no = (TextView)rootView.findViewById(R.id.acn);
                aadhar_card_no.setText(personalDetails.getString("aadhar_card"));

                TextView name_in_hindi = (TextView)rootView.findViewById(R.id.name_in_hindi);
                name_in_hindi.setText(personalDetails.getString("name_in_hindi"));

                TextView dob = (TextView)rootView.findViewById(R.id.dob);
                dob.setText(personalDetails.getString("dob"));

                TextView pc = (TextView)rootView.findViewById(R.id.pc);
                pc.setText(personalDetails.getString("physically_challenged"));

                TextView ki = (TextView)rootView.findViewById(R.id.ki);
                ki.setText(personalDetails.getString("kashmiri_immigrant"));

                TextView category = (TextView)rootView.findViewById(R.id.cat);
                category.setText(personalDetails.getString("category"));

                TextView nationality = (TextView)rootView.findViewById(R.id.nationality);
                nationality.setText(personalDetails.getString("nationality"));

                TextView im = (TextView)rootView.findViewById(R.id.im);
                im.setText(personalDetails.getString("identification_mark"));

                return rootView;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
