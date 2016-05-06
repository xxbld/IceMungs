package org.github.xxbld.icemungs.ui.verbose;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.github.xxbld.icemungs.ui.base.BasePageAdapter;
import org.github.xxbld.icemungs.ui.fragment.BaseUseMainTabFragment;

import java.util.List;

/**
 * Created by xxbld on 2016/5/6.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class VerboseFragment extends BaseUseMainTabFragment {

    public VerboseFragment() {
    }

    /**
     * @param fragTitleNameResId
     * @return
     */
    public static VerboseFragment newInstance(int fragTitleNameResId) {
        VerboseFragment useTabFragment = new VerboseFragment();
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
}
