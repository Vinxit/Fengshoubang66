package com.mingpin.fengshoubang.news.model;

/**
 * Created by Administrator on 2017/3/21.
 */

public interface NewsModel {
    void loadNews(int type,int pageIndex,OnLoadNewsListListener listener);
    void loadNewsDetail(String id,OnloadNewsDetailListener  listener);
    void addComment(String newsid, String username, String content, String gpsx, String gpsy,OnaddCommentListener listener);
}
