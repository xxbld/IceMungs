package org.github.xxbld.icemungs.ui.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.listeners.OnRegisterListener;
import org.github.xxbld.icemungs.presenters.RegisterPresenter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.ui.widgets.materialdialogs.MaterialDialogHelper;
import org.github.xxbld.icemungs.views.IRegisterView;

import butterknife.Bind;
import cn.bmob.v3.BmobUser;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    @Bind(R.id.register_edt_username)
    MaterialEditText mRegisterEdtUsername;
    @Bind(R.id.register_edt_password)
    MaterialEditText mRegisterEdtPassword;
    @Bind(R.id.register_edt_password_again)
    MaterialEditText mRegisterEdtPasswordAgain;
    @Bind(R.id.register_btn_register)
    Button mRegisterBtnRegister;
    @Bind(R.id.register_root)
    RelativeLayout mRegisterRoot;

    RegisterPresenter mRegisterPresenter;
    MaterialDialog mMaterialDialog;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected View getLoadingTargetView() {
        return mRegisterRoot;
    }

    @Override
    protected void initViewsAndEvents() {
        this.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        mRegisterPresenter = new RegisterPresenter();
        mRegisterPresenter.attachView(this);
        mRegisterPresenter.initialized();

        mRegisterBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog = MaterialDialogHelper.getRegisterAlertDialog(RegisterActivity.this);
                mMaterialDialog.show();
                mRegisterBtnRegister.setClickable(false);
                mRegisterPresenter.register(RegisterActivity.this, mRegisterEdtUsername.getText().toString(), mRegisterEdtPassword.getText().toString(), mRegisterEdtPasswordAgain.getText().toString(), new OnRegisterListener() {
                    @Override
                    public void onSuccess(BmobUser student) {
                        mMaterialDialog.dismiss();
                        noticeLoginActivityFinish();
                        mRegisterPresenter.goHome();
                    }

                    @Override
                    public void onUserNameErr(String msg) {
                        mMaterialDialog.dismiss();
                        showToast(msg);
                        mRegisterBtnRegister.setClickable(true);
                    }

                    @Override
                    public void onPasswordErr(String msg) {
                        mMaterialDialog.dismiss();
                        showToast(msg);
                        mRegisterBtnRegister.setClickable(true);
                    }

                    @Override
                    public void onFailure(int failureCode, String msg) {
                        mMaterialDialog.dismiss();
                        showToast(msg);
                        mRegisterBtnRegister.setClickable(true);
                        //TODO 测试用
                        noticeLoginActivityFinish();
                        mRegisterPresenter.goHome();
                    }
                });
            }
        });
    }

    /**
     * 注册成功，通知Login finish
     */
    private void noticeLoginActivityFinish() {
        sendBroadcast(new Intent().setAction(LoginActivity.ACTION_REGISTER_SUCCESS));
    }

    @Override
    protected void setToolbar() {
        mToolbar.setTitle(R.string.register);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRegisterPresenter != null) {
            mRegisterPresenter.detachView();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                RegisterActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //======
    @Override
    public void goHome() {
        goThenKill(MainActivity.class);
    }
}
