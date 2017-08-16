package com.mingpin.fengshoubang.news.model;

import android.util.Log;

import com.mingpin.fengshoubang.bean.NewsDetail;
import com.mingpin.fengshoubang.bean.NewsListItem;
import com.mingpin.fengshoubang.bean.ResultBean;
import com.mingpin.fengshoubang.common.config.FsbApi;
import com.mingpin.fengshoubang.common.utils.JsonUtils;
import com.mingpin.fengshoubang.common.utils.OkHttpUtils;
import com.mingpin.fengshoubang.news.NewsJsonUtils;

import java.util.List;

/**
 * 新闻业务处理类，请求数据
 * Created by Administrator on 2017/3/21.
 */

public class NewsModelImpl implements NewsModel {
    private static final String TAG = "NewsModelImpl";
    /**
     * 加载新闻列表
     * @param listener
     */
    @Override
    public void loadNews(final int type,final int pageIndex, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsListItem> newsListItems = NewsJsonUtils.readJsonNewsBeans(response);
                Log.i(TAG, "数据: "+newsListItems.get(0).getId());
                listener.onSuccess(newsListItems);
            }

            @Override
            public void onFailure(Exception e) {
            }
        };
        FsbApi.getNewsList(type,pageIndex,loadNewsCallback);
    }

    /**
     * 加载新闻详情
     */
    @Override
    public void loadNewsDetail(final String id, final OnloadNewsDetailListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                NewsDetail newsDetail = NewsJsonUtils.readJsonNewsDetailBeans(response);
                Log.i(TAG, "onSuccess: "+newsDetail);
                listener.onSuccess(newsDetail);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
        FsbApi.getNewsDetils(id,loadNewsCallback);
    }

    @Override
    public void addComment(String newsid, String username, String content, String gpsx, String gpsy, final OnaddCommentListener listener) {
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>(){
            @Override
            public void onSuccess(String response) {

                ResultBean resultBean = JsonUtils.deserialize(response,ResultBean.class);
                Log.i(TAG, "ResultBean-onSuccess: "+resultBean.getResult().getMessage());
                listener.onSuccess(resultBean.getResult().getMessage());
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure(e);
            }
        };
        FsbApi.addComment(newsid,username,content,gpsx,gpsy,callback);
    }
}
