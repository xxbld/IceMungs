package org.github.xxbld.icemung.base.mvp;

/**
 * Created by xxbld on 2016/3/1
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IMvpView {
    /**
     * show loading
     * @param msg
     */
    void showLoading(String msg);

    /**
     * hide loading
     */
    void hideLoading();

    /**
     * show error msg
     * @param msg
     */
    void showError(String msg);

    /**
     * show exception msg
     * @param msg
     */
    void showException(String msg);

    /**
     * show net work error
     */
    void showNetError();
}
