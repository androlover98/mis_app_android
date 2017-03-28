package com.android.mis.models;

/**
 * Created by rajat on 26/3/17.
 */

public class Subject {
    private String elective, id, subjectId, name, lecture, tutorial, practical, creditHours, contactHours, type;

    public Subject(String elective, String id, String subjectId, String name, String lecture, String tutorial, String practical, String creditHours, String contactHours, String type)
    {
        this.elective = elective;
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.lecture = lecture;
        this.tutorial = tutorial;
        this.practical = practical;
        this.creditHours = creditHours;
        this.contactHours = contactHours;
        this.type = type;
    }

    public String getElective(){
        return elective;
    }

    public String getId(){
        return id;
    }

    public String getSubjectId(){
        return subjectId;
    }

    public String getName(){
        return name;
    }

    public String getLecture(){
        return lecture;
    }

    public String getTutorial(){
        return tutorial;
    }

    public String getPractical(){
        return practical;
    }

    public String getCreditHours(){
        return creditHours;
    }

    public String getContactHours(){
        return contactHours;
    }

    public String getType(){
        return type;
    }
}
