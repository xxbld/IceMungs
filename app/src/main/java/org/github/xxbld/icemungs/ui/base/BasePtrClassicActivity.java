package org.github.xxbld.icemungs.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.github.xxbld.icemungs.R;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by xxbld on 2016/4/29.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :下拉刷新经典样式 with a RecyclerView
 */
public abstract class BasePtrClassicActivity extends BaseActivity {

    private static final long DEFAULT_DELAY_MILLIS = 150;
    protected PtrClassicFrameLayout mPtrClassicFrameLayout;
    protected RecyclerView mRecyclerView;

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        mPtrClassicFrameLayout = ButterKnife.findById(this, R.id.common_ptr_frame);
        mRecyclerView = ButterKnife.findById(this, R.id.common_recycler_view);
        if (isSetPtrLayout()) {
            setPtr();
        }

    }

    private void setPtr() {
        if (mPtrClassicFrameLayout == null) {
            throw new IllegalArgumentException("your xml layout is not contain PtrClassicFrameLayout " +
                    "that's id is R.id.common_ptr_frame default,can't @+id/");
        } else {
            mPtrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
            mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    onPtrRefreshBegin();
                }

                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }
            });
//            // the following are default settings
//            mPtrClassicFrameLayout.setResistance(1.7f);
//            mPtrClassicFrameLayout.setRatioOfHeaderHeightToRefresh(1.2f);
//            mPtrClassicFrameLayout.setDurationToClose(200);
//            mPtrClassicFrameLayout.setDurationToCloseHeader(1000);
//            // default is false
//            mPtrClassicFrameLayout.setPullToRefresh(false);
//            // default is true
//            mPtrClassicFrameLayout.setKeepHeaderWhenRefresh(true);
            setAutoRefresh();
        }
    }

    private void setAutoRefresh() {
        if (isPtrAutoRefresh()) {
            long delayMillis = getPtrAutoRefreshDelayMillis() >= DEFAULT_DELAY_MILLIS ? getPtrAutoRefreshDelayMillis() : DEFAULT_DELAY_MILLIS;
            mPtrClassicFrameLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPtrClassicFrameLayout.autoRefresh();
                }
            }, delayMillis);
        }
    }

    protected void setPtrRefreshComplete() {
        mPtrClassicFrameLayout.refreshComplete();
    }

    protected abstract boolean isSetPtrLayout();

    /**
     * 下拉刷新开始
     */
    protected abstract void onPtrRefreshBegin();

    /**
     * 是否第一次自动刷新
     */
    protected abstract boolean isPtrAutoRefresh();

    /**
     * @return 延迟时间 >=150
     */
    protected abstract long getPtrAutoRefreshDelayMillis();
}
