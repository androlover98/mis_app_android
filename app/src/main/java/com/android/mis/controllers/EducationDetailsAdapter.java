package com.android.mis.controllers;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.models.Education;

import java.util.List;

import static com.android.mis.R.color.details_background1;

/**
 * Created by rajat on 21/3/17.
 */


public class EducationDetailsAdapter extends RecyclerView.Adapter<EducationDetailsAdapter.MyViewHolder> {

    private List<Education> EducationsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView examination,course,college,year,grade,division;
        public Layout layout;

        public MyViewHolder(View view) {
            super(view);
            examination = (TextView) view.findViewById(R.id.examination);
            college = (TextView) view.findViewById(R.id.school);
            course = (TextView) view.findViewById(R.id.course);
            year = (TextView) view.findViewById(R.id.year);
            grade = (TextView) view.findViewById(R.id.grade)   ;
            division = (TextView) view.findViewById(R.id.division);
        }
    }


    public EducationDetailsAdapter(List<Education> educationList) {
        this.EducationsList = educationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.education_details_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Education member = EducationsList.get(position);
        holder.examination.setText(member.getExamination());
        holder.course.setText(member.getCourse());
        holder.college.setText(member.getCollege());
        holder.year.setText(member.getYear());
        holder.grade.setText(member.getGrade());
        holder.division.setText(member.getDivision());
        if(member.getTag()%2 == 0)
        holder.itemView.setBackgroundResource(R.color.details_background1);
        else
            holder.itemView.setBackgroundResource(R.color.details_background2);
    }

    @Override
    public int getItemCount() {
        return EducationsList.size();
    }
}
