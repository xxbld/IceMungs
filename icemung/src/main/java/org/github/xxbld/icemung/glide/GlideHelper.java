package org.github.xxbld.icemung.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by xxbld on 2016/1/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class  GlideHelper {
    //    Glide.with(myFragment)
//         .load(url)
//         .centerCrop()//居中
//         .placeholder(R.drawable.loading_spinner)//设置占位图
//         .error(R....)//设置加载错误图
//         .transform(new CircleTransform(context))
//         .crossFade()
//         .into(myImageView);

    /**
     * 圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void tranCircleImage(final Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(Math.max(resource.getWidth(), resource.getHeight()) / 2.0f);
                circularBitmapDrawable.setAntiAlias(true); //设置反走样
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
