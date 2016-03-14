package org.github.xxbld.icemungs.ui.activity;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.presenters.SplashIPresenter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.views.ISplashView;

import butterknife.Bind;

public class SplashActivity extends BaseActivity implements ISplashView {

    @Bind(R.id.splash_img_bg)
    ImageView mImgBg;
    @Bind(R.id.splash_txt_versioncode)
    TextView mTxtVersioncode;
    @Bind(R.id.splash_txt_copyright)
    TextView mTxtCopyright;

    private SplashIPresenter mSplashPresenter;
    private Handler handler = new Handler() {
    };

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents() {
        mSplashPresenter = new SplashIPresenter(this);
        mSplashPresenter.attachView(this);
        mSplashPresenter.initialized();
        StatusBarUtil.setTranslucent(this, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.detachView();
    }

    //==========ISplashView Implements Method================

    @Override
    public void initViews(String copyright, String versionName, int bgResId) {
        mTxtCopyright.setText(copyright);
        mTxtVersioncode.setText(versionName);
        mImgBg.setBackgroundResource(bgResId);
    }

    @Override
    public void goHomePage() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                go(WelcomeActivity.class);
            }
        }, 2000);
    }
}
