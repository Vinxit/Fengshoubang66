package com.mingpin.fengshoubang.base.activities;

import android.support.v7.app.ActionBar;

/**
 * Created by Administrator on 2017/4/13.
 */

public abstract class BaseBackActivity extends BaseActivity {
    @Override
    protected void initWindow() {
        super.initWindow();
        //设置actionBar的返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(false);
        }
    }
    /**
     *fragment
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    /**
     * 按返回键 finish当前界面
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
