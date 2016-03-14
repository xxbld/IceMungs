package org.github.xxbld.icemung.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/4.
 */
public class GsonUtil {
    private GsonUtil() {
    }

    /**
     * Object to jsonStr
     *
     * @param object
     * @return
     */
    public static String createJsonStr(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * json to Bean
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * json to List (有点问题)
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> json2BeanList(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * json to map
     * @param json
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> json2Map(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
    }

    /**
     *json to list map
     * @param json
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> json2MapList(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
        }.getType());
    }
}
