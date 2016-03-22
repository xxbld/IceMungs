package org.github.xxbld.icemung.base.mvp;

/**
 * Created by xxbld on 2016/3/1
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：BasePresenter
 */
public class BasePresenter<T extends IMvpView> implements IPresenter<T> {

    private T mMvpView;

    @Override
    public void initialized() {
        checkViewAttached();
    }

    @Override
    public void attachView(T mvpView) {
        this.mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    /**
     * 是否关联 Mvp View
     *
     * @return
     */
    public boolean isViewAttached() {
        return mMvpView == null ? false : true;
    }

    public T getMvpView() {
        return mMvpView;
    }

    /**
     * check viewAttached,if false throw MvpViewNoAttachedException
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNoAttachedException();
        }
    }

    public static class MvpViewNoAttachedException extends RuntimeException {
        public MvpViewNoAttachedException() {
            super("Please call IPresenter.attachView(IMvpView) before" +
                    " requesting data to the IPresenter");
        }
    }
}
