package org.github.xxbld.icemungs.presenters;

import android.content.Context;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.TextUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.listeners.OnRegisterListener;
import org.github.xxbld.icemungs.views.IRegisterView;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript :RegisterPresenter
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {

    BmobUser mStudent = new BmobUser();
    OnRegisterListener mListener;

    public RegisterPresenter() {
    }

    @Override
    public void attachView(IRegisterView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void initialized() {
        super.initialized();
    }

    /**
     * go Main
     */
    public void goHome() {
        this.getMvpView().goHome();
    }

    /**
     * register
     *
     * @param context
     * @param username
     * @param pwd
     * @param listener
     */
    public void register(Context context, String username, String pwd, String pwdAgain, OnRegisterListener listener) {

        if (context == null || listener == null) {
            return;
        }
        this.mListener = listener;
        boolean checkUserOK = checkUser(context, username, pwd, pwdAgain);
        MLog.i(TAG, "checkUserOK: " + checkUserOK);
        if (!checkUserOK) {
            return;
        }
        mStudent.setUsername(username);
        mStudent.setPassword(pwd);
        mStudent.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                mListener.onSuccess(mStudent);
                RegisterPresenter.this.getMvpView().goHome();
            }

            @Override
            public void onFailure(int i, String s) {
                MLog.i(TAG, "code: " + i + " meg: " + s);
                mListener.onFailure(i, s);
            }
        });
    }

    private boolean checkUser(Context context, String username, String pwd, String pwdAgain) {
        boolean userNameEmpty = TextUtil.isEmpty(username);
        boolean userPwdEmpty = TextUtil.isEmpty(pwd);
        boolean userPwdAgainEmpty = TextUtil.isEmpty(pwdAgain);
        if (userNameEmpty) {
            mListener.onUserNameErr(context.getString(R.string.login_username_error));
            return false;
        }
        if (userPwdEmpty) {
            mListener.onPasswordErr(context.getString(R.string.login_pwd_error));
            return false;
        }
        if (userPwdAgainEmpty) {
            mListener.onPasswordErr(context.getString(R.string.login_pwd_error));
            return false;
        }
        if (!pwd.equals(pwdAgain)) {
            mListener.onPasswordErr(context.getString(R.string.login_pwd_error));
            return false;
        }
        return true;
    }
}
