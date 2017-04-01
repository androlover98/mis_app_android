package com.android.mis.controllers.Attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.models.Attendance.SubjectAttendanceItem;
import com.android.mis.utils.Util;

import java.util.List;

/**
 * Created by rajat on 31/3/17.
 */

public class ViewAttendanceAdapter extends RecyclerView.Adapter<com.android.mis.controllers.Attendance.ViewAttendanceAdapter.MyViewHolder> {

    private List<SubjectAttendanceItem> SubjectAttendanceItemsList;
    private Context context;
    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName,subjectId;
        public Button details;
        public ProgressBar progressBar1,progressBar2,progressBar3;
        public TextView p1_label,p2_label,p3_label;

        public MyViewHolder(View view) {
            super(view);
            subjectName = (TextView)view.findViewById(R.id.subject_name);
            subjectId = (TextView)view.findViewById(R.id.subject_id);
            details = (Button) view.findViewById(R.id.attendance_details);
            progressBar1 = (ProgressBar)view.findViewById(R.id.progressBar1);
            progressBar2 = (ProgressBar)view.findViewById(R.id.progressBar2);
            progressBar3 = (ProgressBar)view.findViewById(R.id.progressBar3);
            p1_label = (TextView)view.findViewById(R.id.p1_label1);
            p2_label = (TextView)view.findViewById(R.id.p1_label2);
            p3_label = (TextView)view.findViewById(R.id.p1_label3);

        }
    }


    public ViewAttendanceAdapter(List<SubjectAttendanceItem> SubjectAttendanceItemList,Context context,Activity activity) {
        this.SubjectAttendanceItemsList = SubjectAttendanceItemList;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public com.android.mis.controllers.Attendance.ViewAttendanceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attendance_details_item, parent, false);
        return new com.android.mis.controllers.Attendance.ViewAttendanceAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewAttendanceAdapter.MyViewHolder holder, int position) {
        final SubjectAttendanceItem member = SubjectAttendanceItemsList.get(position);
        holder.subjectName.setText(member.getName());
        holder.subjectId.setText(member.getSubjectId());

        if(member.getTotalClass().contentEquals("class not started")) {
            return;
        }

        int total_classes = Integer.parseInt(member.getTotalClass());
        int required_classes = (int) Math.ceil(.75*total_classes);
        int attend_classes = total_classes - Integer.parseInt(member.getTotalAbsent());

        int percent = (attend_classes*100/total_classes);

        holder.progressBar1.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        holder.progressBar2.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

        holder.progressBar3.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        holder.progressBar3.setProgress(percent);
        holder.progressBar2.setProgress(75);
        holder.progressBar1.setProgress(100);


        holder.p1_label.setText(member.getTotalClass());
        holder.p2_label.setText(member.getTotalClass());
        holder.p3_label.setText(member.getTotalClass());

        if(member.getTag()%2 == 0)
            holder.itemView.setBackgroundResource(R.color.details_background1);
        else
            holder.itemView.setBackgroundResource(R.color.details_background2);

    }

    @Override
    public int getItemCount() {
        return SubjectAttendanceItemsList.size();
    }
}
