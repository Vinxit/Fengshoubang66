package com.mingpin.fengshoubang.news.presenter;

import com.mingpin.fengshoubang.bean.NewsDetail;
import com.mingpin.fengshoubang.news.NewsDetailContract;
import com.mingpin.fengshoubang.news.model.NewsModel;
import com.mingpin.fengshoubang.news.model.NewsModelImpl;
import com.mingpin.fengshoubang.news.model.OnaddCommentListener;
import com.mingpin.fengshoubang.news.model.OnloadNewsDetailListener;

/**
 * Created by Administrator on 2017/3/30.
 */

public class NewsDetailPresenter implements NewsDetailContract.Presenter{

    private NewsDetailContract.View newsDetailView;
    private NewsModel newsModel;
    public NewsDetailPresenter(NewsDetailContract.View  newsDetailView){
        this.newsDetailView = newsDetailView;
        newsModel = new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(final String id) {
        newsDetailView.showProgress();
        newsModel.loadNewsDetail(id, new OnloadNewsDetailListener() {
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
        });
    }


    @Override
    public void addComment(String newsid, String username, String content, String gpsx, String gpsy) {
        newsModel.addComment(newsid, username, content, gpsx, gpsy, new OnaddCommentListener() {
            @Override
            public void onSuccess(String msg) {
                newsDetailView.showConmentSuccess(msg);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void favNews(String newsid, String username) {
    }
}
