package com.mingpin.fengshoubang.news.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.base.activities.BaseBackActivity;
import com.mingpin.fengshoubang.news.bean.NewsDetail;
import com.mingpin.fengshoubang.news.presenter.NewsDetailPresenter;
import com.mingpin.fengshoubang.news.presenter.NewsDetailPresenterImpl;
import com.mingpin.fengshoubang.news.view.NewsDetailView;
import com.mingpin.fengshoubang.widget.CommentBar;


public class NewsDetailsActivity extends BaseBackActivity implements NewsDetailView{

    private static final String TAG = "NewsDetailsActivity";
    private NewsDetail newsDetail;
    private NewsDetailPresenter newsDetailPresenter;
    private CommentBar mCommentBar;
    private LinearLayout linearLayout;
    private MyWebView myWebView;

    private String newstitle;   //新闻标题
    private String hits;       //浏览量
    private String post_time;   //发布时间
    private String newscontent; //新闻内容
    private String classid; //新闻类别

    public static void show(Context context,String id){
        Intent intent = new Intent(context,NewsDetailsActivity.class);
        intent.putExtra("newsId",id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = (LinearLayout) findViewById(R.id.activity_news_details);
        myWebView = (MyWebView) findViewById(R.id.wv_News);
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_news_details;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        String id = intent.getStringExtra("newsId");
        newsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
        newsDetailPresenter.loadNewsDetail(id);
    }
    public void initWidget(){
        LinearLayout layComment = (LinearLayout) findViewById(R.id.ll_comment);
        mCommentBar = CommentBar.delegation(getApplicationContext(),layComment);
        mCommentBar.setCommentHint(getString(R.string.pub_comment_hint));
        mCommentBar.getBottomSheet().getEditText().setHint(R.string.comment_hint);
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
