package com.mingpin.fengshoubang.news.presenter;

import com.mingpin.fengshoubang.config.Urls;
import com.mingpin.fengshoubang.news.bean.NewsListItem;
import com.mingpin.fengshoubang.news.model.NewsModel;
import com.mingpin.fengshoubang.news.model.NewsModelImpl;
import com.mingpin.fengshoubang.news.model.OnLoadNewsListListener;
import com.mingpin.fengshoubang.news.view.NewsView;
import com.mingpin.fengshoubang.news.widget.NewsFragment;

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

    /**
     * 加载新闻
     * @param type
     * @param pageIndex
     */
    @Override
    public void loadNews(final int type, final int pageIndex) {
        String url = getUrl(type, pageIndex);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0) {
            mNewsView.showProgress();
        }
        mNewsModel.loadNews(url, this);
    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_HOT:
                sb.append(Urls.NEWS_HOT_URL);
                break;
            case NewsFragment.NEWS_TYPE_PRODUCT:
                sb.append(Urls.NEWS_PRODUCT_URL);
                break;
            case NewsFragment.NEWS_TYPE_AT:
                sb.append(Urls.NEWS_AT_URL);
                break;
            case NewsFragment.NEWS_TYPE_VIDEO:
                sb.append(Urls.NEWS_VIDEO_URL);
                break;
            default:
                sb.append(Urls.NEWS_HOT_URL);
                break;
        }
        sb.append(pageIndex);
        return sb.toString();
    }

    /**
     * 请求成功的回调
     * @param list
     */
    @Override
    public void onSuccess(List<NewsListItem> list) {
        mNewsView.hideProgress();
        mNewsView.addNews(list);
    }

    /**
     * 请求失败的回调
     * @param msg
     * @param e
     */
    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}
