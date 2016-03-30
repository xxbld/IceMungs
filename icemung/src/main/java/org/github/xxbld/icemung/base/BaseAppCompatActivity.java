package org.github.xxbld.icemung.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.github.xxbld.icemung.R;
import org.github.xxbld.icemung.netsatus.NetStatusObserver;
import org.github.xxbld.icemung.netsatus.NetStatusReceiver;
import org.github.xxbld.icemung.netsatus.NetUtils;
import org.github.xxbld.icemung.utils.TextUtil;
import org.github.xxbld.icemung.widget.loading.VaryViewHelperController;

import butterknife.ButterKnife;

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    /**
     * 日志标记
     */
    protected static String TAG = null;
    /**
     * ScreenInfo
     */
    protected static int mScreenWidth = 0;
    protected static int mScreenHeight = 0;
    protected static float mScreenDensity = 0.0f;
    /**
     * Context
     */
    protected Context mContext = null;

    /**
     * NetStatusObserver  需要注册 receiver
     */
    protected NetStatusObserver mNetStatusObserver;
    /**
     * loading view controller
     */
    private VaryViewHelperController mVaryViewHelperController = null;

    /**
     * overridePendingTransition Mode
     * Activity 切换模式
     */
    public enum TransitionMode {
        TOP, BOTTOM, LEFT, RIGHT, SCALE, FADE, RIGHT_LEFT, LEFT_RIGHT, TOP_BOTTOM, BOTTOM_TOP
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Activity切换模式设置-在onCreate之前
        setOverridePendingTransition();
        super.onCreate(savedInstanceState);

        Bundle extras = this.getIntent().getExtras();
        if (null != extras) {
            handleBundleExtras(extras);
        }

        mContext = this;
        TAG = this.getClass().getSimpleName();
        ActivityManager.getInstance().addActivity(this);

        if (mScreenDensity == 0.0f && mScreenHeight == 0 && mScreenWidth == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            mScreenDensity = displayMetrics.density;
            mScreenHeight = displayMetrics.heightPixels;
            mScreenWidth = displayMetrics.widthPixels;
        }

        if (getContentViewLayoutResID() != 0) {
            setContentView(getContentViewLayoutResID());
        } else {
            throw new IllegalArgumentException("you must return a right layout resource id");
        }

        mNetStatusObserver = new NetStatusObserver() {
            @Override
            public void onNetWorkConnected(NetUtils.NetType netType) {
                super.onNetWorkConnected(netType);
                //on net connected callback
                onNetConnected(netType);
            }

            @Override
            public void onNetWorkDisConnected() {
                super.onNetWorkDisConnected();
                //on net disconnected callback
                onNetDisConnected();
            }
        };
        NetStatusReceiver.registerNetStatusObserver(mNetStatusObserver);

        //last initViewsAndEvents
        initViewsAndEvents();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (getLoadingTargetView() != null) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
    }

    @Override
    public void finish() {
        super.finish();
        ActivityManager.getInstance().removeActivity(this);
        setOverridePendingTransition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        NetStatusReceiver.unRegisterNetStatusObserver(mNetStatusObserver);
    }

    /**
     * go to an other Activity
     *
     * @param clazz
     */
    protected void go(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * go to an other Activity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void go(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * go to an other Activity then kill this
     *
     * @param clazz
     */
    protected void goThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        this.finish();
    }

    /**
     * go to another Activity with bundle then kill this
     *
     * @param clazz
     * @param bundle
     */
    protected void goThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.finish();
    }

    /**
     * go to an another Activity for result
     *
     * @param clazz
     * @param requestCode
     */
    protected void goForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * go to an another Activity for result with bundle
     *
     * @param clazz
     * @param requestCode
     */
    protected void goForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading(msg);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * get SystemBarTintManager Enable
     *
     * @param activity
     * @return
     */
    protected SystemBarTintManager getSystemBarTintManagerEnable(Activity activity, boolean isNeedNavigation) {
        SystemBarTintManager mTintManager = new SystemBarTintManager(activity);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(isNeedNavigation);
        return mTintManager;
    }

    /**
     * set status bar translucency(状态栏半透明)
     *
     * @param on
     */
    @Deprecated
    private void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    protected void showToast(String msg) {
        if (!TextUtil.isEmpty(msg)) {
            Snackbar.make(getLoadingTargetView(), msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * 设置切换
     */
    private void setOverridePendingTransition() {
        if (isOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                case RIGHT_LEFT:
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    break;
                case LEFT_RIGHT:
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    break;
                case TOP_BOTTOM:
                    overridePendingTransition(R.anim.top_in, R.anim.bottom_out);
                    break;
                case BOTTOM_TOP:
                    overridePendingTransition(R.anim.bottom_in, R.anim.top_out);
                    break;
            }
        }
    }

    /**
     * is need set overridePendingTransition
     *
     * @return
     */
    protected abstract boolean isOverridePendingTransition();

    /**
     * 设置切换模式
     *
     * @return TransitionMode
     */
    protected abstract TransitionMode getOverridePendingTransitionMode();

    /**
     * 设置 layout res id
     *
     * @return layout id
     */
    protected abstract int getContentViewLayoutResID();

    /**
     * getLoadingTargetView
     *
     * @return
     */
    protected abstract View getLoadingTargetView();

    /**
     * 处理 bundle extras
     *
     * @param extras
     */
    protected abstract void handleBundleExtras(Bundle extras);

    /**
     * onNetWorkConnected
     *
     * @param netType
     */
    protected abstract void onNetConnected(NetUtils.NetType netType);

    /**
     * onNetWorkDisConnected
     */
    protected abstract void onNetDisConnected();

    /**
     * init views, events
     */
    protected abstract void initViewsAndEvents();
}
