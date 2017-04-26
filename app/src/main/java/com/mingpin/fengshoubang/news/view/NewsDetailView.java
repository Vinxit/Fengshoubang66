package com.mingpin.fengshoubang.news.view;

import com.mingpin.fengshoubang.news.bean.NewsDetail;

/**
 * Created by Administrator on 2017/3/30.
 */

public interface NewsDetailView {
    void showNewsDetailContent(NewsDetail newsDetail);

    void showProgress();

    void hideProgress();
}
