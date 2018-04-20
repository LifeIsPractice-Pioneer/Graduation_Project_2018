package com.example.pioneer.vocabulary_app_project.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pioneer.vocabulary_app_project.R;

/**
 * Created by Pioneer on 2018/3/6.
 */

public class MyFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment, container, false);
        return view;
    }

}
