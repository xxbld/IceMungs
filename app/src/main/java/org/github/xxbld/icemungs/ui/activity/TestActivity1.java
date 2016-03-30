package org.github.xxbld.icemungs.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.adapter.SchoolFragmentAdapter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.fragment.FlashFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/2/19
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class TestActivity1 extends BaseActivity {
    @Bind(R.id.common_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.btn1)
    Button mBtn1;
    @Bind(R.id.btn2)
    Button mBtn2;
    @Bind(R.id.linear)
    LinearLayout mLayout;

    @Bind(R.id.test_tab)
    TabLayout mTestTab;
    @Bind(R.id.test_viewpager)
    ViewPager mTestViewpager;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_test1;
    }

    @Override
    protected void initViewsAndEvents() {

        StatusBarUtil.setTranslucent(this);

        final SystemBarTintManager tintManager = new SystemBarTintManager(TestActivity1.this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透明
                mLayout.setBackgroundResource(R.mipmap.material_design_3);
                tintManager.setTintColor(getResources().getColor(R.color.md_red_A400));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //颜色
                mLayout.setBackgroundResource(0);
                tintManager.setTintColor(Color.parseColor("#FF4081"));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        initTabLayout(getTitles(), getTabFragments());
    }

    @Override
    protected void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initTabLayout(List<String> tabTitles, List<Fragment> fragments) {
        SchoolFragmentAdapter mMainFragmentAdapter = new SchoolFragmentAdapter(getSupportFragmentManager(), fragments, tabTitles);
        mTestViewpager.setAdapter(mMainFragmentAdapter);
        //必须在viewpager.setAdapter之后
        mTestTab.setupWithViewPager(mTestViewpager);
    }

    private List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("新鲜事");
        titles.add("新鲜事");
        titles.add("新鲜事");
        return titles;
    }

    private List<Fragment> getTabFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FlashFragment());
        fragments.add(new FlashFragment());
        fragments.add(new FlashFragment());
        return fragments;
    }
}
