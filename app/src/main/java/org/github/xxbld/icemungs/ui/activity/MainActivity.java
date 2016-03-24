package org.github.xxbld.icemungs.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.presenters.MainPresenter;
import org.github.xxbld.icemungs.ui.adapter.MainFragmentAdapter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.views.IMainView;

import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements IMainView {

    @Bind(R.id.main_nav)
    NavigationView mNavigationView;
    @Bind(R.id.main_drawer)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.content_fab)
    FloatingActionButton mFab;
    @Bind(R.id.content_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.content_tab)
    TabLayout mTabLayout;

    MainPresenter mMainPresenter;
    MainFragmentAdapter mMainFragmentAdapter;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        mMainPresenter.initialized();

        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimary));

        setNav();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    private void setNav() {
        //item 的icon颜色也有
        mNavigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //item click listener
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void setToolbar() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //===================impl
    @Override
    public void initTabLayout(List<String> tabTitles, List<Fragment> fragments) {
        mMainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments, tabTitles);
        mViewPager.setAdapter(mMainFragmentAdapter);
        //必须在viewpager.setAdapter之后
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
