package com.mingpin.fengshoubang.news.presenter;

import com.mingpin.fengshoubang.bean.NewsListItem;
import com.mingpin.fengshoubang.news.NewsContract;
import com.mingpin.fengshoubang.news.model.NewsModel;
import com.mingpin.fengshoubang.news.model.NewsModelImpl;
import com.mingpin.fengshoubang.news.model.OnLoadNewsListListener;

import java.util.List;
/**
 * Created by Administrator on 2017/3/21.
 */

public class NewsPresenter implements NewsContract.Persenter {
    private static final String TAG = "NewsPresenter";

    private NewsContract.View mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenter(NewsContract.View newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new NewsModelImpl();
    }

    /**
     *  加载新闻
     * @param type
     * @param page
     */
    @Override
    public void loadNews(int type, int page) {
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(page == 0) {
            mNewsView.showProgress();
        }
        mNewsModel.loadNews(type,page, new OnLoadNewsListListener() {
            @Override
            public void onSuccess(List<NewsListItem> list) {
                mNewsView.hideProgress();
                mNewsView.addNews(list);
            }

            @Override
            public void onFailure(String msg, Exception e) {
                mNewsView.hideProgress();
                mNewsView.showLoadFailMsg();
            }
        });

    }
}
