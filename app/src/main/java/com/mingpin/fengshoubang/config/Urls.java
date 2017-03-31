package com.mingpin.fengshoubang.config;

/**
 * Description : 接口API的URL
 * Author : huangkun
 */

public class Urls {
    //页面数据条数
    public static final int PAGE_SIZE = 5;
    //服务器返回Json数据标志 result
    public static final String  RESULT= "result";

    public static final String API = "http://www.fengshoubang.cn/api/appapi5.php";
    public static final String APP = "http://www.fengshoubang.cn/api/app5.php";
    public static final String IMG_URL= "http://www.fengshoubang.cn";

    /**资讯版块Url getNewsList
     * classid 分别为 1，3，4，5
     * */
    public static final String NEWS_HOT_URL = API + "?a=getNewsList&classid=1&page=";
    public static final String NEWS_PRODUCT_URL = API + "?a=getNewsList&classid=3&page=";
    public static final String NEWS_AT_URL = API + "?a=getNewsList&classid=4&page=";
    public static final String NEWS_VIDEO_URL = API + "?a=getNewsList&classid=5&page=";
    /**
     * 新闻详情
     */
    public static final String NEWS_DETAIL = API + "?a=getNewsDetails&id=";
}
