package org.github.xxbld.icemungs.presenters;

import android.content.Context;

import org.github.xxbld.icemung.base.mvp.BaseIPresenter;
import org.github.xxbld.icemung.utils.AppUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.views.ISplashView;

import java.util.Calendar;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class SplashPresenter extends BaseIPresenter<ISplashView> {
    private Context mContext;

    public SplashPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(ISplashView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void initialized() {
        getMvpView().initViews(getCopyRight(mContext), getVersionCode(mContext), getImageResourceId());

        getMvpView().goHomePage();
    }

    private String getCopyRight(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }

    private String getVersionCode(Context context) {
        return AppUtil.getVersionName(context);
    }

    private int getImageResourceId() {
        int resId;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (6 <= hour && hour <= 12) {
//            早上
            resId = R.mipmap.material_design_2;
        } else if (12 <= hour && hour <= 18) {
//            中午
            resId = R.mipmap.material_design_3;
        } else {
//            晚上
            resId = R.mipmap.material_design_4;
        }
        return resId;
    }
}
