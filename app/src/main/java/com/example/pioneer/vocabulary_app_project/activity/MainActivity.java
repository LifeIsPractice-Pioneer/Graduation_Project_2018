package com.example.pioneer.vocabulary_app_project.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.pioneer.vocabulary_app_project.R;
import com.example.pioneer.vocabulary_app_project.adapter.MyAdapter;
import com.example.pioneer.vocabulary_app_project.fragment.CourseFragment;
import com.example.pioneer.vocabulary_app_project.fragment.DiscoverFragment;
import com.example.pioneer.vocabulary_app_project.fragment.HomepageFragment;
import com.example.pioneer.vocabulary_app_project.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.radioGroup);//实例化radiogroup
        fragments = new ArrayList<Fragment>();

        //分别添加4个fragment
        fragments.add(new HomepageFragment());
        fragments.add(new CourseFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new MyFragment());
        new MyAdapter(this, fragments, R.id.Fragment, rg);

    }
}
