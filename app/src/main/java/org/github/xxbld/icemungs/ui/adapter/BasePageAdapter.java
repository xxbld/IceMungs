package org.github.xxbld.icemungs.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by xxbld on 2016/3/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public abstract class BasePageAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;

    public BasePageAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
