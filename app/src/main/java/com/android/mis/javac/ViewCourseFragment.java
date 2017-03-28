package com.android.mis.javac;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mis.R;
import com.android.mis.models.Subject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCourseFragment extends Fragment {

    private JSONObject json;
    private String semester;
    private HashMap<String,String> hmap_group;
    private HashMap<String,ArrayList<Subject>> hmap_subjects_elective;
    private ArrayList<Subject> subject_list;
    private String check = "Hey";
    private String electives = "";
    private int textSize = 10;
    private String anyName = "";

    public ViewCourseFragment() {
        // Required empty public constructor
    }

    public ViewCourseFragment(JSONObject json,String semester) throws JSONException {
        this.json = json;
        this.semester = semester;
        hmap_subjects_elective = new HashMap<>();
        hmap_group = new HashMap<>();
        subject_list = new ArrayList<>();

        JSONObject course = json.getJSONObject("course");
        JSONObject subjects = course.getJSONObject("subjects");
        JSONObject group_details = subjects.getJSONObject("group_details");
        JSONObject subject_details = subjects.getJSONObject("subject_details");
        JSONObject group_details_current_sem = group_details.getJSONObject(semester);
        JSONObject subject_details_current_sem = subject_details.getJSONObject(semester);

        Iterator keys = group_details_current_sem.keys();
        while(keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject temp = group_details_current_sem.getJSONObject(key);
            hmap_group.put(key,temp.getString("elective_name"));
            ArrayList<Subject> temp_arr = new ArrayList<>();
            hmap_subjects_elective.put(temp.getString("elective_name"),temp_arr);
            check = check + key +"-"+ temp.getString("elective_name")+" \n ";
            electives += temp.getString("elective_name")+",";
            if(anyName.length() == 0)
                anyName = temp.getString("elective_name");
        }

        keys = subject_details_current_sem.keys();
        while(keys.hasNext()) {
            String key = (String) keys.next();
            JSONObject temp = subject_details_current_sem.getJSONObject(key);
            if(temp.getString("elective").contentEquals("0"))
            {
                subject_list.add(new Subject(temp.getString("elective"),temp.getString("id"),temp.getString("subject_id"),temp.getString("name"),temp.getString("lecture"),temp.getString("tutorial"),temp.getString("practical"),temp.getString("credit_hours"),temp.getString("contact_hours"),temp.getString("type")));
            }
            else{
                hmap_subjects_elective.get(hmap_group.get(temp.getString("elective"))).add(new Subject(temp.getString("elective"),temp.getString("id"),temp.getString("subject_id"),temp.getString("name"),temp.getString("lecture"),temp.getString("tutorial"),temp.getString("practical"),temp.getString("credit_hours"),temp.getString("contact_hours"),temp.getString("type")));
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_course, container, false);
        TableLayout course_table = (TableLayout)rootView.findViewById(R.id.course_table);
        TableLayout course_table_elective = (TableLayout)rootView.findViewById(R.id.course_table_elective);
        TextView label = (TextView)rootView.findViewById(R.id.label);

        for(int i=0;i<subject_list.size();i++)
        {
            course_table.addView(getTableRow(subject_list.get(i),i),new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        if(hmap_group.size() != 0)
        {
            TextView elective = new TextView(getActivity().getApplicationContext());
            elective.setText(electives);
            elective.setTextSize(textSize);
            elective.setTextColor(Color.BLACK);
            elective.setBackgroundResource(R.color.details_background1);
            elective.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f));

            course_table.addView(elective);
        }

        if(hmap_group.size()!=0)
        {
          //  label.setVisibility(View.VISIBLE);
          //  label.setText(electives);

            ArrayList<Subject> elective_subject_list = new ArrayList<>();
            elective_subject_list = hmap_subjects_elective.get(anyName);

            for(int i=0;i<elective_subject_list.size();i++)
            {
                course_table.addView(getTableRow(elective_subject_list.get(i),i),new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }

        }

        return rootView;
    }

    private TableRow getTableRow(Subject subject,int id){
        TableRow row = new TableRow(getActivity().getApplicationContext());
        TextView subjectId = new TextView(getActivity().getApplicationContext());
        subjectId.setText(subject.getSubjectId());
        subjectId.setTextColor(Color.BLACK);
        subjectId.setMaxLines(1);
        subjectId.setTextSize(textSize);
        subjectId.setBackgroundResource(R.drawable.table_border);
        row.addView(subjectId);

        TextView subjectName = new TextView(getActivity().getApplicationContext());
        subjectName.setText(subject.getName());
        subjectName.setTextSize(textSize);
        subjectName.setMaxLines(1);
        subjectName.setTextColor(Color.BLACK);
        subjectName.setBackgroundResource(R.drawable.table_border);
        row.addView(subjectName);

        TextView lecture = new TextView(getActivity().getApplicationContext());
        lecture.setText(subject.getLecture());
        lecture.setTextColor(Color.BLACK);
        lecture.setTextSize(textSize);
        lecture.setBackgroundResource(R.drawable.table_border);
        row.addView(lecture);

        TextView tutorial = new TextView(getActivity().getApplicationContext());
        tutorial.setText(subject.getTutorial());
        tutorial.setTextColor(Color.BLACK);
        tutorial.setTextSize(textSize);
        tutorial.setBackgroundResource(R.drawable.table_border);
        row.addView(tutorial);

        TextView practical = new TextView(getActivity().getApplicationContext());
        practical.setText(subject.getPractical());
        practical.setTextColor(Color.BLACK);
        practical.setTextSize(textSize);
        practical.setBackgroundResource(R.drawable.table_border);
        row.addView(practical);

        TextView creditHours = new TextView(getActivity().getApplicationContext());
        creditHours.setText(subject.getCreditHours());
        creditHours.setTextColor(Color.BLACK);
        creditHours.setTextSize(textSize);
        creditHours.setBackgroundResource(R.drawable.table_border);
        row.addView(creditHours);

        TextView contactHours = new TextView(getActivity().getApplicationContext());
        contactHours.setText(subject.getContactHours());
        contactHours.setTextColor(Color.BLACK);
        contactHours.setTextSize(textSize);
        contactHours.setBackgroundResource(R.drawable.table_border);
        row.addView(contactHours);

        TextView elective = new TextView(getActivity().getApplicationContext());
        if(subject.getElective().contentEquals("0"))
        {
            elective.setText("No");
        }
        else{
            elective.setText("Yes");
        }
        elective.setTextColor(Color.BLACK);
        elective.setTextSize(textSize);
        elective.setBackgroundResource(R.drawable.table_border);
        row.addView(elective);

        TextView type = new TextView(getActivity().getApplicationContext());
        type.setText(subject.getType());
        type.setTextColor(Color.BLACK);
        type.setMaxLines(1);
        type.setTextSize(textSize);
        type.setBackgroundResource(R.drawable.table_border);
        row.addView(type);

        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        return row;
    }

}
