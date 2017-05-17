package com.mingpin.fengshoubang;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.mingpin.fengshoubang.base.activities.BaseActivity;
import com.mingpin.fengshoubang.main.MainActivity;

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGHT = 2000; // 延迟2秒

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        //设置当前界面全屏，隐藏手机状态栏
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
    }

    @Override
    protected void initData() {
        super.initData();
        //设置启动页
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectTo();
            }
        },SPLASH_DISPLAY_LENGHT);
    }

    private void redirectTo() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
