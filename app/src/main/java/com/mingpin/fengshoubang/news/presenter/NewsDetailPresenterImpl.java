package com.mingpin.fengshoubang.news.presenter;

import android.content.Context;

import com.mingpin.fengshoubang.news.bean.NewsDetail;
import com.mingpin.fengshoubang.news.model.NewsModel;
import com.mingpin.fengshoubang.news.model.NewsModelImpl;
import com.mingpin.fengshoubang.news.model.OnloadNewsDetailListener;
import com.mingpin.fengshoubang.news.view.NewsDetailView;

/**
 * Created by Administrator on 2017/3/30.
 */

public class NewsDetailPresenterImpl implements NewsDetailPresenter ,OnloadNewsDetailListener{

    private Context context;
    private NewsDetailView newsDetailView;
    private NewsModel newsModel;
    public NewsDetailPresenterImpl(Context context, NewsDetailView newsDetailView){
        this.context = context;
        this.newsDetailView = newsDetailView;
        newsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String id) {
        newsDetailView.showProgress();
        newsModel.loadNewsDetail(id,this);
    }

    @Override
    public void onSuccess(NewsDetail newsDetail) {
        if(newsDetail != null){
            newsDetailView.showNewsDetailContent(newsDetail);
        }
        newsDetailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
