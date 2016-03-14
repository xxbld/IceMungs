package org.github.xxbld.icemungs.views;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface ISplashView {

    /**
     * 设置状态栏为半透明，fit，，为false
     */
    void setTranslucentStatus();

    /**
     * 初始化views 设置
     * @param copyright
     * @param versionName
     * @param bgResId
     */
    void initViews(String copyright, String versionName, int bgResId);

    /**
     * go welcome | home
     */
    void goHomePage();
}
