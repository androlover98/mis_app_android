package com.android.mis.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.mis.R;
import com.androidadvance.topsnackbar.TSnackbar;

import java.util.HashMap;

/**
 * Created by RDC on 1/29/2016.
 */
public class Util {
    public final static String FOOD_FETCH = "fetch_food";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";


    public static boolean checkInternet(Context context)
    {
        ConnectivityManager check = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();
        for (int i = 0; i<info.length; i++){
            if (info[i].getState() == NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;
    }

    public static void viewSnackbar(View v,String message)
    {
        TSnackbar snackbar = TSnackbar.make(v, message, TSnackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#f56954"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void moveToActivity(Activity source, Class destination, Bundle bundle)
    {
        Intent i = new Intent(source,destination);
        source.startActivity(i);
    }

    public static HashMap<String,Integer> getMenuMappedIds(){
        HashMap<String,Integer> hmap = new HashMap<>();
        hmap.put("home", R.id.home);
        hmap.put("user_details",R.id.user_details);
        hmap.put("view_details",R.id.view_details);
        hmap.put("edit_details",R.id.edit_details);
        hmap.put("attendance",R.id.attendance);
        hmap.put("view_attendance",R.id.view_attendance);
        hmap.put("view_defaulter_list",R.id.view_defaulter_list);
        hmap.put("course_structure",R.id.course_structure);
        hmap.put("view_course_structure",R.id.view_course_structure);

        return hmap;
    }


}
