package org.github.xxbld.icemungs.ui.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.dev.sacot41.scviewpager.DotsView;
import com.dev.sacot41.scviewpager.SCPositionAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimation;
import com.dev.sacot41.scviewpager.SCViewAnimationUtil;
import com.dev.sacot41.scviewpager.SCViewPager;
import com.dev.sacot41.scviewpager.SCViewPagerAdapter;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.notbmob.SchoolServiceItem;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/5/21.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 学校服务主页
 */
public class SchoolPageActivity extends BaseActivity {

    @Bind(R.id.school_page_viewpager)
    SCViewPager mSchoolPageViewpager;
    @Bind(R.id.school_page_dotsview)
    DotsView mSchoolPageDotsview;
    @Bind(R.id.school_page1)
    ImageView mSchoolPage1;
    @Bind(R.id.school_page2)
    ImageView mSchoolPage2;
    @Bind(R.id.school_page3)
    ImageView mSchoolPage3;
    @Bind(R.id.school_page_recycler)
    RecyclerView mSchoolPageRecycler;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_school_page;
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
        setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        initViewPage();
        mSchoolPageRecycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        List<SchoolServiceItem> datas = new ArrayList<>();
        datas.add(new SchoolServiceItem("运动", R.drawable.ic_school_service_sport));
        datas.add(new SchoolServiceItem("教学", R.drawable.ic_school_service_education));
        datas.add(new SchoolServiceItem("图书", R.drawable.ic_school_service_library));
        datas.add(new SchoolServiceItem("校医", R.drawable.ic_school_service_doctor));
//        datas.add(new SchoolServiceItem("运动", R.drawable.ic_school_service_sport));
//        datas.add(new SchoolServiceItem("运动", R.drawable.ic_school_service_sport));
//        datas.add(new SchoolServiceItem("运动", R.drawable.ic_school_service_sport));
//        datas.add(new SchoolServiceItem("运动", R.drawable.ic_school_service_sport));
//        datas.add(new SchoolServiceItem("运动", R.drawable.ic_school_service_sport));
        mSchoolPageRecycler.setAdapter(new BaseRecyclerViewAdapter<SchoolServiceItem>(mSchoolPageRecycler, datas, R.layout.item_school_services) {
            @Override
            public void convert(BaseRecyclerHolder holder, SchoolServiceItem item, int position, boolean isScrolling) {
                holder.setText(R.id.school_services_item_name, item.itemName);
                holder.setImageResource(R.id.school_services_item, item.itemRes);
            }

        });
    }

    private void initViewPage() {
        mSchoolPageDotsview.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mSchoolPageDotsview.setNumberOfPage(3);
        SCViewPagerAdapter mSCViewPagerAdapter = new SCViewPagerAdapter(getSupportFragmentManager());
        mSCViewPagerAdapter.setNumberOfPage(3);
        mSCViewPagerAdapter.setFragmentBackgroundColor(R.color.white);
        mSchoolPageViewpager.setAdapter(mSCViewPagerAdapter);
        mSchoolPageViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mSchoolPageDotsview.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final Point size = SCViewAnimationUtil.getDisplaySize(this);

        SCViewAnimation nameTagAnimation = new SCViewAnimation(mSchoolPage1);
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        mSchoolPageViewpager.addAnimation(nameTagAnimation);

        SCViewAnimation nameTagAnimation2 = new SCViewAnimation(mSchoolPage2);
        nameTagAnimation2.startToPosition(size.x, null);
        nameTagAnimation2.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        nameTagAnimation2.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mSchoolPageViewpager.addAnimation(nameTagAnimation2);

        SCViewAnimation nameTagAnimation3 = new SCViewAnimation(mSchoolPage3);
        nameTagAnimation3.startToPosition(size.x, null);
        nameTagAnimation3.addPageAnimation(new SCPositionAnimation(this, 1, -size.x, 0));
        mSchoolPageViewpager.addAnimation(nameTagAnimation3);
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle("江西理工大学");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
