package org.github.xxbld.icemung.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

/**
 * Created by xxbld on 2016/4/28.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class BitmapUtil {
    public static RoundedBitmapDrawable getCircleDrawable(Context context, Bitmap bitmapResource) {
        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(context.getResources(), bitmapResource);
        circularBitmapDrawable.setCornerRadius(Math.max(bitmapResource.getWidth(), bitmapResource.getHeight()) / 2.0f);
        circularBitmapDrawable.setAntiAlias(true); //设置反走样
        return circularBitmapDrawable;
    }
}
