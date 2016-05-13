package org.github.xxbld.icemungs.ui.activity;

import android.os.Bundle;
import android.view.View;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

/**
 * Created by xxbld on 2016/5/12.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class ChooseSchoolActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_choose_school;
    }

    @Override
    protected View getLoadingTargetView() {
        return super.getLoadingTargetView();
    }

    @Override
    protected void handleBundleExtras(Bundle extras) {
        super.handleBundleExtras(extras);
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();

    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle("选择学校");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
