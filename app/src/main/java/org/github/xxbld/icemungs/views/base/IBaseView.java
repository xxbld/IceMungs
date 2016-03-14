package org.github.xxbld.icemungs.views.base;

/**
 * Created by xxbld on 2016/2/22
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：mvp base view
 */
public interface IBaseView {
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
