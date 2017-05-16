package com.mingpin.fengshoubang.base.fragments;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.mingpin.fengshoubang.R;

/**
 * Created by Vinxit_HK on 2017/5/16.
 */

public abstract class BaseTitleFragment extends BaseFragment {

    Toolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_title;
    }

    @Override
    protected void onBindViewBefore(View root) {
        super.onBindViewBefore(root);
        ViewStub stub = (ViewStub) root.findViewById(R.id.lay_content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }
    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(getTitleRes());
    }
    //获取布局id
    protected abstract int getContentLayoutId();
    //设置标题文字
    protected abstract int getTitleRes();
}
