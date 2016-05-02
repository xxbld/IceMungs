package org.github.xxbld.icemungs.ui.fragment;

import android.view.View;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BasePtrClassicFragment;

/**
 * Created by xxbld on 2016/4/28.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class PtrFragment extends BasePtrClassicFragment {

    @Override
    protected View getLoadingTargetView() {
        return super.getLoadingTargetView();
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
//        boolean recNull = mRecyclerView == null;
//        MLog.i(TAG, "recNull:" + recNull);
        mPtrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mPtrClassicFrameLayout.refreshComplete();
                setPtrRefreshComplete();
            }
        }, 2000);
    }

    @Override
    protected void onPtrRefreshBegin() {
        MLog.i(TAG, "onPtrRefreshBegin");
    }

    @Override
    protected boolean isPtrAutoRefresh() {
        return true;
    }

    @Override
    protected long getPtrAutoRefreshDelayMillis() {
        return 0;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_ptr_base;
    }
}
