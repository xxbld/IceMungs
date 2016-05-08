package org.github.xxbld.icemungs.listeners;

import cn.bmob.v3.BmobUser;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：OnRegisterListener
 */
public interface OnRegisterListener {
    void onSuccess(BmobUser student);

    void onUserNameErr(String msg);

    void onPasswordErr(String msg);

    void onFailure(int failureCode, String msg);
}
