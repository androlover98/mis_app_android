package com.android.mis.javac.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mis.R;
import com.android.mis.controllers.Home.PostAdapter;
import com.android.mis.models.Home.Post;
import com.android.mis.models.Home.Post1;
import com.android.mis.models.Home.PostList;
import com.android.mis.utils.Urls;
import com.android.mis.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajat on 6/3/17.
 */

public class HomeTabsFragment extends Fragment {

    private List<PostList> posts;
    private String type;
    private int image_res;
    private JSONArray post_details;
    private HashMap<String,ArrayList<Post1>> hmap;
    private ArrayList<String> dates;

    public HomeTabsFragment(){

    }

    public HomeTabsFragment(String type, JSONArray post_details) throws JSONException {
        this.type = type;
        this.post_details = post_details;
        hmap = new HashMap<>();
        dates = new ArrayList<>();
        setImageRes();
        for(int i=0;i<post_details.length();i++)
        {
            JSONObject post = post_details.getJSONObject(i);
            //Date date = new Date(post.getString("posted_on"));
            if(hmap.get(Util.getDateFromDateTime(post.getString("posted_on"))) == null) {
                hmap.put(Util.getDateFromDateTime(post.getString("posted_on")),new ArrayList<Post1>());
                dates.add(Util.getDateFromDateTime(post.getString("posted_on")));
            }
            switch (type){
                case "notice":
                    image_res = R.drawable.info;
                    hmap.get(Util.getDateFromDateTime(post.getString("posted_on"))).add(new Post1(post.getString("notice_id"),post.getString("notice_no"),post.getString("notice_cat"),post.getString("notice_sub"),post.getString("notice_path"),post.getString("issued_by"),post.getString("auth_id"),post.getString("posted_on"),post.getString("last_date"),Integer.toString(post.getInt("modification_value")),getName(post.getString("salutation"),post.getString("first_name"),post.getString("middle_name"),post.getString("last_name")),post.getString("auth_name"),post.getString("department"),post.getString("designation"),image_res));
                    break;
                case "circular":
                    image_res = R.drawable.circular;
                    hmap.get(Util.getDateFromDateTime(post.getString("posted_on"))).add(new Post1(post.getString("circular_id"),post.getString("circular_no"),post.getString("circular_cat"),post.getString("circular_sub"),post.getString("circular_path"),post.getString("issued_by"),post.getString("auth_id"),post.getString("posted_on"),post.getString("last_date"),Integer.toString(post.getInt("modification_value")),getName(post.getString("salutation"),post.getString("first_name"),post.getString("middle_name"),post.getString("last_name")),post.getString("auth_name"),post.getString("department"),post.getString("designation"),image_res));
                    break;
                case "meeting":
                    image_res = R.drawable.meetings;
                    hmap.get(Util.getDateFromDateTime(post.getString("posted_on"))).add(new Post1(post.getString("minutes_id"),post.getString("minutes_no"),post.getString("meeting_cat"),post.getString("meeting_type"),post.getString("minutes_path"),post.getString("issued_by"),post.getString("auth_id"),post.getString("posted_on"),post.getString("valid_upto"),Integer.toString(post.getInt("modification_value")),getName(post.getString("salutation"),post.getString("first_name"),post.getString("middle_name"),post.getString("last_name")),post.getString("auth_name"),post.getString("department"),post.getString("designation"),image_res));
                    break;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_fragment_home, container, false);

        posts = new ArrayList<>();

        try {
            populateRealData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager  = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        PostAdapter adapter = new PostAdapter(posts);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private void populateSampleData(int image_res)
    {
        /*ArrayList<Post> temp = new ArrayList<>();
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));

        posts.add(new PostList("23-09-2017",temp));
        temp.clear();
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));
        temp.add(new Post("Rajat Bajaj","12:23 PM","Student of ISM","Something is launching herea at IMS","Download","abc",image_res));

        posts.add(new PostList("24-08-2017",temp));*/
    }

    private void populateRealData() throws JSONException {
        if(post_details.length() == 0)
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<Post1> temp = new ArrayList<>();
            temp.add(new Post1("","","","","","","","","","",Urls.no_post_message,"","","",image_res));
            posts.add(new PostList(dateFormat.format(date),temp));
        }
        for(int i=0;i<dates.size();i++)
        {
            posts.add(new PostList(dates.get(i),hmap.get(Util.getDateFromDateTime(dates.get(i)))));
        }
    }

    private void setImageRes(){
        switch (type){
            case "notice":
                image_res = R.drawable.info;
                break;
            case "circular":
                image_res = R.drawable.circular;
                break;
            case "meeting":
                image_res = R.drawable.meetings;
                break;
        }
    }

    private String getName(String salutation,String firstName,String middleName,String lastName){
        if(middleName.trim().length() == 0)
        {
            return salutation+" "+firstName+" "+lastName;
        }
        else{
            return salutation+" "+firstName+" "+middleName+" "+lastName;
        }
    }
}