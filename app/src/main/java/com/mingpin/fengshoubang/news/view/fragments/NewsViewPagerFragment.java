package com.mingpin.fengshoubang.news.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.base.fragments.BaseViewPagerFragment;
import com.mingpin.fengshoubang.common.config.FsbApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsViewPagerFragment extends BaseViewPagerFragment {

    public static NewsViewPagerFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        NewsViewPagerFragment fragment = new NewsViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected PagerInfo[] getPagers() {
        return new PagerInfo[]{
                new PagerInfo(getString(R.string.news_hot),NewsListFragment.newInstance(FsbApi.NEWS_TYPE_HOT)),
                new PagerInfo(getString(R.string.news_product),NewsListFragment.newInstance(FsbApi.NEWS_TYPE_PRODUCT)),
                new PagerInfo(getString(R.string.news_at),NewsListFragment.newInstance(FsbApi.NEWS_TYPE_AT)),
                new PagerInfo(getString(R.string.news_video),NewsListFragment.newInstance(FsbApi.NEWS_TYPE_VIDEO)),

        };
    }
    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_news;
    }

}
