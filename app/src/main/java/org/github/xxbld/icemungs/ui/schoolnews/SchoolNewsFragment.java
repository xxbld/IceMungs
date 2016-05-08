package org.github.xxbld.icemungs.ui.schoolnews;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.github.xxbld.icemungs.ui.adapter.BasePageAdapter;
import org.github.xxbld.icemungs.ui.fragment.BaseUseMainTabFragment;

import java.util.List;

/**
 * Created by xxbld on 2016/5/6.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolNewsFragment extends BaseUseMainTabFragment {

    public SchoolNewsFragment() {
    }

    /**
     * @param fragTitleNameResId
     * @return
     */
    public static SchoolNewsFragment newInstance(int fragTitleNameResId) {
        SchoolNewsFragment useTabFragment = new SchoolNewsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(FRAG_TITLE_NAME_ID, fragTitleNameResId);
        useTabFragment.setArguments(arguments);
        return useTabFragment;
    }

    @Override
    protected BasePageAdapter getBasePageAdapter(List<String> tabTitles, final List<Fragment> fragments) {

        return new BasePageAdapter(getChildFragmentManager(), tabTitles) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
            setAdapter();
    }
}
