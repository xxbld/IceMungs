package org.github.xxbld.icemung.webservices;

/**
 * Created by xxbld on 2016/1/13.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :on Visit Webservice Listener
 */
public interface OnVisitListener {
    /**
     * result exist & not null
     *
     * @param result
     */
    void onResultFull(String result);


    /**
     * before visit Webservice
     */
    void onPre();

    /**
     * connect to server error
     */
    void onConnectFailed();

    /**
     * exist Exception
     *
     * @param exceptionMsg
     */
    void onError(String exceptionMsg);
}
