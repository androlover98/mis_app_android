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
public class BankDetailsFragment extends Fragment {

    JSONObject bankDetails;

    public BankDetailsFragment(JSONObject bankDetails) {
        // Required empty public constructor
        this.bankDetails = bankDetails;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bank_details_fragment, container, false);

        TextView bn = (TextView)rootView.findViewById(R.id.bn);
        TextView ac = (TextView)rootView.findViewById(R.id.ac);

        try {
            bn.setText(bankDetails.getString("bank_name"));
            ac.setText(bankDetails.getString("acc_no"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

}
