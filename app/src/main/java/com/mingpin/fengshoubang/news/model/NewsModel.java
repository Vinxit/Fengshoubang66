package com.mingpin.fengshoubang.news.model;

/**
 * Created by Administrator on 2017/3/21.
 */

public interface NewsModel {
    void loadNews(String url, OnLoadNewsListListener listener);

}
