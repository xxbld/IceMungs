package org.github.xxbld.icemung.webservices;


import android.util.Log;

import junit.framework.TestCase;

/**
 * Created by xxbld on 2016/3/11
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class WebServicesHelperTest extends TestCase {

    private static final String TAG = "WebServicesHelperTest";

    public void testGetWebServiceAsyn() throws Exception {
        WebServicesParameter parameter = new WebServicesParameter();
        parameter.nameSpace = "http://WebXml.com.cn/";
        parameter.webserviceUrl = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        parameter.methodName = "getMobileCodeInfo";
        parameter.setKeysAndValues(new String[]{"mobileCode", "userID"}, new String[]{"18146688495", ""});

        WebServicesTask wsTask = WebServicesHelper.getInstance().getWebServiceAsyn(new WebServicesCallback() {
            @Override
            public void onResult(String result) {
                Log.i(TAG, "res:" + result);
            }

            @Override
            public void onErr(String exceptionMsg) {
                Log.i(TAG, "res:" + exceptionMsg);
            }
        });
        wsTask.execute(parameter);
    }
}