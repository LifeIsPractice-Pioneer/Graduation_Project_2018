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
import com.example.pioneer.vocabulary_app_project.activity.MainVoActivity;

public class DiscoverFragment extends Fragment {
    private View view;
    private ImageButton hp_ib_tb;
    private ImageButton hp_ib_tm;
    private ImageButton hp_ib_jd;
    private ImageButton hp_ib_dd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.discover_fragment, container, false);
        hp_ib_tb = (ImageButton) view.findViewById(R.id.hp_ib_tb);
        hp_ib_tm = (ImageButton) view.findViewById(R.id.hp_ib_tm);
        hp_ib_jd = (ImageButton) view.findViewById(R.id.hp_ib_jd);
        hp_ib_dd = (ImageButton) view.findViewById(R.id.hp_ib_dd);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        hp_ib_tb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("https://www.taobao.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_tm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("https://www.tmall.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_jd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("https://www.jd.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_dd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("https://www.dangdang.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
    }

}
