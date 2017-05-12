package com.mingpin.fengshoubang.news.widget;

import android.util.Log;
import android.view.View;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.base.fragments.BaseFragments;
import com.mingpin.fengshoubang.news.bean.NewsDetail;
import com.mingpin.fengshoubang.news.view.NewsDetailView;
import com.mingpin.fengshoubang.widget.MyWebView;

/**
 * Created by Administrator on 2017/5/3.
 */

public class NewsDetailsFragment extends BaseFragments implements NewsDetailView {
/*    @BindView(R.id.wv_News)MyWebView myWebView;*/
    private static final String TAG = "NewsDetailsFragment";
    private MyWebView myWebView;
    private String newstitle;   //新闻标题
    private String hits;       //浏览量
    private String post_time;   //发布时间
    private String newscontent; //新闻内容
    private String classid; //新闻类别
    public static NewsDetailsFragment newInstance(){
        return new NewsDetailsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_details;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        myWebView = (MyWebView)mRoot.findViewById(R.id.wv_News);
    }

    @Override
    protected void initData() {
        super.initData();
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
 /*       String content = "";
        if(classid.equals("5")){
            content = "<iframe src=\""+newscontent+"\" style=\"width:100%;height: 230px; frameborder:0;display:none;\" allowfullscreen >"+"</iframe>";
        }else{
            content = newscontent;
        }*/
        String html = html_head + newscontent;
        Log.i(TAG,"content"+html);
        myWebView.loadDetailDataAsync(html, new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
