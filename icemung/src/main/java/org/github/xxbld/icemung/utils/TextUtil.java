package org.github.xxbld.icemung.utils;

/**
 * Created by Administrator on 2016/1/10.
 */
public class TextUtil {
    /**
     * is String empty
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
