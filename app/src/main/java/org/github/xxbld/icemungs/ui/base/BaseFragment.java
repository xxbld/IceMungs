package org.github.xxbld.icemungs.ui.base;

import android.view.View;

import org.github.xxbld.icemung.base.BaseAppFragment;
import org.github.xxbld.icemung.base.mvp.IMvpView;

/**
 * Created by xxbld on 2016/3/23
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public abstract class BaseFragment extends BaseAppFragment implements IMvpView {
    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    //===========impl
    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showException(String msg) {

    }

    @Override
    public void showNetError() {

    }
}
