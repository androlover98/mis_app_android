package com.android.mis.models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by rajat on 24/3/17.
 */

public class Course {
    private String id,deptName,duration,courseId,branchId,branchName,start,end,courseName;
    private ArrayList<String> semList;
    private int tag;

    public Course(String id,String deptName,String duration,String courseId,String branchId,String branchName,String start,String end,String courseName,ArrayList<String> semList,int tag){
        this.id = id;
        this.deptName = deptName;
        this.duration = duration;
        this.courseId = courseId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.start = start;
        this.end = end;
        this.courseName = courseName;
        this.semList = semList;
        this.tag = tag;
    }

    public String getId(){
        return id;
    }

    public String getDeptName(){
        return deptName;
    }

    public String getCourseId(){
        return courseId;
    }

    public String getBranchId(){
        return branchId;
    }

    public String getBranchName(){
        return branchName;
    }

    public String getDuration(){
        return duration;
    }

    public ArrayList<String> getSemList(){
        return semList;
    }

    public String getStart(){
        return start;
    }

    public String getEnd(){
        return end;
    }

    public String getCourseName(){
        return courseName;
    }

    public int getTag(){
        return tag;
    }
}
