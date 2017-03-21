package com.mingpin.fengshoubang.news.bean;

import java.io.Serializable;

/**
 * Created by huangkun on 2017/3/20.
 */

public class NewsListItem implements Serializable{
    private String classid;   //新闻类别id
    private String newstitle; //新闻标题
    private String img;       //新闻图片
    private String hits;      //浏览量
    private int c_num;        //评论数
    private String id;        //新闻id

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getC_num() {
        return c_num;
    }

    public void setC_num(int c_num) {
        this.c_num = c_num;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }
}
