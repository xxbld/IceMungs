package org.github.xxbld.icemungs.interactors.impl;

import android.content.Context;

import com.dev.sacot41.scviewpager.SCViewPagerAdapter;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.interactors.IWelcomeInteractor;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class WelcomeInteactorImpl implements IWelcomeInteractor {

    private static final int PAGE_NUM = 5;

    @Override
    public int getPageNum() {
        return PAGE_NUM;
    }

    @Override
    public int[] getDotsViewSelect() {
        return new int[]{R.drawable.dot_selected,R.drawable.dot_unselected};
    }

    @Override
    public SCViewPagerAdapter getViewPagerAdapter(Context context) {
        SCViewPagerAdapter viewPagerAdapter =new SCViewPagerAdapter(((BaseActivity)context).getSupportFragmentManager());
        viewPagerAdapter.setNumberOfPage(getPageNum());
        viewPagerAdapter.setFragmentBackgroundColor(R.color.md_green_400);
        return viewPagerAdapter;
    }
}
