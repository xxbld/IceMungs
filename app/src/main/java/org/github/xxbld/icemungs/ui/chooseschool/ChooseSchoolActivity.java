package org.github.xxbld.icemungs.ui.chooseschool;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.School;
import org.github.xxbld.icemungs.presenters.ChooseSchoolPresenter;
import org.github.xxbld.icemungs.ui.base.BasePtrClassicActivity;
import org.github.xxbld.icemungs.views.IChooseSchoolView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxbld on 2016/5/12.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
@Deprecated
public class ChooseSchoolActivity extends BasePtrClassicActivity implements IChooseSchoolView {
    ChooseSchoolPresenter mChooseSchoolPresenter;
    SchoolChooseAdapter mSchoolChooseAdapter;
    List<School> mSchools = new ArrayList<>();

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.frag_ptr_base;
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
        this.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        mChooseSchoolPresenter = new ChooseSchoolPresenter(this);
        mChooseSchoolPresenter.attachView(this);
        mChooseSchoolPresenter.initialized();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSchoolChooseAdapter = new SchoolChooseAdapter(mRecyclerView, mSchools, mChooseSchoolPresenter);
        mRecyclerView.setAdapter(mSchoolChooseAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChooseSchoolPresenter != null) {
            mChooseSchoolPresenter.detachView();
        }
    }

    @Override
    protected boolean isSetPtrLayout() {
        return true;
    }

    @Override
    protected void onPtrRefreshBegin() {
        if (mChooseSchoolPresenter != null && mSchoolChooseAdapter != null) {
            mChooseSchoolPresenter.loadSchool();
        }
    }

    @Override
    protected boolean isPtrAutoRefresh() {
        return true;
    }

    @Override
    protected long getPtrAutoRefreshDelayMillis() {
        return 0;
    }

    @Override
    protected void setToolbar() {
//        mToolbar.setTitle("选择学校");
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //====================
    @Override
    public void setRecycler(Object object) {
        if (object != null) {
            List<School> schools = (List<School>) object;
            mSchools.addAll(schools);
            mSchoolChooseAdapter.notifyDataSetChanged();
        }

    }
}
