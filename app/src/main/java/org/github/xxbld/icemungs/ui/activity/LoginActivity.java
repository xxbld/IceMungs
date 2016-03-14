package org.github.xxbld.icemungs.ui.activity;

import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
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

    private Handler mHandler = new Handler() {
    };

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_login;
    }

//    @Override
//    protected View getLoadingTargetView() {
////        return mRootView;
////        return ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
//    }

    @Override
    protected void initViewsAndEvents() {
        mToolbar.setTitle("Login");
        mToolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(mToolbar);

        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading("loading...");
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoading();
                        goHome();
                    }
                }, 2000);
            }
        });
    }

    //    ===============ILoginView Implement Methods====================
    @Override
    public void goHome() {
        go(MainActivity.class);
    }

    @Override
    public void showLoading(String msg) {
        this.toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading() {
        this.toggleShowLoading(false, null);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }
}
