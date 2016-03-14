package org.github.xxbld.icemungs.presenters.impl;

import android.content.Context;

import org.github.xxbld.icemungs.interactors.ISplashInteractor;
import org.github.xxbld.icemungs.interactors.impl.SplashInteractorImpl;
import org.github.xxbld.icemungs.presenters.IPresenter;
import org.github.xxbld.icemungs.views.ISplashView;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class SplashPresenterImpl implements IPresenter {

    private ISplashView mSplashView;
    private Context mContext;
    private ISplashInteractor mSplashInteractor;

    public SplashPresenterImpl(Context context, ISplashView splashView) {
        if (null == splashView) {
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        this.mContext = context;
        this.mSplashView = splashView;
        mSplashInteractor = new SplashInteractorImpl();
    }

    @Override
    public void initialized() {

        mSplashView.setTranslucentStatus();

        mSplashView.initViews(
                mSplashInteractor.getCopyRight(mContext),
                mSplashInteractor.getVersionCode(mContext),
                mSplashInteractor.getImageResourceId());

        mSplashView.goHomePage();
    }
}
