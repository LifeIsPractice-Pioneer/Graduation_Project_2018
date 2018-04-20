package com.example.pioneer.vocabulary_app_project.util;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Pioneer on 2018/1/27.
 */

public class MarqueTextView extends android.support.v7.widget.AppCompatTextView {
    public MarqueTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MarqueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextView(Context context) {
        super(context);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
