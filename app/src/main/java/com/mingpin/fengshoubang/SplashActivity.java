package com.mingpin.fengshoubang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 2000; // 延迟2秒
    OkHttpClient mOkHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置当前界面全屏，隐藏手机状态栏
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);

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
        getAsynHttp();
        finish();
    }
    private void getAsynHttp() {
   /*     mOkHttpClient=new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.fengshoubang.cn/api/appapi5.php?a=getNewsList&classid=1&page=1");
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call mcall= mOkHttpClient.newCall(request);
        Response response = mcall.execute();*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.fengshoubang.cn/api/appapi5.php?a=getNewsList&classid=1&page=1";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    okhttp3.Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {

                        Log.i("123", response.body().string());
                    } else {
                        Log.i("456", "okHttp is request error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
 /*       mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                    Log.i("wangshu", "cache---" + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Log.i("wangshu", "network---0" + str);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/
    }
}
