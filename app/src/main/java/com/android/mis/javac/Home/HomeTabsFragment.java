package com.android.mis.javac.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mis.R;
import com.android.mis.controllers.Home.PostAdapter;
import com.android.mis.models.Home.Post;
import com.android.mis.models.Home.PostList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajat on 6/3/17.
 */

public class HomeTabsFragment extends Fragment {

    List<PostList> posts;
    private String type;
    private int image_res;

    public HomeTabsFragment(){

    }

    public HomeTabsFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_fragment_home, container, false);

        posts = new ArrayList<>();

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
            default:
                image_res = R.drawable.meetings;
        }
        populateSampleData(image_res);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager  = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        PostAdapter adapter = new PostAdapter(posts);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private void populateSampleData(int image_res)
    {
        ArrayList<Post> temp = new ArrayList<>();
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

        posts.add(new PostList("24-08-2017",temp));

    }
}