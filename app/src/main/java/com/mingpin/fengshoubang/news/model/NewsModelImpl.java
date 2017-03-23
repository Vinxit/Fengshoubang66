package com.mingpin.fengshoubang.news.model;

import com.mingpin.fengshoubang.utils.OkHttpUtils;

/**
 * 新闻业务处理类，请求数据
 * Created by Administrator on 2017/3/21.
 */

public class NewsModelImpl implements NewsModel {
    /**
     * 加载新闻列表
     * @param url
     * @param type
     * @param listener
     */
    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        };
    }
}
