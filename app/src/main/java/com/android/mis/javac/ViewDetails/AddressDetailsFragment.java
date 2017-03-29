package com.android.mis.javac.ViewDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.models.ViewDetails.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressDetailsFragment extends Fragment {

    private JSONObject addressDetails;
    private String auth;
    private ArrayList<Address> addresses;

    public AddressDetailsFragment() {
        // Required empty public constructor
    }

    public AddressDetailsFragment(JSONObject addressDetails, String auth) throws JSONException {
        this.addressDetails = addressDetails;
        addresses = new ArrayList<>();
        if(auth.contentEquals("stu")){
            addressDetails = addressDetails.getJSONObject("address");
            JSONObject present = addressDetails.getJSONObject("present");
            JSONObject permanent = addressDetails.getJSONObject("permanent");
            addresses.add(new Address(present.getString("line1"),present.getString("line2"),present.getString("city"),present.getString("state"),present.getString("pincode"),present.getString("country"),present.getString("contact_no"),"present"));
            addresses.add(new Address(permanent.getString("line1"),permanent.getString("line2"),permanent.getString("city"),permanent.getString("state"),permanent.getString("pincode"),permanent.getString("country"),permanent.getString("contact_no"),"permanent"));
        }
        else{
            JSONArray array = addressDetails.getJSONArray("address");
            for(int i=0;i<array.length();i++)
            {
                JSONObject address = array.getJSONObject(i);
                addresses.add(new Address(address.getString("line1"),address.getString("line2"),address.getString("city"),address.getString("state"),address.getString("pincode"),address.getString("country"),address.getString("contact_no"),address.getString("type")));
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.address_fragment, container, false);

        for(int i=0;i<addresses.size();i++)
        {
            if(addresses.get(i).getType().toLowerCase().contentEquals("present"))
            {
                TextView first = (TextView)rootView.findViewById(R.id.street_1);
                TextView second = (TextView)rootView.findViewById(R.id.city_1);
                TextView third = (TextView)rootView.findViewById(R.id.contact_1);

                first.setText(addresses.get(i).getLine1()+", "+addresses.get(i).getLine2());
                second.setText(addresses.get(i).getCity()+", "+addresses.get(i).getPincode()+", "+addresses.get(i).getState()+","+addresses.get(i).getCountry());
                third.setText(addresses.get(i).getContact_no());
            }
            else {

                TextView first = (TextView)rootView.findViewById(R.id.street_2);
                TextView second = (TextView)rootView.findViewById(R.id.city_2);
                TextView third = (TextView)rootView.findViewById(R.id.contact_2);

                first.setText(addresses.get(i).getLine1()+", "+addresses.get(i).getLine2());
                second.setText(addresses.get(i).getCity()+", "+addresses.get(i).getPincode()+", "+addresses.get(i).getState()+","+addresses.get(i).getCountry());
                third.setText(addresses.get(i).getContact_no());
            }

        }

        return rootView;
    }

}
