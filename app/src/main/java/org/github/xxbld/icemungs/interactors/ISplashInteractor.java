package org.github.xxbld.icemungs.interactors;

import android.content.Context;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface ISplashInteractor {

    /**
     * get copyright text
     * @param context
     * @return
     */
    String getCopyRight(Context context);

    /**
     * get version code
     * @param context
     * @return
     */
    String getVersionCode(Context context);

    /**
     * get image resource id
     * @return
     */
    int getImageResourceId();
}
