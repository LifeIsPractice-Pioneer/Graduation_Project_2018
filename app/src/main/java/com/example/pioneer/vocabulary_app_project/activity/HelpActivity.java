package com.example.pioneer.vocabulary_app_project.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pioneer.vocabulary_app_project.R;

public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.showabout);
        TextView tv = (TextView) this.findViewById(R.id.about_title);
        tv.setText("帮助");
        TextView words = (TextView) this.findViewById(R.id.about_words);
        words.setText(R.string.words);
        TextView abs = (TextView) this.findViewById(R.id.about_abstract);
        abs.setText(R.string.abs);
        TextView learn = (TextView) this.findViewById(R.id.about_learn);
        learn.setText(R.string.learn);
        TextView review = (TextView) this.findViewById(R.id.about_review);
        review.setText(R.string.review);
        TextView test = (TextView) this.findViewById(R.id.about_test);
        test.setText(R.string.test);
        TextView attetion = (TextView) this.findViewById(R.id.about_attention);
        attetion.setText(R.string.attetion);
    }

}
