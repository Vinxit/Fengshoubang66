package com.mingpin.fengshoubang.news;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mingpin.fengshoubang.common.utils.JsonUtils;
import com.mingpin.fengshoubang.config.Urls;
import com.mingpin.fengshoubang.news.bean.NewsDetail;
import com.mingpin.fengshoubang.news.bean.NewsListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class NewsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为新闻列表对象
     * @param res
     * @return
     */
    public static List<NewsListItem> readJsonNewsBeans(String res) {
        List<NewsListItem> beans = new ArrayList<NewsListItem>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(Urls.RESULT);
            if(jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }

                if (!jo.has("imgextra")) {
                    NewsListItem news = JsonUtils.deserialize(jo, NewsListItem.class);
                    beans.add(news);

                }
            }
            System.out.println(beans);
        } catch (Exception e) {
            Log.e(TAG, "readJsonNewsBeans error" );
        }
        return beans;
    }

    public static NewsDetail readJsonNewsDetailBeans(String res) {
        NewsDetail newsDetail = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(Urls.RESULT);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            JsonObject jo = jsonArray.get(0).getAsJsonObject();
            if(jsonElement == null) {
                return null;
            }
            newsDetail = JsonUtils.deserialize(jo, NewsDetail.class);
        } catch (Exception e) {
        }
        return newsDetail;
    }
}
