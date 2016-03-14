package org.github.xxbld.icemungs.interactors.impl;

import android.content.Context;

import org.github.xxbld.icemung.utils.AppUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.interactors.ISplashInteractor;

import java.util.Calendar;

/**
 * Created by xxbld on 2016/2/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class SplashInteractorImpl implements ISplashInteractor {

    @Override
    public String getCopyRight(Context context) {
        return context.getResources().getString(R.string.splash_copyright);
    }

    @Override
    public String getVersionCode(Context context) {
        return AppUtil.getVersionName(context);
    }

    @Override
    public int getImageResourceId() {
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
