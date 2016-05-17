package org.github.xxbld.icemungs.presenters;

import android.content.res.Resources;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.Constant;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.ui.fragment.UseTabFragment;
import org.github.xxbld.icemungs.ui.resume.ResumeFragment;
import org.github.xxbld.icemungs.ui.schoolmap.SchoolMapFragment;
import org.github.xxbld.icemungs.views.IMainView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xxbld on 2016/3/22
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class MainPresenter extends BasePresenter<IMainView> {

    Student mStudent;

    public MainPresenter(Student student) {
        this.mStudent = student;
    }

    @Override
    public void initialized() {
        super.initialized();
    }


    @Override
    public void attachView(IMainView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void initNav() {
        this.getMvpView().initNavigationViewFrags(getNavFragmentItems());
    }

    /**
     * Head Set
     */
    public void initHeadImageViewPara() {
        this.getMvpView().setHeadImageView(mStudent.getIconUrl(), mStudent.getUsername());
    }

    /**
     * Nav Set
     *
     * @return
     */
    private Map<Integer, Object[]> getNavFragmentItems() {
        int[] keys = {R.id.nav_school, R.id.nav_daily, R.id.nav_verbose, R.id.nav_resume};
        int[] titles = {R.string.nav_menu_school, R.string.nav_menu_daily, R.string.nav_menu_verbose, R.string.nav_menu_resume};
//        Object[] titles = {R.string.nav_menu_school, "null", null, "履历a"};
        int[] menuIds = {R.menu.main_nav_menu_school, R.menu.main_nav_menu_schoolmap, 0, 0};
        Object[] fragments = new Object[]{
                UseTabFragment.newInstance(R.string.nav_menu_school),
                SchoolMapFragment.newInstance(Constant.ARCGIS_MAPSERVER_TITLE_URL,
                        Constant.ACRGIS_MAPSERVER_TITLE_IMAGE_URL),
                UseTabFragment.newInstance(R.string.nav_menu_verbose),
                new ResumeFragment()};
//        Object[] fragments = new Object[]{SchoolNewsFragment.newInstance(R.string.nav_menu_school), new ResumeFragment(),
//                VerboseFragment.newInstance(R.string.nav_menu_verbose), new ResumeFragment()};
        Map<Integer, Object[]> fragMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            Object[] value = new Object[3];
            value[0] = fragments[i];
            value[1] = titles[i];
            value[2] = menuIds[i];
            fragMap.put(keys[i], value);
        }
        return fragMap;
    }

    private String getString(int resId) {
        return Resources.getSystem().getString(resId);
    }
}
