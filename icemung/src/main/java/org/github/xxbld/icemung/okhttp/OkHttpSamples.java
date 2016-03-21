package org.github.xxbld.icemung.okhttp;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Request;

/**
 * Created by xxbld on 2016/1/16.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class OkHttpSamples {
    public static void demoGet() {
//        http://blog.csdn.net/lmj623565791/article/details/49734867
        String url = "http://www.csdn.net/";
        OkHttpUtils
                .get()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });
    }
}
