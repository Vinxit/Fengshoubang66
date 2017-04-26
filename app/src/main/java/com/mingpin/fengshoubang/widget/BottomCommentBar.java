package com.mingpin.fengshoubang.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.mingpin.fengshoubang.R;

/**
 *底部弹出评论框
 * Created by Vinxit_HK on 2017/4/14.
 */

public class BottomCommentBar {
    private View mRootView;
    private Context mContext;
    private FrameLayout mFrameLayout;
    private EditText mEditText;
    private ImageButton mFaceView;
    private Button mBtnCommit;
    private BottomCommentBar(Context context){
        this.mContext = context;
    }
    public static BottomCommentBar delegation(Context context) {
        BottomCommentBar bar = new BottomCommentBar(context);
        bar.mRootView = LayoutInflater.from(context).inflate(R.layout.layout_input_comment_bar,null,false);
        bar.intView();
        return bar;
    }
    private void intView(){
        mFrameLayout = (FrameLayout) mRootView.findViewById(R.id.fl_face);
        mEditText = (EditText) mRootView.findViewById(R.id.et_comment);
        mFaceView = (ImageButton) mRootView.findViewById(R.id.ib_face);
        mBtnCommit = (Button) mRootView.findViewById(R.id.btn_comment);

    }
    public EditText getEditText() {
        return mEditText;
    }
}

