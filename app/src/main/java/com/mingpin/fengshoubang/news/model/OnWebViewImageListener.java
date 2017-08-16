package com.mingpin.fengshoubang.news.model;

/**
 * 监听webview上的图片
 * @author Vinxit_Hk@163.com
 * Created by Administrator on 2017/4/10.
 */

public interface OnWebViewImageListener {

    void showImagePreview(String bigImageUrl,String[] imgs);

    void showToast();
}
