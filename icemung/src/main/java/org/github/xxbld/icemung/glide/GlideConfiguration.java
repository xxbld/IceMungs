package org.github.xxbld.icemung.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by xxbld on 2016/1/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :Glide默认的Bitmap格式是RGB_565 这里变成ARGB_8888(内存开销是前面的2倍)格式
 * 同时在AndroidManifest.xml中将GlideModule定义为meta-data
 * <p>
 * <meta-data android:name="com.github.xxbld.templelibrary.glide.GlideConfiguration"
 * android:value="GlideModule"/>
 * </p>
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}
