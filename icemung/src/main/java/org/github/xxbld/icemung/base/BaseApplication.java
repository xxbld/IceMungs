package org.github.xxbld.icemung.base;

import android.app.Application;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.SharedPreUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/29.
 */
public abstract class BaseApplication extends Application {
    protected static String TAG = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        if (isButterKnifeDebug()) {
            ButterKnife.setDebug(true);
        }else {
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
    }

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
