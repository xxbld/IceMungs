package org.github.xxbld.icemungs.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.github.xxbld.icemungs.ui.adapter.BasePageAdapter;

import java.util.List;

/**
 * Created by xxbld on 2016/5/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class UseTabFragmentAdapter extends BasePageAdapter {

    List<String> mTitles;
    List<Fragment> mFragments;

    public UseTabFragmentAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm, titles);
        this.mFragments = fragments;
        this.mTitles = titles;
    }

    /*解决notifyDataSetChanged()界面不刷新问题*/
    @Override
    public int getItemPosition(Object object) {
//        if (object instanceof UseTabFragment) {
//            return POSITION_NONE;
//        }
//        if (object.getClass().getName().equals(UseTabFragment.class.getName())) {
//            return POSITION_NONE;
//        }
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
