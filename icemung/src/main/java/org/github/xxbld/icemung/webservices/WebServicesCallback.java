package org.github.xxbld.icemung.webservices;

/**
 * Created by xxbld on 2016/1/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public abstract class WebServicesCallback implements OnVisitListener {
    @Override
    public void onResultFull(String result) {
        onResult(result);
    }

    @Override
    public void onPre() {

    }

    @Override
    public void onConnectFailed() {

    }

    @Override
    public void onError(String exceptionMsg) {
        onErr(exceptionMsg);
    }

    public abstract void onResult(String result);

    public abstract void onErr(String exceptionMsg);
}
