package com.mingpin.fengshoubang.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mingpin.fengshoubang.R;

/**
 * desc:详情页输入框
 * Created by Vinxit_HK
 * on 2017/4/16.
 */

public class CommentBar {
    private Context mContext;
    private View mRootView;
    private FrameLayout mFrameLayout;
    private ViewGroup mParent;
    private ImageButton mCommentView;
    private ImageButton mShareView;
    private TextView mCommentText;
    private BottomCommentBar mDelegation;
    private LinearLayout mCommentLayout;

    private CommentBar(Context context) {
        this.mContext = context;
    }

    public static CommentBar delegation(Context context, ViewGroup parent) {
        CommentBar bar = new CommentBar(context);
        bar.mRootView = LayoutInflater.from(context).inflate(R.layout.layout_comment_bar, parent, false);
        bar.mParent = parent;
        bar.mDelegation = BottomCommentBar.delegation(context);
        bar.mParent.addView(bar.mRootView);
        bar.initView();
        return bar;
    }
    private void initView() {
        //((CoordinatorLayout.LayoutParams) mRootView.getLayoutParams()).setBehavior(new FloatingAutoHideDownBehavior());
        mCommentView = (ImageButton) mRootView.findViewById(R.id.ib_comment);
        mShareView = (ImageButton) mRootView.findViewById(R.id.ib_share);
        mCommentText = (TextView) mRootView.findViewById(R.id.tv_comment);
        mCommentLayout = (LinearLayout) mRootView.findViewById(R.id.ll_comment);
        mCommentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mDelegation.show(mCommentText.getHint().toString());
            }
        });
    }
    public BottomCommentBar getBottomSheet() {
        return mDelegation;
    }
    public void setCommentHint(String text) {
        mCommentText.setHint(text);
    }

}
