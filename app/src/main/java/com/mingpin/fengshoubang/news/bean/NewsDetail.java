package com.mingpin.fengshoubang.news.bean;

/**
 * Created by Administrator on 2017/3/20.
 */

public class NewsDetail {
    private String classid;     //新闻类别
    private String newstitle;   //新闻标题
    private String nickname;    //作者
    private String post_time;   //发布时间
    private String id;          //文章id
    private String isZambia;    //是否点赞
    private String iscollect;   //是否收藏
    private String hits;        //浏览量
    private String source;      //来源
    private String img;         //图片
    private String newscontent; //新闻内容

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsZambia() {
        return isZambia;
    }

    public void setIsZambia(String isZambia) {
        this.isZambia = isZambia;
    }

    public String getIscollect() {
        return iscollect;
    }

    public void setIscollect(String iscollect) {
        this.iscollect = iscollect;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getNewstitle() {
        return newstitle;

    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getClassid() {

        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }
}
