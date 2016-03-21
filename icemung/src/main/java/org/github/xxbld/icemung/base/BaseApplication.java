package org.github.xxbld.icemung.base;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import org.github.xxbld.icemung.rxbus.RxBus;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.SharedPreUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/29.
 */
public abstract class BaseApplication extends Application {
    protected static String TAG = null;

    /**
     * 在系统初始化时生成一个util实例 避免多次检测同步
     */
    private static SharedPreUtil sSharedPreHelper;
    private static RxBus sBus;
    private static OkHttpUtils sOkHttpHelper;

    public final static String RXBUS_INSTANCE = "RxBus";
    public final static String SP_INSTANCE = "SharedPreferencesHelper";
    public final static String OKHTTP_INSTANCE = "OkHttpHelper";
//    public final static String DB_INSTANCE = "DataBaseHelper";

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        if (isButterKnifeDebug()) {
            ButterKnife.setDebug(true);
        } else {
            ButterKnife.setDebug(false);
        }
        if (isNeedMLog()) {
            MLog.enableLog();
            if (getCommonTag() != null) {
                MLog.setCommonTag(getCommonTag());
            }
        } else {
            MLog.disableLog();
        }
        if (isInitSharedPreUtil()) {
            SharedPreUtil.getInstance().init(this.getApplicationContext());
        }

        if (isInitSharedPreUtil()) {
            sSharedPreHelper = SharedPreUtil.getInstance();
        }
        sBus = RxBus.getInstance();
        sOkHttpHelper = OkHttpUtils.getInstance();

        registerSingleton();
        registerYourSingletons();
    }

    /**
     * 注册单例到data manager
     */
    private void registerSingleton() {
        if (isInitSharedPreUtil()) {
            DataManager.registerSingleton(SP_INSTANCE, sSharedPreHelper);
        }
        DataManager.registerSingleton(RXBUS_INSTANCE, sBus);
        DataManager.registerSingleton(OKHTTP_INSTANCE, sOkHttpHelper);
    }

//    /**
//     * 注册网络监听 Receiver
//     */
//    protected abstract void registerNetWorkReceiver();

    /**
     * DataManager.registerSingleton()
     *
     * @return
     */
    protected abstract void registerYourSingletons();

    /**
     * is need init SharedPreUtil
     *
     * @return
     */
    protected abstract boolean isInitSharedPreUtil();

    /**
     * is open ButterKnifeDebug mode
     *
     * @return
     */
    protected abstract boolean isButterKnifeDebug();

    /**
     * is open MLog's debug
     *
     * @return
     */
    protected abstract boolean isNeedMLog();

    /**
     * get MLog's CommonTag
     *
     * @return
     */
    protected abstract String getCommonTag();
}
