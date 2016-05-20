package org.github.xxbld.icemungs.presenters;

import android.support.v4.app.Fragment;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.fragment.FlashFragment;
import org.github.xxbld.icemungs.ui.schoolnews.NewsFragment;
import org.github.xxbld.icemungs.views.IFragUseMainTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：侧边 Fragment Presenter
 */
public class FragUseMainTabPresenter extends BasePresenter<IFragUseMainTabView> {

    private int fragTitleNameResId;

    public FragUseMainTabPresenter(int fragTitleNameResId) {
        this.fragTitleNameResId = fragTitleNameResId;
    }

    @Override
    public void attachView(IFragUseMainTabView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void initialized() {
        super.initialized();
    }

    public void initTabLayout(String studentID) {
        List<String> titles = null;
        List<Fragment> frags = null;
        if (fragTitleNameResId == R.string.nav_menu_school) {
            titles = getSchoolNewsTitles();
            frags = getSchoolNewsTabFragments(studentID);
        }
        if (fragTitleNameResId == R.string.nav_menu_verbose) {
            titles = getVerboseTitles();
            frags = getVerboseTabFragments();
        }
        this.getMvpView().initTabLayout(titles, frags);
    }

    private List<Fragment> getVerboseTabFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FlashFragment());
        fragments.add(new FlashFragment());
        return fragments;
    }

    private List<String> getVerboseTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("我说");
        titles.add("投票");
        return titles;
    }


    private List<String> getSchoolNewsTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("最新");
        titles.add("本周最热");
        titles.add("校级新闻");
        titles.add("学院新闻");
        titles.add("专业动态");
        return titles;
    }

    private List<Fragment> getSchoolNewsTabFragments(String studentID) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance(studentID, FragNewsPresenter.NewsType.NEW.getValue()));
        fragments.add(new FlashFragment());
        fragments.add(new FlashFragment());
        fragments.add(new FlashFragment());
        fragments.add(new FlashFragment());
//        fragments.add(NewsFragment.newInstance(studentID, FragNewsPresenter.NewsType.HOT.getValue()));
//        fragments.add(NewsFragment.newInstance(studentID, FragNewsPresenter.NewsType.SCHOOL.getValue()));
//        fragments.add(NewsFragment.newInstance(studentID, FragNewsPresenter.NewsType.ACADEMY.getValue()));
//        fragments.add(NewsFragment.newInstance(studentID, FragNewsPresenter.NewsType.SPECIALITY.getValue()));
        return fragments;
    }
}
