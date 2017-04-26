package com.mingpin.fengshoubang.news.model;

import com.mingpin.fengshoubang.news.bean.NewsDetail;

/**
 * Created by Administrator on 2017/3/30.
 */

public interface OnloadNewsDetailListener {
    void onSuccess(NewsDetail newsDetail);

    void onFailure(String msg, Exception e);
}
