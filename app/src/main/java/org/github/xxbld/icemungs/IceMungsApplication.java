package org.github.xxbld.icemungs;

import org.github.xxbld.icemung.base.BaseApplication;

import cn.bmob.v3.Bmob;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class IceMungsApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bmob SDK
        Bmob.initialize(this, "468e2678c4bff9cbf234f74f15731aad");
    }

    @Override
    protected boolean isInitSharedPreUtil() {
        return true;
    }

    @Override
    protected boolean isButterKnifeDebug() {
        return false;
    }

    @Override
    protected boolean isNeedMLog() {
        return BuildConfig.DEBUG;
    }

    @Override
    protected String getCommonTag() {
        return "IceMungs";
    }
}
