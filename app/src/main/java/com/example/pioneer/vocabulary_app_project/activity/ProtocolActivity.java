package com.example.pioneer.vocabulary_app_project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.pioneer.vocabulary_app_project.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Pioneer on 2018/1/27.
 */

public class ProtocolActivity extends AppCompatActivity {
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);
        tv = (TextView) findViewById(R.id.protocol_tv_content);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        read();
    }

    public void read() {
        //tv.setSelected(true);
        StringBuffer sb = new StringBuffer();
        File file = new File("/sdcard/Download/protocol.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null && line.length() != 0) {
            sb.append(line);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.setText(sb.toString());
    }
}
