package org.github.xxbld.icemung.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxbld on 2016/3/15
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：DataManager 各类数据相关单例集合
 */
public class DataManager {
    /**
     * instance collect
     */
    private static Map<String, Object> objMapSingleton = new HashMap<>();

    private DataManager() {
    }

    public static void registerSingleton(String key, Object instance) {
        if (!objMapSingleton.containsKey(key)) {
            objMapSingleton.put(key, instance);
        }
    }

    public static Object getSingleton(String key) {
        return objMapSingleton.get(key);
    }

    public static int singletonCount() {
        return objMapSingleton.size();
    }
}
