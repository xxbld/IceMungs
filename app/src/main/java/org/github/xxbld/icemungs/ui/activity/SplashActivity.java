package org.github.xxbld.icemungs.ui.activity;

import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.presenters.SplashPresenter;
import org.github.xxbld.icemungs.ui.base.BaseActivity;
import org.github.xxbld.icemungs.views.ISplashView;

import butterknife.Bind;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class SplashActivity extends BaseActivity implements ISplashView {

    @Bind(R.id.splash_frame)
    View mRevealView;
    @Bind(R.id.splash_img_bg)
    ImageView mImgBg;
    @Bind(R.id.splash_txt_versioncode)
    TextView mTxtVersioncode;
    @Bind(R.id.splash_txt_copyright)
    TextView mTxtCopyright;

    private SplashPresenter mSplashPresenter;
    private Handler handler = new Handler() {
    };

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents() {
        this.setStatusBarTranslucent(false);
        mSplashPresenter = new SplashPresenter(this);
        mSplashPresenter.attachView(this);
        mSplashPresenter.initialized();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setReveal();
        }
    }

    private void setReveal() {
        // get the center for the clipping circle
        int cx = (mRevealView.getLeft() + mRevealView.getRight()) / 2;
        int cy = (mRevealView.getTop() + mRevealView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int dx = Math.max(cx, mRevealView.getWidth() - cx);
        int dy = Math.max(cy, mRevealView.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1500);
        animator.start();
    }

    @Override
    protected void setToolbar() {

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
                goThenKill(WelcomeActivity.class);
            }
        }, 1500);
    }
//    private void loginTest() {
//        BmobUser.loginByAccount(this, "小小冰绿豆", "123065", new LogInListener<BmobUser>() {
//            @Override
//            public void done(BmobUser student, BmobException e) {
//
//                MLog.i(TAG, "msg" + e.getMessage());
//            }
//        });
//}
}
