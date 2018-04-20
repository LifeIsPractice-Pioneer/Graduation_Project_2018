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
import com.example.pioneer.vocabulary_app_project.activity.MainTrActivity;
import com.example.pioneer.vocabulary_app_project.activity.MainVoActivity;

public class HomepageFragment extends Fragment {
    private View view;
    private ImageButton hp_ib_vo;
    private ImageButton hp_ib_tr;
    private ImageButton hp_ib_ar;
    private ImageButton hp_ib_mo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homepage_fragment, container, false);
        hp_ib_vo = (ImageButton) view.findViewById(R.id.hp_ib_vo);
        hp_ib_tr = (ImageButton) view.findViewById(R.id.hp_ib_tr);
        hp_ib_ar = (ImageButton) view.findViewById(R.id.hp_ib_ar);
        hp_ib_mo = (ImageButton) view.findViewById(R.id.hp_ib_mo);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        hp_ib_vo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainVoActivity.class);
                startActivity(intent);
            }
        });
        hp_ib_tr.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainTrActivity.class);
                startActivity(intent);
            }
        });
        hp_ib_ar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("http://www.enread.com/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
        hp_ib_mo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("http://www.tingroom.com/video/");
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                startActivity(intent);
            }
        });
    }

}