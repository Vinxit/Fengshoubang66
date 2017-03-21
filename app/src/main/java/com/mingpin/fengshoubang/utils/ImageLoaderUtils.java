package com.mingpin.fengshoubang.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mingpin.fengshoubang.R;

/**
 * Description:图片加载工具类
 * Created by Administrator on 2017/3/21.
 */

public class ImageLoaderUtils {
    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail).crossFade().into(imageView);
    }

}
