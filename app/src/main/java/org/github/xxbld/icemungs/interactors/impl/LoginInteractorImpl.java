package org.github.xxbld.icemungs.interactors.impl;

import org.github.xxbld.icemung.utils.TextUtil;
import org.github.xxbld.icemungs.interactors.ILoginInteractor;
import org.github.xxbld.icemungs.listeners.OnLoginFinishedListener;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class LoginInteractorImpl implements ILoginInteractor {
    @Override
    public void login(String userName, String pwd, OnLoginFinishedListener loginFinishedListener) {
        if (TextUtil.isEmpty(userName)){
            loginFinishedListener.onUserNameErr();
        }
        if (TextUtil.isEmpty(pwd)){
            loginFinishedListener.onPasswordErr();
        }

    }
}
