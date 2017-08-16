package com.mingpin.fengshoubang.common.config;

import com.mingpin.fengshoubang.common.utils.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 *  丰收邦接口
 * Created by Vinxit_HK on 2017/6/22.
 */

public class FsbApi {

    //服务器返回Json数据标志 result
    public static final String  RESULT= "result";
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    public static final String API = "http://www.fengshoubang.cn/api/appapi7.php";
    public static final String APP = "http://www.fengshoubang.cn/api/app7.php";
    public static final String IMG_URL= "http://www.fengshoubang.cn";

    public static final int NEWS_TYPE_HOT = 1;      //动态
    public static final int NEWS_TYPE_PRODUCT = 3;  //产品
    public static final int NEWS_TYPE_AT = 4;       //农技
    public static final int NEWS_TYPE_VIDEO = 5;    //视频
    /**
     * 获取新闻列表
     * @param type  新闻类型
     * @param pageIndex 请求页
     * @param callback
     */
    public static void getNewsList(int type,int pageIndex,OkHttpUtils.ResultCallback callback){
        OkHttpUtils.get(API+"?a=getNewsList&classid="+type+"&page="+pageIndex,callback);
    }

    /**
     * 获取新闻详情
     * @param id  新闻id
     * @param callback
     */
    public static void getNewsDetils(String id,OkHttpUtils.ResultCallback callback){
        OkHttpUtils.get(API+"?a=getNewsDetails&id="+id,callback);
    }

    /**
     * 提交新闻评论 以Json字符串格式提交
     * @param newsid    新闻id
     * @param username  用户名
     * @param content   评论内容
     * @param gpsx
     * @param gpsy
     * @param callback
     */
    public static void addComment(String newsid, String username, String content, String gpsx, String gpsy,OkHttpUtils.ResultCallback callback){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("newsid",newsid)
                    .put("username",username)
                    .put("content",content)
                    .put("gpsx",gpsx)
                    .put("gpsy",gpsy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        OkHttpUtils.post(APP+"?a=postNewsComment",body,callback);
    }
}
