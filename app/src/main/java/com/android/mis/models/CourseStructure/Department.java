package com.android.mis.models.CourseStructure;

/**
 * Created by rajat on 23/3/17.
 */

public class Department {
    private String deptId,deptName,deptType;

    public Department(String deptName,String deptId,String deptType){
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptType = deptType;
    }

    public String getDeptId(){
        return deptId;
    }

    public String getDeptName(){
        return deptName;
    }

    public String getDeptType() { return deptType;}

}
