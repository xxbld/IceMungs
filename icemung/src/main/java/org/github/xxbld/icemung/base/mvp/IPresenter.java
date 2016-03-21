package org.github.xxbld.icemung.base.mvp;

/**
 * Created by xxbld on 2016/3/1
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface   IPresenter<T extends IMvpView> {
    /**
     * 关联 MVP view
     *
     * @param mvpView
     */
    void attachView(T mvpView);

    /**
     * 解除关联
     */
    void detachView();

    /**
     * 初始操作
     */
    void initialized();
}
