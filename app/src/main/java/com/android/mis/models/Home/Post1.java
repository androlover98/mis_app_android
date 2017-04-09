package com.android.mis.models.Home;

/**
 * Created by rajat on 9/4/17.
 */

public class Post1 {
    private String postId,postNo,postCat,postSub,postPath,postIssuedById,postIssuedAuthId,postIssueDate,postLastDate,postModificationValue,postIssuedBy,postAuthName,postIssuedByDept,postIssuedByDesignation;
    private int imageDrawable;


    public Post1(String postId,String postNo,String postCat,String postSub,String postPath,String postIssuedById,String postIssuedAuthId,String postIssueDate,String postLastDate,String postModificationValue,String postIssuedBy,String postAuthName,String postIssuedByDept,String postIssuedByDesignation,int imageDrawable){
        this.postId =postId;
        this.postNo = postNo;
        this.postCat = postCat;
        this.postSub = postSub;
        this.postPath = postPath;
        this.postIssuedById = postIssuedById;
        this.postIssuedAuthId = postIssuedAuthId;
        this.postIssueDate = postIssueDate;
        this.postLastDate = postLastDate;
        this.postModificationValue = postModificationValue;
        this.postIssuedBy = postIssuedBy;
        this.postAuthName = postAuthName;
        this.postIssuedByDept = postIssuedByDept;
        this.postIssuedByDesignation = postIssuedByDesignation;
        this.imageDrawable = imageDrawable;
    }

    public void setImageDrawable(int imageDrawable){
        this.imageDrawable = imageDrawable;
    }

    public int getImageDrawable(){
        return imageDrawable;
    }

    public String getPostId(){
        return postId;
    }

    public String getPostNo(){
        return postNo;
    }

    public String getPostCat(){
        return postCat;
    }

    public String getPostSub(){
        return postSub;
    }

    public String getPostPath(){
        return postPath;
    }

    public String getPostIssuedById(){
        return  postIssuedById;
    }

    public String getPostIssueDate()
    {
        if(postIssueDate.length() == 0)
            return "";
        return postIssueDate.split("T")[0];
    }

    public String getPostIssueTime(){
        if(postIssueDate.length() == 0)
            return "";
        return postIssueDate.split("T")[1].split(":")[0]+":"+postIssueDate.split("T")[1].split(":")[1];
    }

    public String getPostLastDate(){
        return postLastDate;
    }

    public String getPostModificationValue(){
        return postModificationValue;
    }

    public String getPostIssuedBy(){
        return postIssuedBy;
    }

    public String getPostAuthName(){
        return postAuthName;
    }

    public String getPostIssuedByDept(){
        return postIssuedByDept;
    }

    public String getPostIssuedByDesignation(){
        return postIssuedByDesignation;
    }


}
