package com.mingpin.fengshoubang.news.model;

/**
 * Created by Vinxit_HK on 2017/6/29.
 */

public interface OnaddCommentListener {
    void onSuccess(String msg);

    void onFailure(Exception e);
}
