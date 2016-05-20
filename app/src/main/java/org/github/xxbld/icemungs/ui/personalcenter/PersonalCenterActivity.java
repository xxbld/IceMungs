package org.github.xxbld.icemungs.ui.personalcenter;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.adapter.BasePageAdapter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.schoolnews.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/5/2.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :个人中心
 */
public class PersonalCenterActivity extends BaseActivity {

    @Bind(R.id.personal_appbar)
    AppBarLayout mAppBarLayout;
    @Bind(R.id.personal_img)
    ImageView mPersonalImg;
    @Bind(R.id.personal_collapsing_toolbar)
    CollapsingToolbarLayout mPersonalCollapsingToolbar;
    @Bind(R.id.personal_icon)
    ImageView mPersonalIcon;
    @Bind(R.id.personal_tab)
    TabLayout mPersonalTab;
    @Bind(R.id.layout_viewpager)
    ViewPager mLayoutViewpager;
    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        //展开、折叠、中间
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void initViewsAndEvents() {
        this.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        super.initViewsAndEvents();
        GlideHelper.tranCircleImage(this, R.drawable.ic_user, mPersonalIcon);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        mPersonalTab.setBackgroundColor(getResources().getColor(R.color.transparent));
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        mPersonalTab.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
//                            由折叠变为中间状态
                            mPersonalTab.setBackgroundColor(getResources().getColor(R.color.transparent));
                            mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
        List<String> titles = new ArrayList<>();
        titles.add("我说");
        titles.add("我关注的");
        BasePageAdapter basePageAdapter = new BasePageAdapter(getSupportFragmentManager(), titles) {
            @Override
            public Fragment getItem(int position) {
                return NewsFragment.newInstance(null, 0);
            }
        };
        mLayoutViewpager.setAdapter(basePageAdapter);
        mLayoutViewpager.setOffscreenPageLimit(2);
        mPersonalTab.setupWithViewPager(mLayoutViewpager);
    }

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected View getLoadingTargetView() {
        return super.getLoadingTargetView();
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle("YY");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
