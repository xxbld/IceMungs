package org.github.xxbld.icemungs.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.listeners.OnLoginFinishedListener;
import org.github.xxbld.icemungs.presenters.LoginPresenter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.views.ILoginView;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements ILoginView {

    @Bind(R.id.login_root)
    View mRootView;
    @Bind(R.id.login_edt_username)
    MaterialEditText mEdtUsername;
    @Bind(R.id.login_edt_password)
    MaterialEditText mEdtPassword;

    @Bind(R.id.login_btn_login)
    Button mBtnLogin;

    private LoginPresenter loginPresenter;
    private Handler mHandler = new Handler() {
    };

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected View getLoadingTargetView() {
        return mRootView;
    }

    @Override
    protected void initViewsAndEvents() {
        this.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
        loginPresenter.initialized();
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnLogin.setClickable(false);
                loginPresenter.login(LoginActivity.this, mEdtUsername.getText().toString(), mEdtPassword.getText().toString(), new OnLoginFinishedListener() {
                    @Override
                    public void onSuccess(Student student) {
                        //before goHome
                    }

                    @Override
                    public void onUserNameErr(String msg) {
                        showToast(msg);
                        mBtnLogin.setClickable(true);
                    }

                    @Override
                    public void onPasswordErr(String msg) {
                        showToast(msg);
                        mBtnLogin.setClickable(true);
                    }

                    @Override
                    public void onFailure(int failureCode, String msg) {
                        showToast(msg);
                        mBtnLogin.setClickable(true);
                    }
                });
            }
        });
        goHome();
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.login);
        mToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    //================impls
    @Override
    public void goHome() {
        LoginActivity.this.go(MainActivity.class);
    }
}
