package org.github.xxbld.icemungs.ui.resume;

import android.view.View;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BasePtrClassicFragment;

import butterknife.ButterKnife;

/**
 * Created by xxbld on 2016/4/28.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class ResumeFragment extends BasePtrClassicFragment {
    View tab;

    @Override
    protected View getLoadingTargetView() {
        return mPtrClassicFrameLayout;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        tab = ButterKnife.findById(getActivity(), R.id.content_tab);
        setTab();
        mPtrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
//                mPtrClassicFrameLayout.refreshComplete();
                setPtrRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onResume() {
        super.onResume();
//        setTab();
    }

    private void setTab() {
        if (tab != null) {
            if (tab.getVisibility() == View.VISIBLE) {
                tab.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected boolean isSetPtrLayout() {
        return true;
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
