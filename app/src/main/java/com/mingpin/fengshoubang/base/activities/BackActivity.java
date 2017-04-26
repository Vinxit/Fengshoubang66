package com.mingpin.fengshoubang.base.activities;

import android.support.v7.widget.Toolbar;

/**
 * 带回退按钮
 * Created by Administrator on 2017/4/20.
 */

public abstract class BackActivity extends BaseActivity {
    protected Toolbar toolbar;

    @Override
    protected void initWindow() {
        super.initWindow();
/*        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null){
                // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
                actionBar.setDisplayHomeAsUpEnabled(true);

                actionBar.setHomeButtonEnabled(false);
            }
        }*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
