package org.github.xxbld.icemungs.data;

import org.github.xxbld.icemung.utils.SharedPreUtil;

/**
 * Created by xxbld on 2016/5/12.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class Setting {
    public final static String APPLICATION_FIRST_IN = "firstIn";

    public static void putIsFirstIn(boolean isFirstIn) {
        SharedPreUtil.putBoolean(APPLICATION_FIRST_IN, isFirstIn);
    }

    public static boolean getIsFirstIn() {
        return SharedPreUtil.getBoolean(APPLICATION_FIRST_IN, false);
    }
}
