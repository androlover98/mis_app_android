package com.android.mis.models;

import java.util.ArrayList;

/**
 * Created by rajat on 18/3/17.
 */

public class PostList {
    private String date;
    private ArrayList<Post> allPostsOnGivenDate;

    public PostList(){

    }

    public PostList(String date,ArrayList<Post> allPostsOnDate){
        this.date = date;
        this.allPostsOnGivenDate = allPostsOnDate;
    }

    public String getSectionTitle(){
        return this.date;
    }

    public void setSectionTitle(String section){
        this.date = section;
    }

    public ArrayList<Post> getAllPostsOnGivenDate(){
        return this.allPostsOnGivenDate;
    }
}
