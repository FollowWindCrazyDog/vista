package com.example.a11633.vista.common.utils;
/**
 * Created by 11633 on 2018/4/14.
 */

import com.google.gson.Gson;

/**
 * @author 11633
 * @date 2018/4/14 17:27
 */
public class JsonUtils {
    public static <T> T toBean(String jsonData,Class<T> clazz){
        Gson gson = new Gson();
        T t = gson.fromJson(jsonData, clazz);
        return t;
    }
}
