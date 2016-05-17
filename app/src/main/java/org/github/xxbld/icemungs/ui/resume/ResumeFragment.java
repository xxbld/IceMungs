package org.github.xxbld.icemungs.ui.resume;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.github.xxbld.icemung.base.BaseWebActivity;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.News;
import org.github.xxbld.icemungs.ui.adapter.NewsAdapter;
import org.github.xxbld.icemungs.ui.base.BasePtrClassicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/4/28.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class ResumeFragment extends BasePtrClassicFragment {
    View tab;

    List<News> mNewses = new ArrayList<>();
    NewsAdapter mNewsAdapter;

    @Override
    protected View getLoadingTargetView() {
        return mPtrClassicFrameLayout;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        tab = ButterKnife.findById(getActivity(), R.id.content_tab);
        setTab();
        mNewsAdapter = new NewsAdapter(mRecyclerView, mNewses);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                News news = (News) data;
                Bundle bundle = new Bundle();
                bundle.putBoolean(BaseWebActivity.BUNDLE_KEY_SHOW_BOTTOM_BAR, true);
                bundle.putString(BaseWebActivity.BUNDLE_KEY_TITLE, news.getNewsTitle());
                bundle.putString(BaseWebActivity.BUNDLE_KEY_URL, news.getNewsUrl());
                go(BaseWebActivity.class, bundle);
            }
        });
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        MLog.i(TAG, "Resume onPtrRefreshBegin");
        BmobQuery<News> newsBmobQuery = new BmobQuery<>();
        newsBmobQuery.findObjects(mContext, new FindListener<News>() {
            @Override
            public void onSuccess(List<News> list) {
                MLog.i(TAG, "Resume:" + list.size());
                if (mNewses.size() == 0) {
                    mNewses.addAll(list);
                    mNewsAdapter.refresh(mNewses);
                    mNewsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int i, String s) {
                MLog.i(TAG, "Resume:" + s);
                setPtrRefreshComplete();
            }
        });
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
