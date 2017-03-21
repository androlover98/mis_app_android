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
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeDetailsFragment extends Fragment {


    JSONObject feeDetails;

    public FeeDetailsFragment(JSONObject feeDetails) {
        // Required empty public constructor
        this.feeDetails = feeDetails;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fee_details_fragment, container, false);

        TextView mop = (TextView)rootView.findViewById(R.id.mop);
        TextView fpd = (TextView)rootView.findViewById(R.id.fpd);
        TextView cheque = (TextView)rootView.findViewById(R.id.cheque);
        TextView fpa = (TextView)rootView.findViewById(R.id.fpa);

        try {
            mop.setText(feeDetails.getString("mode_of_payment"));
            fpd.setText(feeDetails.getString("date"));
            cheque.setText(feeDetails.getString("dd_no"));
            fpa.setText(feeDetails.getString("amount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

}
