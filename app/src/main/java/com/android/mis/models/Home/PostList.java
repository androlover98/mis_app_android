package com.android.mis.models.Home;

import java.util.ArrayList;

/**
 * Created by rajat on 18/3/17.
 */

public class PostList {
    private String date;
    private ArrayList<Post1> allPostsOnGivenDate;

    public PostList(){

    }

    public PostList(String date,ArrayList<Post1> allPostsOnDate){
        this.date = date;
        this.allPostsOnGivenDate = allPostsOnDate;
    }

    public String getSectionTitle(){
        return this.date;
    }

    public void setSectionTitle(String section){
        this.date = section;
    }

    public ArrayList<Post1> getAllPostsOnGivenDate(){
        return this.allPostsOnGivenDate;
    }
}
