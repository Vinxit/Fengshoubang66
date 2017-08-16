package com.mingpin.fengshoubang.common.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.mingpin.fengshoubang.R;

/**
 * Created by Vinxit_HK on 2017/6/27.
 */

public class EmptyLayout extends LinearLayout {
    public EmptyLayout(Context context) {
        super(context);

        init();
    }

    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_error_layout,this,false);
        addView(view);
    }
}
