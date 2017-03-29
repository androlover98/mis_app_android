package com.android.mis.javac.ViewDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mis.R;
import com.android.mis.controllers.ViewDetails.EducationDetailsAdapter;
import com.android.mis.models.ViewDetails.Education;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EducationDetailsFragment extends Fragment {

    private JSONObject json;
    private RecyclerView recyclerView;
    private ArrayList<Education> educationDetailsArrayList;

    public EducationDetailsFragment(JSONObject json) throws JSONException {
        // Required empty public constructor
        this.json = json;
        educationDetailsArrayList = new ArrayList<>();
        JSONArray educationDetails = json.getJSONArray("education");
        for (int i=0;i<educationDetails.length();i++)
        {
            JSONObject education = educationDetails.getJSONObject(i);
            educationDetailsArrayList.add(new Education(education.getString("exam"),education.getString("specialization"),education.getString("institute"),education.getString("year"),education.getString("grade"),education.getString("division"),i));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.education_details_fragment, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        EducationDetailsAdapter mAdapter = new EducationDetailsAdapter(educationDetailsArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }
}
