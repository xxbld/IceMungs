package org.github.xxbld.icemungs.interactors;

import android.content.Context;

import com.dev.sacot41.scviewpager.SCViewPagerAdapter;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IWelcomeInteractor {
    /**
     * get viewpager page numbers
     * @return
     */
    int getPageNum();

    /**
     * get dots view selects
     * @return data[0] = selected; data[1] = unselected;
     */
    int[] getDotsViewSelect();

    /**
     * get inited pagerAdapter
     * @param context
     * @return
     */
    SCViewPagerAdapter getViewPagerAdapter(Context context);

    /**
     * get SCViewAnimations
     * @param context
     * @return
     */
//    List<SCViewAnimation> getViewsAnimations(Context context);
}
