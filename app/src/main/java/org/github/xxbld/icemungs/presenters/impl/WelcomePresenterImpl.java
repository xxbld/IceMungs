package org.github.xxbld.icemungs.presenters.impl;

import android.content.Context;

import org.github.xxbld.icemungs.interactors.IWelcomeInteractor;
import org.github.xxbld.icemungs.interactors.impl.WelcomeInteactorImpl;
import org.github.xxbld.icemungs.presenters.IPresenter;
import org.github.xxbld.icemungs.views.IWelcomeView;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class WelcomePresenterImpl implements IPresenter {

    private IWelcomeView mWelcomeView;
    private Context mContext;
    private IWelcomeInteractor mWelcomeInteractor;

    public WelcomePresenterImpl(Context context,IWelcomeView welcomeView) {
        if (null == welcomeView){
            throw new IllegalArgumentException("Constructor's parameters must not be Null");
        }
        this.mContext=context;
        this.mWelcomeView=welcomeView;
        mWelcomeInteractor= new WelcomeInteactorImpl();
    }

    @Override
    public void initialized() {
        mWelcomeView.setTranslucentStatus();
        mWelcomeView.initViewPager(
                mWelcomeInteractor.getViewPagerAdapter(mContext));

        int[] selects=mWelcomeInteractor.getDotsViewSelect();
        mWelcomeView.initDotsView(selects[0], selects[1], mWelcomeInteractor.getPageNum());
    }
}
