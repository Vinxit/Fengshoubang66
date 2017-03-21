package com.mingpin.fengshoubang.news.presenter;

import com.mingpin.fengshoubang.news.NewsFragment;
import com.mingpin.fengshoubang.news.bean.NewsListItem;
import com.mingpin.fengshoubang.news.model.NewsModel;
import com.mingpin.fengshoubang.news.model.NewsModelImpl;
import com.mingpin.fengshoubang.news.model.OnLoadNewsListListener;
import com.mingpin.fengshoubang.news.view.NewsView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class NewsPresenterImpl implements NewsPresenter,OnLoadNewsListListener {
    private static final String TAG = "NewsPresenterImpl";

    private NewsView mNewsView;
    private NewsModel mNewsModel;

    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(final int type, final int pageIndex) {
        String url = getUrl(type, pageIndex);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0) {
            mNewsView.showProgress();
        }
        mNewsModel.loadNews(url, type, this);
    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
/*        switch (type) {
            case NewsFragment.NEWS_TYPE_HOT:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_PRODUCT:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_AT:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_VIDEO:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }*/
/*        sb.append("/").append(pageIndex).append(Urls.END_URL);*/
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsListItem> list) {

    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
