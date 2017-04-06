package com.android.mis.controllers.Attendance;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.javac.ViewDetails.ViewDetails;
import com.android.mis.models.Attendance.SubjectAttendanceItem;
import com.android.mis.models.CourseStructure.Subject;
import com.android.mis.utils.Callback;
import com.android.mis.utils.NetworkRequest;
import com.android.mis.utils.SessionManagement;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rajat on 31/3/17.
 */

public class ViewAttendanceAdapter extends RecyclerView.Adapter<com.android.mis.controllers.Attendance.ViewAttendanceAdapter.MyViewHolder> implements Callback{

    private List<SubjectAttendanceItem> SubjectAttendanceItemsList;
    private Context context;
    private Activity activity;
    private TableLayout layout;
    //private Dialog dialog;


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
        holder.subjectId.setText('('+member.getSubjectId()+')');

        if(member.getTag()%2 == 0)
            holder.itemView.setBackgroundResource(R.color.details_background1);
        else
            holder.itemView.setBackgroundResource(R.color.details_background2);


        holder.progressBar1.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        holder.progressBar2.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

        holder.progressBar3.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);


        if(member.getTotalClass().contentEquals("class not started")) {
            return;
        }

        int total_classes = Integer.parseInt(member.getTotalClass());
        int required_classes = (int) Math.ceil(.75*total_classes);
        int attend_classes = total_classes - Integer.parseInt(member.getTotalAbsent());

        int percent = ((attend_classes*100)/total_classes);

        holder.p1_label.setText(member.getTotalClass());
        holder.p2_label.setText(Integer.toString(required_classes));
        holder.p3_label.setText(Integer.toString(attend_classes)+"("+Integer.toString(percent)+"%)");

        holder.progressBar3.setProgress(percent);
        holder.progressBar2.setProgress(75);
        holder.progressBar1.setProgress(100);

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View mProgressView;
                final View mErrorView;
                Button refreshOnError;

                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_attendance_details);
                dialog.setTitle("Details");
                dialog.show();

                mProgressView = dialog.findViewById(R.id.loader);
                mErrorView = dialog.findViewById(R.id.err);
                refreshOnError = (Button)mErrorView.findViewById(R.id.refresh_button);
                layout = (TableLayout)dialog.findViewById(R.id.attendance_details_table) ;

                refreshOnError.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fetchAttendanceDetails(mProgressView,mErrorView,member);
                    }
                });
                fetchAttendanceDetails(mProgressView,mErrorView,member);
            }
        });
    }

    private void fetchAttendanceDetails(View mProgressView,View mErrorView,SubjectAttendanceItem member)
    {
        HashMap<String,String> params = new HashMap<>();
        SessionManagement session = new SessionManagement(context);
        if(session.isLoggedIn())
        {
            params = session.getSessionDetails();
        }
        params.put("map_id",member.getMapId());
        params.put("sub_id",member.getId());
        NetworkRequest nr = new NetworkRequest(activity,mProgressView,mErrorView,this,"get", Urls.server_protocol,Urls.view_detailed_attendance_url,params,false,true,0);
        nr.setSnackbar_message(Urls.error_connection_message);
        nr.initiateRequest();
    }

    @Override
    public void performAction(String result, int tag) {
        try{
            JSONObject json = new JSONObject(result);
            if(json.getBoolean("success") == true)
            {
                JSONObject attendance_details = json.getJSONObject("attendance");
                Iterator keysToCopyIterator = attendance_details.keys();
                ArrayList<String> keysList = new ArrayList<String>();
                while(keysToCopyIterator.hasNext()) {
                    String key = (String) keysToCopyIterator.next();
                    keysList.add(key);
                }
                showView(keysList,attendance_details);
            }
        }catch (Exception e){
            Log.e("Exception",e.toString());
        }
    }

    private void showView(ArrayList<String> keys,JSONObject json) throws JSONException {
        for(int i=0;i<keys.size();i++)
        {
            layout.addView(getTableRow(keys.get(i),json.getInt(keys.get(i))),new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    private TableRow getTableRow(String date,int status){
        TableRow row = new TableRow(context);
        TextView date_class = new TextView(context);

        date_class.setText(date);
        date_class.setTextColor(Color.BLACK);
        date_class.setMaxLines(1);
        date_class.setGravity(Gravity.CENTER);
        date_class.setTextSize(12);
        date_class.setBackgroundResource(R.drawable.table_border);
        row.addView(date_class);

        TextView status_stu = new TextView(context);

        if(status == 1) {
            status_stu.setText("'P'");
            status_stu.setTextColor(Color.GREEN);
        }
        else{
            status_stu.setText("'A'");
            status_stu.setTextColor(Color.RED);
        }
        status_stu.setMaxLines(1);
        status_stu.setGravity(Gravity.CENTER);
        status_stu.setTextSize(12);
        status_stu.setBackgroundResource(R.drawable.table_border);
        row.addView(status_stu);

        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


        return row;
    }


    @Override
    public int getItemCount() {
        return SubjectAttendanceItemsList.size();
    }
}
