package com.mingpin.fengshoubang.news.view;

import com.mingpin.fengshoubang.news.bean.NewsListItem;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public interface NewsView {
    void showProgress();

    void addNews(List<NewsListItem> newsList);

    void hideProgress();

    void showLoadFailMsg();

}
