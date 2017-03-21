package com.android.mis.models;

/**
 * Created by rajat on 21/3/17.
 */

public class Education {
    private String examination,course,college,year,grade,division;

    public Education(String examination,String course,String college,String year,String grade,String division)
    {
        this.examination = examination;
        this.course = course;
        this.college = college;
        this.year = year;
        this.grade = grade;
        this.division = division;
    }

    public String getExamination()
    {
        return examination;
    }

    public String getCourse()
    {
        return course;
    }

    public String getCollege()
    {
        return college;
    }

    public String getYear()
    {
        return year;
    }

    public String getGrade()
    {
        return grade;
    }

    public String getDivision()
    {
        return division;
    }
}
