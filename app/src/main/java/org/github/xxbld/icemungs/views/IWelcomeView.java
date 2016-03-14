package org.github.xxbld.icemungs.views;

import org.github.xxbld.icemung.base.mvp.IMvpView;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IWelcomeView extends IMvpView {

    /**
     * init dotsView
     *
     * @param resIdSelected
     * @param resIdUnSelected
     * @param pageNum
     */
    void initDotsView(int resIdSelected, int resIdUnSelected, int pageNum);

    /**
     * init viewPager
     *
     * @param pageNum
     */
    void initViewPager(int pageNum);

    /**
     * go login or home Activity
     */
    void goLoginOrHome();
}
