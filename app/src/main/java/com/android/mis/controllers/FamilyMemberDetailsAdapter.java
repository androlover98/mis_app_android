package com.android.mis.controllers;

import android.content.Context;
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
import com.android.mis.utils.CircleTransform;
import com.android.mis.utils.Urls;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rajat on 21/3/17.
 */


public class FamilyMemberDetailsAdapter extends RecyclerView.Adapter<FamilyMemberDetailsAdapter.MyViewHolder> {

    private List<FamilyMember> familyMembersList;
    private Context context;

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


    public FamilyMemberDetailsAdapter(List<FamilyMember> familyMembersList,Context context) {
        this.familyMembersList = familyMembersList;
        this.context = context;
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

        Picasso.with(context).load(Urls.image_base_path+member.getImage_url()).transform(new CircleTransform()).placeholder(R.mipmap.default_usr).error(R.mipmap.default_usr).resize(250,250).into(holder.pic);

        if(member.getStatus().toLowerCase().contentEquals("active"))
        {
            holder.status.setText("ACTIVE");
            holder.status.setTextColor(Color.GREEN);
        }
        else{
            holder.status.setText("ACTIVE");
            holder.status.setTextColor(Color.RED);
        }

        if(member.getTag()%2 == 0)
            holder.itemView.setBackgroundResource(R.color.details_background1);
        else
            holder.itemView.setBackgroundResource(R.color.details_background2);
    }

    @Override
    public int getItemCount() {
        return familyMembersList.size();
    }
}
