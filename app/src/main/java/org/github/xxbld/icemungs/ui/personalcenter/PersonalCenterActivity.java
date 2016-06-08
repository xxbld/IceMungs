package org.github.xxbld.icemungs.ui.personalcenter;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.ui.adapter.BasePageAdapter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.verbose.ITalkFragment1;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;

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
    private Student mStudent;
    private String mStudentId;

    private enum CollapsingToolbarLayoutState {
        //展开、折叠、中间
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void handleBundleExtras(Bundle extras) {
        super.handleBundleExtras(extras);
        if (extras != null) {
            mStudentId = extras.getString("studentId");
            MLog.i(TAG, "studentId:" + mStudentId);
        }
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        this.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
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
                            //由折叠变为中间状态
                            mPersonalTab.setBackgroundColor(getResources().getColor(R.color.transparent));
                            mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });
        Student currentUser = BmobUser.getCurrentUser(this, Student.class);
        if (mStudentId == null || mStudentId.equals(currentUser.getObjectId())) {
            mStudent = currentUser;
            loadPersonalCenter();
        } else {
            BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
            studentBmobQuery.getObject(this, mStudentId, new GetListener<Student>() {
                @Override
                public void onSuccess(Student student) {
                    mStudent = student;
                    loadPersonalCenter();
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
//        loadPersonalCenter();
    }

    private void loadPersonalCenter() {
        mStudent = BmobUser.getCurrentUser(this, Student.class);
        GlideHelper.tranCircleImage(this, mStudent.getIconUrl(), mPersonalIcon);
        List<String> titles = new ArrayList<>();
        titles.add("资料");
        titles.add("我说");
        titles.add("关注");
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(PersonalDataFragment.newInstance(mStudent.getObjectId()));
        fragments.add(new ITalkFragment1());
        fragments.add(PersonalNoticeFragment.newInstance(mStudent.getObjectId()));
        BasePageAdapter basePageAdapter = new BasePageAdapter(getSupportFragmentManager(), titles) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };
        mLayoutViewpager.setAdapter(basePageAdapter);
        mLayoutViewpager.setOffscreenPageLimit(3);
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
        mToolbar.setTitle("小小冰绿豆");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
