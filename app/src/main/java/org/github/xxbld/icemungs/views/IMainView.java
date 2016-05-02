package org.github.xxbld.icemungs.views;

import android.graphics.Bitmap;

import org.github.xxbld.icemung.base.mvp.IMvpView;

import java.util.Map;

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
     * @param headBitmap
     * @param userName
     */
    void setHeadImageView(Bitmap headBitmap, String userName);
}
