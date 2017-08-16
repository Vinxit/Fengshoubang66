package com.mingpin.fengshoubang.news.view.fragments;


import android.view.View;
import android.widget.Toast;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.base.fragments.BaseFragment;
import com.mingpin.fengshoubang.bean.NewsDetail;
import com.mingpin.fengshoubang.news.NewsDetailContract;
import com.mingpin.fengshoubang.widget.MyWebView;

/**
 * Created by Administrator on 2017/5/3.
 */

public class NewsDetailsFragment extends BaseFragment implements NewsDetailContract.View{
    private static final String TAG = "NewsDetailsFragment";

    private NewsDetailContract.Presenter mPresenter;
    private MyWebView myWebView;
    private String newstitle;   //新闻标题
    private String hits;       //浏览量
    private String post_time;   //发布时间
    private String newscontent; //新闻内容
    private String classid; //新闻类别
    private String id; //新闻id

    public static NewsDetailsFragment newInstance() {

        return new NewsDetailsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_details;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        myWebView = (MyWebView) root.findViewById(R.id.wv_News);
    }

    @Override
    public void showNewsDetailContent(NewsDetail newsDetail) {
        newstitle = newsDetail.getNewstitle();
        post_time = newsDetail.getPost_time();
        hits = newsDetail.getHits();
        newscontent = newsDetail.getNewscontent();
        classid = newsDetail.getClassid();

        String html_head = "<span style=\"font-size: 18px;color: #333;font-weight: 600;line-height: 24px;\">"+newstitle+"</span>"
                +"<div style=\"width: 100%; clear: both; overflow: hidden; margin-bottom: 10px;\">"
                + "<div style=\"float: left; color: #999; font-size: 14px;\">"+"发布时间:"+post_time+"</div>"
                + "<div style=\"float: right; color: #999;font-size: 14px;\">"+"浏览量:"+hits+"</div>"
                +"</div>";

        String html = html_head + newscontent;
        myWebView.loadDetailDataAsync(html, new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    public void showConmentSuccess(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFavSuccess() {

    }

    @Override
    public void showFavError() {

    }


    @Override
    public void showConmentError() {

    }

    @Override
    public void showShareSuccess() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setPresenter(NewsDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
