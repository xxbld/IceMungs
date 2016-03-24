package org.github.xxbld.icemungs.presenters;

import android.support.v4.app.Fragment;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemungs.ui.fragment.FlashFragment;
import org.github.xxbld.icemungs.ui.fragment.TestArcgisFragment;
import org.github.xxbld.icemungs.views.IMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxbld on 2016/3/22
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter() {
    }

    @Override
    public void initialized() {
        this.getMvpView().initTabLayout(getTitles(),getTabFragments());
    }


    @Override
    public void attachView(IMainView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }



    private List<String> getTitles(){
        List<String> titles = new ArrayList<>();
        titles.add("新鲜事");
        titles.add("新鲜事");
        titles.add("新鲜事");
        return titles;
    }

    private List<Fragment> getTabFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FlashFragment());
        fragments.add(new TestArcgisFragment());
        fragments.add(new FlashFragment());
        return  fragments;
    }
}
