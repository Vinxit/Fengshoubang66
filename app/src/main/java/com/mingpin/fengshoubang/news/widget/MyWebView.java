package com.mingpin.fengshoubang.news.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mingpin.fengshoubang.news.model.OnWebViewImageListener;
import com.mingpin.fengshoubang.widget.ImageGalleryActivity;



/**
 * Created by Administrator on 2017/4/5.
 */

public class MyWebView extends WebView {

    private static String TAG = "MyWebView";
    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyWebView(Context context) {
        super(context);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

 /*   @Override
    public void loadData(String data, String mimeType, String encoding) {
        super.loadData(data, mimeType, encoding);
    }*/

    public void init(){
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);        //设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("utf-8");//设置默认编码格式
        settings.setUseWideViewPort(true);
        //API 17以上的版本
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            addJavascriptInterface(new OnWebViewImageListener() {
                @Override
                @JavascriptInterface
                public void showImagePreview(String bigImageUrl) {
                    if (bigImageUrl != null){
                        /*ImageGalleryActivity.show(getContext(),bigImageUrl);*/
                        Intent intent = new Intent(getContext(), ImageGalleryActivity.class);
                        intent.putExtra("image",bigImageUrl);
                        getContext().startActivity(intent);
                    }
                    Toast.makeText(getContext(),"haha",Toast.LENGTH_LONG);
                }
            },"mWebViewImageListener");
        }
    }
    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
         loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.mWebViewImageListener.showImagePreview(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }
    @Override
    public void destroy() {
        setWebViewClient(null);

        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(false);

        removeJavascriptInterface("mWebViewImageListener");
        removeAllViewsInLayout();

        removeAllViews();
        super.destroy();
    }
    public void loadDetailDataAsync(final String content, Runnable finishCallback) {
        this.setWebViewClient(new OWebClient(finishCallback));
        Context context = getContext();
        if (context != null && context instanceof Activity) {
            final Activity activity = (Activity) context;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String body = setupWebContent(content, true);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadDataWithBaseURL("", body, "text/html", "UTF-8", "");
                        }
                    });
                }
            }).start();

        }
    }
    private static String setupWebContent(String content, boolean isShowImagePreview) {
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(content.trim()))
            return "";

        Log.i(TAG, "setupWebContent: "+content);
        return String.format(
                "<!DOCTYPE html>"
                        + "<html>"
                        + "<body>"
                        + "%s"
                        + "</body>"
                        + "</html>"
                , content);
    }
    private class OWebClient extends WebViewClient implements Runnable{
        private Runnable mFinishCallback;
        private boolean mDone = false;
        OWebClient(Runnable finishCallback){
            super();
            mFinishCallback = finishCallback;
        }

        @Override
        public void run() {
            if (!mDone) {
                mDone = true;
                if (mFinishCallback != null)
                    mFinishCallback.run();
            }
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
            mDone = false;
            //webview加载三秒后 强制完成
            view.postDelayed(this,3000);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            addImageClickListner();
            run();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.cancel();
        }
    }
}
