package com.android.mis.models.Attendance;

/**
 * Created by rajat on 31/3/17.
 */

public class SubjectAttendanceItem {
    private String subjectId,name,id,totalAbsent,totalClass;
    private int tag;

    public SubjectAttendanceItem(String subjectId,String name,String id,String totalAbsent,String totalClass,int tag)
    {
        this.subjectId = subjectId;
        this.name = name;
        this.id = id;
        this.totalAbsent = totalAbsent;
        this.totalClass = totalClass;
        this.tag = tag;
    }

    public SubjectAttendanceItem(String subjectId,String name,String id,int totalAbsent,int totalClass,int tag)
    {
        this.subjectId = subjectId;
        this.name = name;
        this.id = id;
        this.totalAbsent = Integer.toString(totalAbsent);
        if(totalClass == -1)
        this.totalClass = "class not started";
        else
        this.totalClass = Integer.toString(totalClass);
        this.tag = tag;
    }

    public String getSubjectId(){
        return subjectId;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getTotalAbsent(){
        return totalAbsent;
    }

    public String getTotalClass(){
        return totalClass;
    }

    public int getTag(){
        return tag;
    }
}
