package org.github.xxbld.icemungs.views;

import com.dev.sacot41.scviewpager.SCViewPagerAdapter;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IWelcomeView {

    /**
     * set translucent status bar and fitSystem false
     */
    void setTranslucentStatus();

    /**
     * init dotsView
     * @param resIdSelected
     * @param resIdUnSelected
     * @param pageNum
     */
    void initDotsView(int resIdSelected, int resIdUnSelected, int pageNum);

    /**
     * init viewPager
     * @param scViewPagerAdapter
     */
    void initViewPager(SCViewPagerAdapter scViewPagerAdapter);

    /**
     * go main Activity
     */
    void goLoginOrHome();
}
