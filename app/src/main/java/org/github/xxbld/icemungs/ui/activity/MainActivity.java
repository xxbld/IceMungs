package org.github.xxbld.icemungs.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.presenters.MainPresenter;
import org.github.xxbld.icemungs.ui.adapter.NavFragmentAdapter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.widgets.SheetFloatActionButton;
import org.github.xxbld.icemungs.views.IMainView;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainView {

    @Bind(R.id.main_nav)
    NavigationView mNavigationView;
    @Bind(R.id.main_drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.common_toolbar_scroll_enteralways)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    SheetFloatActionButton mFab;

    MaterialSheetFab mSheetMaterialSheetFab;
    Menu mMenu;
    MainPresenter mMainPresenter;
    NavFragmentAdapter mNavFragmentAdapter;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimary));
        mMainPresenter = new MainPresenter();
        mMainPresenter.attachView(this);
        mMainPresenter.initialized();
        setSheetFAB();
    }

    private void setSheetFAB() {
        View overlay = ButterKnife.findById(this, R.id.overlay);
        View sheetCardView = ButterKnife.findById(this, R.id.fab_sheet);
        mSheetMaterialSheetFab = new MaterialSheetFab<>(mFab, sheetCardView, overlay, getResources().getColor(R.color.white), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    protected void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.mMenu = menu;
        mMainPresenter.initNav();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            MLog.i(TAG, "Action Setting Clicked !");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //===================impl

    @Override
    public void initNavigationViewFrags(Map<Integer, Object[]> frags) {
        setNav();
        mNavFragmentAdapter = new NavFragmentAdapter(getSupportFragmentManager(),
                R.id.content_frame_frags, frags, mNavigationView, mMenu);
        mNavFragmentAdapter.switchToItem(R.id.nav_school);
        mNavFragmentAdapter.setOnNavSelectedListener(new NavFragmentAdapter.OnNavSelectedListener() {
            @Override
            public boolean onNavSelected(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.nav_theme:
                        MLog.i(TAG, "Theme Clicked");
                        break;
                    case R.id.nav_setting:
                        MLog.i(TAG, "Setting Clicked");
                        break;
                }
                mDrawerLayout.closeDrawers();
                return false;
            }

            @Override
            public void onSwitchFragmentSuccess(int currentItemId) {

            }

        });
    }

    private void setNav() {
        //item 的icon颜色也有
        mNavigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
}
