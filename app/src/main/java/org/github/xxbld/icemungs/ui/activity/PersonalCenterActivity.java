package org.github.xxbld.icemungs.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/5/2.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :个人中心
 */
public class PersonalCenterActivity extends BaseActivity {

    @Bind(R.id.personal_img)
    ImageView mPersonalImg;
    @Bind(R.id.personal_collapsing_toolbar)
    CollapsingToolbarLayout mPersonalCollapsingToolbar;
    @Bind(R.id.personal_tab)
    TabLayout mPersonalTab;
    @Bind(R.id.layout_viewpager)
    ViewPager mLayoutViewpager;

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        this.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        mPersonalCollapsingToolbar.setTitle("YY");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bamboo);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(final Palette palette) {
                int defaultColor = getResources().getColor(R.color.colorPrimary);
                int defaultTitleColor = getResources().getColor(R.color.white);
                int bgColor = palette.getDarkVibrantColor(defaultColor);
                int titleColor = palette.getLightVibrantColor(defaultTitleColor);

                mPersonalCollapsingToolbar.setContentScrimColor(bgColor);
                mPersonalCollapsingToolbar.setCollapsedTitleTextColor(titleColor);
                mPersonalCollapsingToolbar.setExpandedTitleColor(titleColor);
            }
        });
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
