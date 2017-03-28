package com.android.mis.controllers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.javac.ViewCourse;
import com.android.mis.models.Course;
import com.android.mis.utils.Util;

import java.util.List;

/**
 * Created by rajat on 24/3/17.
 */


public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {

    private List<Course> CoursesList;
    private Context context;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView course,session,branch;
        public Spinner spinner;
        public Button view_course;
        public Layout layout;

        public MyViewHolder(View view) {
            super(view);
            session = (TextView)view.findViewById(R.id.session);
            branch = (TextView)view.findViewById(R.id.branch);
            course = (TextView) view.findViewById(R.id.course);
            spinner = (Spinner)view.findViewById(R.id.semester_spinner);
            view_course = (Button)view.findViewById(R.id.view_course_button);
        }
    }


    public CoursesAdapter(List<Course> CourseList,Context context,Activity activity) {
        this.CoursesList = CourseList;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_structure_key_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Course member = CoursesList.get(position);
        holder.course.setText(member.getCourseName());
        holder.branch.setText(member.getBranchName());
        holder.session.setText("["+member.getStart()+"-"+member.getEnd()+"]");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,R.layout.simple_spinner_item,member.getSemList());

        holder.spinner.setAdapter(dataAdapter);
        holder.view_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                String selected = holder.spinner.getSelectedItem().toString();
                extras.putString("session",member.getStart()+"_"+member.getEnd());
                extras.putString("branch_id",member.getBranchId());
                extras.putString("course",member.getCourseId());
                if(selected.contentEquals("All"))
                {
                    extras.putString("semester","*");
                    extras.putString("start_sem",member.getSemList().get(1));
                    extras.putString("end_sem",member.getSemList().get(member.getSemList().size()-1));
                }
                else{
                    extras.putString("semester",selected);
                    extras.putString("start_sem",selected);
                    extras.putString("end_sem",selected);
                }
                Util.moveToActivity(activity, ViewCourse.class,extras);
            }
        });

        if(member.getTag()%2 == 0)
            holder.itemView.setBackgroundResource(R.color.details_background1);
        else
            holder.itemView.setBackgroundResource(R.color.details_background2);
    }

    @Override
    public int getItemCount() {
        return CoursesList.size();
    }
}
