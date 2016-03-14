package org.github.xxbld.icemungs.presenters.impl;

import android.content.Context;

import org.github.xxbld.icemungs.interactors.ILoginInteractor;
import org.github.xxbld.icemungs.presenters.IPresenter;
import org.github.xxbld.icemungs.views.ILoginView;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class LoginPresenterImpl implements IPresenter {

    private Context mContext;
    private ILoginView mLoginView;
    private ILoginInteractor mLoginInteractor;

    public LoginPresenterImpl(Context context, ILoginView loginView) {
        if (null == loginView){
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        this.mContext=context;
        this.mLoginView=loginView;
//        TODO
//        mLoginInteractor=
    }

    @Override
    public void initialized() {

    }
}
