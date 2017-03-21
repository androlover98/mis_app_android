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
import com.android.mis.controllers.StayDetailsAdapter;
import com.android.mis.models.StayDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StayDetailsFragment extends Fragment {

    private JSONObject json;
    private RecyclerView recyclerView;
    private ArrayList<StayDetails> stayDetailsArrayList;

    public StayDetailsFragment(JSONObject json) throws JSONException {
        // Required empty public constructor
        this.json = json;
        stayDetailsArrayList = new ArrayList<>();
        JSONArray stayDetails = json.getJSONArray("stay");
        for (int i=0;i<stayDetails.length();i++)
        {
            JSONObject stay = stayDetails.getJSONObject(i);
            stayDetailsArrayList.add(new StayDetails(stay.getString("from"),stay.getString("to"),stay.getString("res_addr"),stay.getString("dist_hq_name")));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stay_details_fragment, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);

        StayDetailsAdapter mAdapter = new StayDetailsAdapter(stayDetailsArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return rootView;

    }

}
