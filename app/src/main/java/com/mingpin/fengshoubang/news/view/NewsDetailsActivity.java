package com.mingpin.fengshoubang.news.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.mingpin.fengshoubang.R;
import com.mingpin.fengshoubang.base.activities.BaseBackActivity;
import com.mingpin.fengshoubang.news.presenter.NewsDetailPresenter;
import com.mingpin.fengshoubang.news.view.fragments.NewsDetailsFragment;
import com.mingpin.fengshoubang.widget.CommentBar;


public class NewsDetailsActivity extends BaseBackActivity{

    private static final String TAG = "NewsDetailsActivity";

    private LinearLayout layComment;
    private NewsDetailPresenter newsDetailPresenter;
    private NewsDetailsFragment newsDetailsFragment;
    private CommentBar mCommentBar;
    private String id; //新闻id

    private String commentText; //评论内容

    public static void show(Context context,String id){
        Intent intent = new Intent(context,NewsDetailsActivity.class);
        intent.putExtra("newsId",id);
        context.startActivity(intent);
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_news_details;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        id = intent.getStringExtra("newsId");
        newsDetailPresenter.loadNewsDetail(id);
    }

    @Override
    public void initWidget(){
        newsDetailsFragment = NewsDetailsFragment.newInstance();
        addFragment(R.id.lay_container,newsDetailsFragment );
        newsDetailPresenter = new NewsDetailPresenter(newsDetailsFragment);
        layComment = (LinearLayout) findViewById(R.id.ll_comment);
        mCommentBar = CommentBar.delegation(this,layComment);
        mCommentBar.setCommentHint(getString(R.string.pub_comment_hint));
        mCommentBar.getBottomSheet().getEditText().setHint(R.string.comment_hint);
        mCommentBar.getBottomSheet().getBtnCommit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentText = mCommentBar.getBottomSheet().getCommentText();
                newsDetailPresenter.addComment(id,"lVUnWbU14643200190EZ",commentText,"118.46","38.6");
                mCommentBar.getBottomSheet().hide();
            }
        });
    }
}
