package com.mingpin.fengshoubang.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.news.bean.NewsDetail;
import com.mingpin.fengshoubang.news.presenter.NewsDetailPresenter;
import com.mingpin.fengshoubang.news.presenter.NewsDetailPresenterImpl;
import com.mingpin.fengshoubang.news.view.NewsDetailView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailView{

    private static final String TAG = "NewsDetailsActivity";
    private NewsDetail newsDetail;
    private NewsDetailPresenter newsDetailPresenter;
    private TextView textView;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Intent intent = getIntent();
        String id = intent.getStringExtra("newsId");
/*        webView = (WebView) findViewById(R.id.wv_News);
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webView.loadUrl("http://www.fengshoubang.cn/mobile/news.php?id="+id);*/
        //加载新闻详情数据
        newsDetailPresenter = new NewsDetailPresenterImpl(getApplication(), this);
        newsDetailPresenter.loadNewsDetail(id);

         textView = (TextView)findViewById(R.id.tv_title);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());//设置可以滚动
        textView.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页

    }

    @Override
    public void showNewsDetailContent(String newsDetailContent) {
        Document doc = Jsoup.parseBodyFragment(newsDetailContent);
        Element body = doc.body();
        Log.i(TAG,"showNewsDetailContent:"+doc);
        Log.i(TAG,"showNewsDetailContent:"+body);
/*        Html.fromHtml();*/
        textView.setText(newsDetailContent);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
