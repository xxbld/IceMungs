package org.github.xxbld.icemungs.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.dev.sacot41.scviewpager.DotsView;
import com.dev.sacot41.scviewpager.SCViewPager;
import com.dev.sacot41.scviewpager.SCViewPagerAdapter;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.presenters.IPresenter;
import org.github.xxbld.icemungs.presenters.impl.WelcomePresenterImpl;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.views.IWelcomeView;

import butterknife.Bind;

public class WelcomeActivity extends BaseActivity implements IWelcomeView{

    @Bind(R.id.welcome_viewpager)
    SCViewPager mViewpager;
    @Bind(R.id.welcome_dotsview)
    DotsView mDotsView;

    @Bind(R.id.welcome_btn_home)
    Button mBtnToHome;

    private IPresenter mWelcomePresenter;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViewsAndEvents() {
        mWelcomePresenter=new WelcomePresenterImpl(this,this);
        mWelcomePresenter.initialized();

        mBtnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLoginOrHome();
            }
        });
    }

    //================WelcomeView Implement Methods===========================
    @Override
    public void setTranslucentStatus() {
        StatusBarUtil.setTranslucent(this,false);
    }

    @Override
    public void initDotsView(int resIdSelected, int resIdUnSelected, int pageNum) {
        mDotsView.setDotRessource(resIdSelected, resIdUnSelected);
        mDotsView.setNumberOfPage(pageNum);
    }

    @Override
    public void initViewPager(SCViewPagerAdapter scViewPagerAdapter) {
        mViewpager.setAdapter(scViewPagerAdapter);
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDotsView.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void goLoginOrHome() {
        go(LoginActivity.class);
    }
}
