package org.github.xxbld.icemungs.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemung.netsatus.NetStatusReceiver;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.presenters.MainPresenter;
import org.github.xxbld.icemungs.ui.adapter.NavFragmentAdapter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.widgets.SheetFloatActionButton;
import org.github.xxbld.icemungs.views.IMainView;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener {

    @Bind(R.id.main_nav)
    NavigationView mNavigationView;
    @Bind(R.id.main_drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.fab)
    SheetFloatActionButton mFab;
    @Bind(R.id.content_tab)
    TabLayout mTabLayout;

    View mHeadView;
    ImageView mHeadImageView;
    TextView mHeadNameTextView;
    MaterialSheetFab mSheetMaterialSheetFab;
    Menu mMenu;
    MainPresenter mMainPresenter;
    NavFragmentAdapter mNavFragmentAdapter;

    Student mCurrentStudent;
    private int mCurrentFragId;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        //获取当前用户
        mCurrentStudent = BmobUser.getCurrentUser(MainActivity.this, Student.class);
        if (mCurrentStudent == null) {
            //TODO handle null
            mCurrentStudent = new Student();
            mCurrentStudent.setUsername("YY");
        }
        this.setStatusBarColorWithDrawer(mDrawerLayout, getResources().getColor(R.color.colorPrimary));
        mMainPresenter = new MainPresenter(mCurrentStudent);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetStatusReceiver.registerNetStatusReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetStatusReceiver.unRegisterNetStatusReceiver(this);
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
    public void setHeadImageView(String headIconUrl, String userName) {
        if (headIconUrl == null) {
            GlideHelper.tranCircleImage(this.getApplicationContext(), R.drawable.ic_user, mHeadImageView);
        } else {
            GlideHelper.tranCircleImage(this.getApplicationContext(), headIconUrl, mHeadImageView);
        }
        mHeadNameTextView.setText(userName);
    }

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
                mCurrentFragId = currentItemId;
                switch (currentItemId) {
//                    case R.id.nav_school:
//                        mTabLayout.setVisibility(View.VISIBLE);
//                        break;
//                    case R.id.nav_verbose:
//                        mTabLayout.setVisibility(View.VISIBLE);
//                        break;
                    case R.id.nav_daily:
                        mTabLayout.setVisibility(View.GONE);
                        break;
                    case R.id.nav_resume:
                        mTabLayout.setVisibility(View.GONE);
                        break;
                }
            }

        });
        mNavFragmentAdapter.switchToItem(R.id.nav_verbose);
    }

    private void setNav() {
        //item 的icon颜色也有
        mNavigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        setHeaderView();
    }

    private void setHeaderView() {
        mHeadView = mNavigationView.getHeaderView(0);
        mHeadImageView = (ImageView) mHeadView.findViewById(R.id.main_head_img);
        mHeadNameTextView = (TextView) mHeadView.findViewById(R.id.main_head_name_textview);
        mHeadImageView.setOnClickListener(this);
        mMainPresenter.initHeadImageViewPara();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.main_head_img:
                //to personal center
                this.go(PersonalCenterActivity.class);
                break;
            default:
                break;
        }
    }
}
