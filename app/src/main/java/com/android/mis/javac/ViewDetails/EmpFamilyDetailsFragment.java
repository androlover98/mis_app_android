package com.android.mis.javac.ViewDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.controllers.FamilyMemberDetailsAdapter;
import com.android.mis.models.FamilyMember;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EmpFamilyDetailsFragment extends Fragment {

    private JSONObject json;
    private ArrayList<FamilyMember> familyMemberArrayList;
    private RecyclerView recyclerView;

    public EmpFamilyDetailsFragment(JSONObject json) throws JSONException {
        // Required empty public constructor
        this.json = json;
        familyMemberArrayList = new ArrayList<>();
        JSONArray familyMembers = json.getJSONArray("family");

        for(int i=0;i<familyMembers.length();i++)
        {
            JSONObject member = familyMembers.getJSONObject(i);
            familyMemberArrayList.add(new FamilyMember(member.getString("name"),member.getString("relationship"),member.getString("dob"),member.getString("profession"),member.getString("present_post_addr"),member.getString("active_inactive"),member.getString("photopath"),i));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.emp_family_details, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        FamilyMemberDetailsAdapter mAdapter = new FamilyMemberDetailsAdapter(familyMemberArrayList,getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

}
