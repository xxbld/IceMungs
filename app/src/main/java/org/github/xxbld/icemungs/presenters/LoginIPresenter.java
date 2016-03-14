package org.github.xxbld.icemungs.presenters;

import android.os.Handler;

import org.github.xxbld.icemung.base.mvp.BaseIPresenter;
import org.github.xxbld.icemungs.views.ILoginView;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class LoginIPresenter extends BaseIPresenter<ILoginView> {
    public LoginIPresenter() {
    }

    @Override
    public void attachView(ILoginView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void login(String username, String pwd) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getMvpView().goHome();
            }
        }, 2000);
    }
}
