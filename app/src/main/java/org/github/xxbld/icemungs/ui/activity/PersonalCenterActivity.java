package org.github.xxbld.icemungs.ui.activity;

import android.view.View;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

/**
 * Created by xxbld on 2016/5/2.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :个人中心
 */
public class PersonalCenterActivity extends BaseActivity {

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
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
        setTitle("YY");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
