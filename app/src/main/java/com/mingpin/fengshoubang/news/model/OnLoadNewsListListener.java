package com.mingpin.fengshoubang.news.model;

import com.mingpin.fengshoubang.news.bean.NewsListItem;

import java.util.List;

/**
 * Description : 新闻列表加载回调
 * Author : AstroGypsophila
 * Github  : https://github.com/AstroGypsophila
 * Date   : 2016/8/28
 */
public interface OnLoadNewsListListener {

    void onSuccess(List<NewsListItem> list);

    void onFailure(String msg, Exception e);
}