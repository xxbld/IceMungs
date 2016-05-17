package org.github.xxbld.icemungs.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.listeners.OnLoginFinishedListener;
import org.github.xxbld.icemungs.presenters.LoginPresenter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.widgets.materialdialogs.MaterialDialogHelper;
import org.github.xxbld.icemungs.views.ILoginView;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    public static final String ACTION_REGISTER_SUCCESS = "org.github.xxbld.icemungs.registerSuccess";

    @Bind(R.id.login_root)
    View mRootView;
    @Bind(R.id.login_edt_username)
    MaterialEditText mEdtUsername;
    @Bind(R.id.login_edt_password)
    MaterialEditText mEdtPassword;
    @Bind(R.id.login_btn_login)
    Button mBtnLogin;
    @Bind(R.id.login_btn_register)
    TextView mBtnRegister;

    RegisterSuccessReceiver mRegisterSuccessReceiver;
    LoginPresenter loginPresenter;
    MaterialDialog mMaterialDialog;

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
        mEdtUsername.setText("小小冰绿豆");
        mEdtPassword.setText("123065");
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
//        queryUser();
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.login);
        mToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegisterSuccessReceiver = new RegisterSuccessReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_REGISTER_SUCCESS);
        registerReceiver(mRegisterSuccessReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {
            loginPresenter.detachView();
        }
        if (mRegisterSuccessReceiver != null) {
            unregisterReceiver(mRegisterSuccessReceiver);
        }
    }

    //================impls
    @Override
    public void goHome() {
        LoginActivity.this.goThenKill(MainActivity.class);
    }

    @Override
    public void goRegister() {
        LoginActivity.this.go(RegisterActivity.class);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.login_btn_login:
                mMaterialDialog = MaterialDialogHelper.getLoginAlertDialog(this);
                mMaterialDialog.show();
                login();
                break;
            case R.id.login_btn_register:
                loginPresenter.goRegister();
                break;
        }
    }

    /**
     * login
     */
    private void login() {
        mBtnLogin.setClickable(false);
        loginPresenter.login(LoginActivity.this, mEdtUsername.getText().toString(), mEdtPassword.getText().toString(), new OnLoginFinishedListener() {
            @Override
            public void onSuccess(Student student) {
                mMaterialDialog.dismiss();
                loginPresenter.goHome();
            }

            @Override
            public void onUserNameErr(String msg) {
                mMaterialDialog.dismiss();
                showToast(msg);
                mBtnLogin.setClickable(true);
            }

            @Override
            public void onPasswordErr(String msg) {
                mMaterialDialog.dismiss();
                showToast(msg);
                mBtnLogin.setClickable(true);
            }

            @Override
            public void onFailure(int failureCode, String msg) {
                mMaterialDialog.dismiss();
                showToast(msg);
                mBtnLogin.setClickable(true);
                //TODO 测试用
                loginPresenter.goHome();
            }
        });
    }

    /**
     * 注册成功receive
     */
    public class RegisterSuccessReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equalsIgnoreCase(ACTION_REGISTER_SUCCESS)) {
                LoginActivity.this.finish();
            }
        }
    }
}
