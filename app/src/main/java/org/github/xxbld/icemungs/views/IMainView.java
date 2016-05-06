package org.github.xxbld.icemungs.views;

import org.github.xxbld.icemung.base.mvp.IMvpView;

import java.util.Map;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by xxbld on 2016/3/22
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IMainView extends IMvpView {

    /**
     * 设置NavigationView
     *
     * @param frags
     */
    void initNavigationViewFrags(Map<Integer, Object[]> frags);

    /**
     * set head
     *
     * @param headIcon
     * @param userName
     */
    void setHeadImageView(BmobFile headIcon, String userName);
}
