package org.github.xxbld.icemung.webservices;

/**
 * Created by xxbld on 2016/1/13.
 * you can contact me at: 1024920618@qq.com
 *
 * @description ：help to visit webservice ,default dotNet WebService beside get json from server
 */
public class WebServicesHelper {
    public static final String TAG = WebServicesHelper.class.getSimpleName();
    private static WebServicesHelper mHelper = null;

    private WebServicesHelper() {
//        throw new ExceptionInInitializerError("you must getInstance to get a WebServiceHelper instance");
    }

    /**
     * get instance
     *
     * @return
     */
    public static WebServicesHelper getInstance() {
        if (mHelper == null) {
            synchronized (WebServicesHelper.class) {
                if (mHelper == null) {
                    mHelper = new WebServicesHelper();
                    return mHelper;
                }
            }
        }
        return null;
    }

    /**
     * get a WebService AsyncTask
     *
     * @param callback
     * @return
     */
    public WebServicesTask getWebServiceAsyn(WebServicesCallback callback) {
        if (callback != null) {
            return new WebServicesTask(callback);
        }
        return null;
    }

}
