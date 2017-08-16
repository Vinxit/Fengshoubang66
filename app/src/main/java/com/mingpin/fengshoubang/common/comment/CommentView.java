package com.mingpin.fengshoubang.common.comment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/5/3.
 */

public class CommentView extends LinearLayout implements View.OnClickListener{
    public CommentView(Context context) {
        super(context);
        init();
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setOrientation(VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(getContext());
    }

    @Override
    public void onClick(View v) {

    }
}
