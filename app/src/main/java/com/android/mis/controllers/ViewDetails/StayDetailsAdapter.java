package com.android.mis.controllers.ViewDetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.models.ViewDetails.StayDetails;

import java.util.List;

/**
 * Created by rajat on 21/3/17.
 */


public class StayDetailsAdapter extends RecyclerView.Adapter<StayDetailsAdapter.MyViewHolder> {

    private List<StayDetails> StayDetailssList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView from,to,address,headquarters;

        public MyViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            to = (TextView) view.findViewById(R.id.to);
            address = (TextView) view.findViewById(R.id.address);
            headquarters = (TextView) view.findViewById(R.id.headquarters);
        }
    }


    public StayDetailsAdapter(List<StayDetails> StayDetailsList) {
        this.StayDetailssList = StayDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stay_details_fragment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StayDetails member = StayDetailssList.get(position);
        holder.from.setText(member.getFrom());
        holder.to.setText(member.getTo());
        holder.address.setText(member.getAddress());
        holder.headquarters.setText(member.getHeadquarters());
        if(member.getTag()%2 == 0)
            holder.itemView.setBackgroundResource(R.color.details_background1);
        else
            holder.itemView.setBackgroundResource(R.color.details_background2);
    }

    @Override
    public int getItemCount() {
        return StayDetailssList.size();
    }
}