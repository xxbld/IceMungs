package org.github.xxbld.icemungs.views;

import android.support.v4.app.Fragment;

import org.github.xxbld.icemung.base.mvp.IMvpView;

import java.util.List;

/**
 * Created by xxbld on 2016/3/22
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IMainView extends IMvpView {

    void initTabLayout(List<String> tabTitles, List<Fragment> fragments);

}
