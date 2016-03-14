package org.github.xxbld.icemungs.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.content_fab)
    FloatingActionButton mFab;
    @Bind(R.id.main_nav)
    NavigationView mNavigationView;
    @Bind(R.id.main_drawer)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.cb_1)
    CheckBox mCheckBox;
    @Bind(R.id.content_coo)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.btn_go)
    Button mButton;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        //item 的icon颜色也有
        mNavigationView.setItemIconTintList(null);
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimary));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MLog.e(TAG, "click:" + mCheckBox.isChecked());
                if (mCheckBox.isChecked()) {
                    mCoordinatorLayout.setBackgroundResource(R.mipmap.material_design_1);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                } else {
                    mCoordinatorLayout.setBackgroundResource(0);
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.md_red_A400));
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go(TestActivity1.class);
            }
        });
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
}
