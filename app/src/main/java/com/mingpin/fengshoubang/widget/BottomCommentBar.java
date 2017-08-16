package com.mingpin.fengshoubang.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

    private static final String TAG ="BottomCommentBar";

    private View mRootView;
    private Context mContext;
    private FrameLayout mFrameLayout;
    private EditText mEditText;
    private ImageButton mFaceView;
    private Button mBtnCommit;
    private BottomDialog mDialog;
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
        //评论为空，不能点击
        mBtnCommit.setEnabled(false);

        mDialog = new BottomDialog(mContext,false);
        mDialog.setContentView(mRootView);
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mFrameLayout.setVisibility(View.GONE);
            }
        });
        //监听输入文本
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG,"TextLength"+s.toString().length());
                if (s.toString().length()>0){
                    mBtnCommit.setEnabled(true);
                }else{
                    mBtnCommit.setEnabled(false);
                }
            }
        });
    }

    public void show(String hint) {
        mDialog.show();
   /*     if (!"添加评论".equals(hint)) {
            mEditText.setHint(hint + " ");
        }*/
    }

    public void hide(){
        mDialog.hide();
    }

    public EditText getEditText() {
        return mEditText;
    }
    public String getCommentText() {
        return mEditText.getText().toString().trim();
    }
    public Button getBtnCommit() {
        return mBtnCommit;
    }
}

