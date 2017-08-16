package com.mingpin.fengshoubang.news;

import com.mingpin.fengshoubang.base.BasePresenter;
import com.mingpin.fengshoubang.base.BaseView;
import com.mingpin.fengshoubang.bean.NewsListItem;

import java.util.List;

/**
 * Created by Vinxit_HK on 2017/7/6.
 */

public interface NewsContract {

    interface View extends BaseView<Persenter>{
        void showProgress();

        void addNews(List<NewsListItem> newsList);

        void hideProgress();

        void showLoadFailMsg();

    }
    interface Persenter extends BasePresenter{
        void loadNews(int type, int page);
    }
}
