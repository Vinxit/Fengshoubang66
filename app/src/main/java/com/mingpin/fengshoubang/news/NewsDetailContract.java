package com.mingpin.fengshoubang.news;

import com.mingpin.fengshoubang.base.BasePresenter;
import com.mingpin.fengshoubang.base.BaseView;
import com.mingpin.fengshoubang.bean.NewsDetail;

/**
 * Created by Vinxit_HK on 2017/7/6.
 */

public interface NewsDetailContract {

    interface EmptyView{
    }


    interface View extends BaseView<Presenter> {
        void showNewsDetailContent(NewsDetail newsDetail);
        void showConmentSuccess(String msg);
        void showFavSuccess();
        void showFavError();
        void showConmentError();
        void showShareSuccess();
        void showProgress();
        void hideProgress();
    }

    interface Presenter extends BasePresenter {

        void loadNewsDetail(String id);
        void addComment(
                String newsid,
                String username,
                String content,
                String gpsx,
                String gpsy
        );
        void favNews(
                String newsid,
                String username
        );
    }
}
