package com.android.mis.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rajat on 23/3/17.
 */

import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.mis.R;
import com.android.mis.models.CourseStructure.Department;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    int itemRes;
    ArrayList<Department> objects;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext,int item_res,ArrayList<Department> objects) {
        this.context = applicationContext;
        this.objects = objects;
        this.itemRes = item_res;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(itemRes, null);
        TextView names = (TextView) view.findViewById(R.id.name);
        names.setText(objects.get(i).getDeptName());
        return view;
    }
}