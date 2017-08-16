package com.mingpin.fengshoubang.common.utils;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * Json转换工具类
 * Created by Administrator on 2017/3/23.
 */

public class JsonUtils {
    private static Gson mGson = new Gson();
    /**
     * 将json字符串转换为对象
     */
    public static <T>T deserialize(String json, Class<T> clz)throws JsonSyntaxException{

        return mGson.fromJson(json,clz);
    }

    /**
     * 将json对象转换为实体对象
     */
    public static <T> T deserialize(JsonObject json,Class<T> clz)throws JsonSyntaxException{
        return mGson.fromJson(json,clz);
    }
}
