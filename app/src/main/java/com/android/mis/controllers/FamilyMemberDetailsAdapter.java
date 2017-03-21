package com.android.mis.controllers;

import android.graphics.Color;
import android.graphics.Movie;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.models.FamilyMember;

import java.util.List;

/**
 * Created by rajat on 21/3/17.
 */


public class FamilyMemberDetailsAdapter extends RecyclerView.Adapter<FamilyMemberDetailsAdapter.MyViewHolder> {

    private List<FamilyMember> familyMembersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,relationship,dob,profession,postal_address,status;
        public ImageView pic;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            relationship = (TextView) view.findViewById(R.id.relation);
            dob = (TextView) view.findViewById(R.id.dob);
            profession = (TextView) view.findViewById(R.id.profession);
            postal_address = (TextView) view.findViewById(R.id.address)   ;
            status = (TextView) view.findViewById(R.id.status);
            pic = (ImageView) view.findViewById(R.id.member_pic);
        }
    }


    public FamilyMemberDetailsAdapter(List<FamilyMember> familyMembersList) {
        this.familyMembersList = familyMembersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emp_family_details_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FamilyMember member = familyMembersList.get(position);
        holder.name.setText(member.getName());
        holder.relationship.setText('('+member.getRelationship()+')');
        holder.dob.setText(member.getDateofbirth());
        holder.profession.setText(member.getProfession());
        holder.postal_address.setText(member.getPostal_address());
        if(member.getStatus().toLowerCase().contentEquals("active"))
        {
            holder.postal_address.setText("ACTIVE");
            holder.postal_address.setTextColor(Color.GREEN);
        }
        else{
            holder.postal_address.setText("ACTIVE");
            holder.postal_address.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return familyMembersList.size();
    }
}
