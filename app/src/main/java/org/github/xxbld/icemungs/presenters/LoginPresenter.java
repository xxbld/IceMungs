package org.github.xxbld.icemungs.presenters;

import android.content.Context;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.TextUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.listeners.OnLoginFinishedListener;
import org.github.xxbld.icemungs.views.ILoginView;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    Student mStudent = new Student();
    OnLoginFinishedListener mListener;

    public LoginPresenter() {
    }

    @Override
    public void attachView(ILoginView mvpView) {
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
     * goRegister
     */
    public void goRegister() {
        this.getMvpView().goRegister();
    }

    /**
     * goHome
     */
    public void goHome() {
        this.getMvpView().goHome();
    }

    /**
     * login
     *
     * @param context
     * @param username
     * @param pwd
     * @param listener
     */
    public void login(Context context, String username, String pwd, OnLoginFinishedListener listener) {
//        username = "小小冰绿豆";
//        pwd = "123065";
        if (context == null || listener == null) {
            return;
        }
        this.mListener = listener;
        boolean checkUserOK = checkUser(context, username, pwd);
        MLog.i(TAG, "checkUserOK: " + checkUserOK);
        if (!checkUserOK) {
            return;
        }
        mStudent.setUsername(username);
        mStudent.setPassword(pwd);
        mStudent.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                mListener.onSuccess(mStudent);
            }

            @Override
            public void onFailure(int i, String s) {
                MLog.i(TAG, "code: " + i + " meg: " + s);
                mListener.onFailure(i, s);
            }
        });
    }

    private boolean checkUser(Context context, String username, String pwd) {
        boolean userNameEmpty = TextUtil.isEmpty(username);
        boolean userPwdEmpty = TextUtil.isEmpty(pwd);
        if (userNameEmpty) {
            mListener.onUserNameErr(context.getString(R.string.login_username_error));
            return false;
        }
        if (userPwdEmpty) {
            mListener.onPasswordErr(context.getString(R.string.login_pwd_error));
            return false;
        }
        return true;
    }
}
