package org.github.xxbld.icemung.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/1/5.
 */
public class SharedPreUtil {

    private final static String TAG = SharedPreUtil.class.getSimpleName();
    private static SharedPreUtil mInstance = null;

    private final static String DEFAULT_SP_NAME = "config";
    private static String CURRENT_SP_NAME = "config";

//    /**
//     * 存储所有的本应用的SpFiless name
//     * <p>
//     *     后面再添加 获取应用内sharedPreferences所有文件名
//     * </p>
//     */
//    private static List<String> SpFiles=new ArrayList<>();

    private static boolean isInited = false;
    /**
     * 操作当前的xml
     */
    private static SharedPreferences sp;
    private static Context mContext;

    private SharedPreUtil() {
    }

    /**
     * get SharedPreUtil instance
     *
     * @return
     */
    public static SharedPreUtil getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * init SharedPreUtil you'd better provide a application context
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        if (sp == null) {
            sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
            CURRENT_SP_NAME = DEFAULT_SP_NAME;
        }
        isInited = true;
    }

    /**
     * 获取当前操作的SharedPreferences xml name
     *
     * @return
     */
    public String getCurrentSpName() {
        return CURRENT_SP_NAME;
    }

    /**
     * 当前操作的SharedPreferences xml 是否是默认的“config.xml”
     *
     * @return
     */
    public boolean isDefaultSpName() {
        return DEFAULT_SP_NAME.equals(CURRENT_SP_NAME);
    }

    /**
     * is init ed SharedPreUtil
     *
     * @return
     */
    public boolean isInited() {
        return isInited;
    }

    /**
     * 切换操作的xml
     *
     * @param xmlName xml name Mode is Context.MODE_PRIVATE
     */
    public void switchSharedPreference(String xmlName) {
        this.switchSharedPreference(xmlName, Context.MODE_PRIVATE);
    }

    /**
     * 切换操作的xml
     *
     * @param xmlName
     * @param mode    Context.MODE_XXX
     */
    public void switchSharedPreference(String xmlName, int mode) {
        if (isInited()) {
            if (!xmlName.equals(CURRENT_SP_NAME)) {
                sp = mContext.getSharedPreferences(xmlName, mode);
                CURRENT_SP_NAME = xmlName;
            }
        } else {
            throw new ExceptionInInitializerError("please init instance");
        }
    }

    //=========================基本方法(皆对当前SP)===================================

    /**
     * 清空当前 xml
     */
    public void clearCurrentSp() {
        sp.edit().clear().commit();
    }

    /**
     * 清空指定的 xml
     *
     * @param xmlName
     */
    public void clearTargetSp(String xmlName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(xmlName, 0);
        sharedPreferences.edit().clear().commit();
    }

    /**
     * put boolean
     *
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * put int
     *
     * @param key
     * @param value
     */
    public static void putInt(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    /**
     * put string
     *
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    /**
     * put long
     *
     * @param key
     * @param value
     */
    public static void putLong(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    /**
     * put float
     *
     * @param key
     * @param value
     */
    public static void putFloat(String key, float value) {
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * get boolean with default value = false
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * get boolean
     *
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    /**
     * get int with default value = -1
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * get int
     *
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    /**
     * get string with default value = null
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * get string
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * get long with default value = 0
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return getLong(key, 0);
    }

    /**
     * get long
     *
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    /**
     * get float with default value = 0.0f
     *
     * @param key
     * @return
     */
    public static float getFloat(String key) {
        return getFloat(key, 0.0f);
    }

    /**
     * get float
     *
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(String key, float defValue) {
        return sp.getFloat(key, defValue);
    }
}
