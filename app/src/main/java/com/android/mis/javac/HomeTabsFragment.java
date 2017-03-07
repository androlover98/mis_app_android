package com.android.mis.javac;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mis.R;

/**
 * Created by rajat on 6/3/17.
 */

public class HomeTabsFragment extends Fragment {
    public HomeTabsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_fragment_home, container, false);
        return rootView;
    }
}