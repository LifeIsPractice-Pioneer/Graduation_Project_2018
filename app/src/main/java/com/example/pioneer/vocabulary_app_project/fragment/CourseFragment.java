package com.example.pioneer.vocabulary_app_project.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pioneer.vocabulary_app_project.R;

public class CourseFragment extends Fragment {
    private View view;
    private ImageButton hp_ib_jr;
    private ImageButton hp_ib_hj;
    private ImageButton hp_ib_bw;
    private ImageButton hp_ib_ww;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.course_fragment, container, false);
        hp_ib_jr = (ImageButton) view.findViewById(R.id.hp_ib_jr);
        hp_ib_hj = (ImageButton) view.findViewById(R.id.hp_ib_hj);
        hp_ib_bw = (ImageButton) view.findViewById(R.id.hp_ib_bw);
        hp_ib_ww = (ImageButton) view.findViewById(R.id.hp_ib_ww);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        hp_ib_jr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("https://www.vipjr.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_hj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("https://class.hujiang.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_bw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("http://www.beiwaiclass.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_ww.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("http://www.24en.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
    }
}
