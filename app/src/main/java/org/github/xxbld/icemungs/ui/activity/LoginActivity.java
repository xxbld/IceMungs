package org.github.xxbld.icemungs.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
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

//    @Override
//    protected View getLoadingTargetView() {
////        return mRootView;
//    }

    @Override
    protected void initViewsAndEvents() {
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);

        mToolbar.setTitle("Login");
        mToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login(mEdtUsername.getText().toString(), mEdtPassword.getText().toString());
            }
        });
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
