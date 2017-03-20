package com.android.mis.models;

/**
 * Created by rajat on 8/3/17.
 */

public class Post {
    private String user_post,time_post,type_user_post,details_post,text_button_post,button_link;
    private int image_drawable;

    public Post(){

    }

    public Post(String user_post,String time_post,String type_user_post,String details_post,String text_button_post,String button_link,int image_drawable)
    {
        this.user_post = user_post;
        this.time_post = time_post;
        this.type_user_post = type_user_post;
        this.details_post = details_post;
        this.text_button_post = text_button_post;
        this.button_link = button_link;
        this.image_drawable = image_drawable;
    }

    public String getUserOfPost(){
        return user_post;
    }

    public int getImageDrawable() { return image_drawable; }

    public String getTimeOfPost(){
        return time_post;
    }

    public String getTypeOfUserOfPost(){
        return type_user_post;
    }

    public String getDetailsOfPost(){
        return details_post;
    }

    public String getTextOfPostButton(){
        if(text_button_post.length() != 0)
            return text_button_post;
        else
            return "";
    }

    public String getLinkOfButton(){
        if(button_link.length() != 0)
        {
            return button_link;
        }
        else{
            return "";
        }
    }
}
