package org.github.xxbld.icemungs.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.presenters.FragUseMainTabPresenter;
import org.github.xxbld.icemungs.ui.base.BaseFragment;
import org.github.xxbld.icemungs.ui.base.BasePageAdapter;
import org.github.xxbld.icemungs.views.IFragUseMainTabView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xxbld on 2016/4/28.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 侧边的 Fragment 并使用tab
 */
public abstract class BaseUseMainTabFragment extends BaseFragment implements IFragUseMainTabView {

    //    private static final String FRAG_USE_MAIN_TAB_LAYOUT = "use_main_tab_layout";
    protected static final String FRAG_TITLE_NAME_ID = "frag_title_name";

    private boolean isUseMainTabLayout = true;
    private int fragTitleNameResId;
    private String fragTitleName;

    TabLayout mTabLayout;
    @Bind(R.id.layout_viewpager)
    ViewPager mViewPager;
    BasePageAdapter mBasePageAdapter;

    FragUseMainTabPresenter mFragUseMainTabPresenter;

    public BaseUseMainTabFragment() {
    }

    @Override
    protected View getLoadingTargetView() {
        return super.getLoadingTargetView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.fragTitleNameResId = arguments.getInt(FRAG_TITLE_NAME_ID);
            this.fragTitleName = getResources().getString(fragTitleNameResId);
        }
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        mTabLayout = ButterKnife.findById(getActivity(), R.id.content_tab);
        mFragUseMainTabPresenter = new FragUseMainTabPresenter(fragTitleNameResId);
        mFragUseMainTabPresenter.attachView(this);
        mFragUseMainTabPresenter.initialized();
        if (isUseMainTabLayout) {
            //使用tabs
            mTabLayout.setVisibility(View.VISIBLE);
            mFragUseMainTabPresenter.initTabLayout(isUseMainTabLayout);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_viewpager;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isUseMainTabLayout) {
            if (mTabLayout.getVisibility() == View.GONE) {
                mTabLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFragUseMainTabPresenter != null) {
            mFragUseMainTabPresenter.detachView();
        }
    }

    //======
    @Override
    public void initTabLayout(List<String> tabTitles, final List<Fragment> fragments) {
        mBasePageAdapter = getBasePageAdapter(tabTitles, fragments);
        mViewPager.setAdapter(mBasePageAdapter);
        //必须在viewpager.setAdapter之后
        mTabLayout.setupWithViewPager(mViewPager);
    }

    protected abstract BasePageAdapter getBasePageAdapter(List<String> tabTitles, List<Fragment> fragments);
}
