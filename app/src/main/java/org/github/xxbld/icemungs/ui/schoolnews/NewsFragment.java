package org.github.xxbld.icemungs.ui.schoolnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.github.xxbld.icemung.base.BaseWebActivity;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.News;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.listeners.OnNewsLoadListener;
import org.github.xxbld.icemungs.presenters.FragNewsPresenter;
import org.github.xxbld.icemungs.ui.adapter.NewsAdapter;
import org.github.xxbld.icemungs.ui.base.BasePtrClassicFragment;
import org.github.xxbld.icemungs.views.IFragNewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxbld on 2016/4/28.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 校级新闻
 */
public class NewsFragment extends BasePtrClassicFragment implements IFragNewsView {

    private static final String FRAG_NEWS_TYPE = "newsType";
    public static final String FRAG_STUDENT_ID = "studentId";

    int mNewsTypeTag;
    String mStudentId;
    Student mStudent;
    NewsAdapter mNewsAdapter;
    FragNewsPresenter mFragNewsPresenter;
    List<News> mNewses = new ArrayList<>();

    public NewsFragment() {
    }

    public static NewsFragment newInstance(String studentId, int newsTypeTag) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(FRAG_NEWS_TYPE, newsTypeTag);
        arguments.putString(FRAG_STUDENT_ID, studentId);
        newsFragment.setArguments(arguments);
        return newsFragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAG_NEWS_TYPE, mNewsTypeTag);
        outState.putString(FRAG_STUDENT_ID, mStudentId);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mNewsTypeTag = savedInstanceState.getInt(FRAG_NEWS_TYPE, 0);
            mStudentId = savedInstanceState.getString(FRAG_STUDENT_ID);
        }
    }


    @Override
    protected View getLoadingTargetView() {
        return mPtrClassicFrameLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsTypeTag = getArguments().getInt(FRAG_NEWS_TYPE, 0);
            mStudentId = getArguments().getString(FRAG_STUDENT_ID);
        }
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        //        BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
//        studentBmobQuery.include("school.newsLevel,academy.newsLevel");
//        studentBmobQuery.getObject(mContext, mStudentId, new GetListener<Student>() {
//            @Override
//            public void onSuccess(Student student) {
//                MLog.i(TAG, "S:" + student.getSchool().getNewsLevel().toString());
//                mPtrClassicFrameLayout.autoRefresh();
//                mStudent = student;
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//
//            }
//        });
        MLog.i(TAG, "mPtrClassicFrameLayout:" + mPtrClassicFrameLayout.toString());
        mStudent = new Student();
        mStudent.setObjectId(mStudentId);

        mFragNewsPresenter = new FragNewsPresenter(getActivity());
        mFragNewsPresenter.attachView(this);
        mFragNewsPresenter.initialized();
        mFragNewsPresenter.setOnNewsLoadListener(new OnNewsLoadListener() {
            @Override
            public void onSuccess() {
                setPtrRefreshComplete();
            }

            @Override
            public void onFailed(String msg) {
                MLog.i(TAG, "fail" + msg);
                setPtrRefreshComplete();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewsAdapter = new NewsAdapter(mRecyclerView, mNewses);
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
    public void onDestroy() {
        super.onDestroy();
        MLog.i(TAG, "onDestroy!");
        if (mFragNewsPresenter != null) {
            mFragNewsPresenter.detachView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected boolean isSetPtrLayout() {
        return true;
    }

    @Override
    protected void onPtrRefreshBegin() {
        MLog.i(TAG, "onPtrRefreshBegin");
        if (mStudent != null) {
            mFragNewsPresenter.loadNews(mStudent, mNewsTypeTag);
        }
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

    //========================
    @Override
    public void setRecyclerNews(List<News> newses) {
        if (mNewses.size() == 0) {
            mNewses.addAll(newses);
            mNewsAdapter.notifyDataSetChanged();
        }
    }
}
