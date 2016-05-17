package org.github.xxbld.icemungs.presenters;

import android.content.Context;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.data.models.News;
import org.github.xxbld.icemungs.data.models.NewsLevel;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.listeners.OnNewsLoadListener;
import org.github.xxbld.icemungs.views.IFragNewsView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class FragNewsPresenter extends BasePresenter<IFragNewsView> {

    Context mContext;
    OnNewsLoadListener mOnNewsLoadListener;

    public FragNewsPresenter(Context context) {
        mContext = context;
        MLog.i(TAG, "news FragNewsPresenter");
    }

    @Override
    public void attachView(IFragNewsView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void initialized() {
        super.initialized();
    }

    public void setOnNewsLoadListener(OnNewsLoadListener loadListener) {
        this.mOnNewsLoadListener = loadListener;
    }

    /**
     * 加载新闻
     *
     * @param student
     * @param newsTypeTag
     */
    public void loadNews(Student student, int newsTypeTag) {
        MLog.i(TAG, "newsTypeTag:" + newsTypeTag);
        getNews(student, newsTypeTag);
    }

    /**
     * @param student
     */
    private void getNews1(Student student, int newsTypeTag) {
        BmobQuery<NewsLevel> newsLevelBmobQuery = new BmobQuery<>();
        if (newsTypeTag == NewsType.NEW.getValue()) {
            newsLevelBmobQuery.addWhereGreaterThan("levelCode", 0);
        } else if (newsTypeTag == NewsType.HOT.getValue()) {
            newsLevelBmobQuery.addWhereGreaterThan("levelCode", 0);
        } else if (newsTypeTag == NewsType.SCHOOL.getValue()) {
            newsLevelBmobQuery.addWhereEqualTo("levelCode", 1);
        } else if (newsTypeTag == NewsType.ACADEMY.getValue()) {
            newsLevelBmobQuery.addWhereEqualTo("levelCode", 2);
        } else if (newsTypeTag == NewsType.SPECIALITY.getValue()) {
            newsLevelBmobQuery.addWhereEqualTo("levelCode", 4);
        }
        newsLevelBmobQuery.findObjects(mContext, new FindListener<NewsLevel>() {
            @Override
            public void onSuccess(List<NewsLevel> list) {
                MLog.i(TAG, "news:" + list.size());
                NewsLevel newsLevel = list.get(0);
                BmobQuery<News> newsBmobQuery = new BmobQuery<>();
                newsBmobQuery.addWhereEqualTo("newsLevel", new BmobPointer(newsLevel));
                newsBmobQuery.findObjects(mContext, new FindListener<News>() {
                    @Override
                    public void onSuccess(List<News> list) {
                        if (mOnNewsLoadListener != null) {
                            mOnNewsLoadListener.onSuccess();
                        }
                        getMvpView().setRecyclerNews(list);
                    }

                    @Override
                    public void onError(int i, String s) {
                        if (mOnNewsLoadListener != null) {
                            mOnNewsLoadListener.onFailed(s);
                        }
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                if (mOnNewsLoadListener != null) {
                    mOnNewsLoadListener.onFailed(s);
                }
            }
        });
    }

    /**
     * @param student
     */
    private void getNews(final Student student, final int newsTypeTag) {
        BmobQuery<News> newsBmobQuery = new BmobQuery<>();
//        newsBmobQuery.addWhereGreaterThan("levelCode", 0);
//        if (newsTypeTag == NewsType.NEW.getValue()) {
//            newsBmobQuery.addWhereGreaterThan("levelCode", 0);
//        } else if (newsTypeTag == NewsType.HOT.getValue()) {
//            newsBmobQuery.addWhereGreaterThan("levelCode", 0);
//        } else if (newsTypeTag == NewsType.SCHOOL.getValue()) {
//            newsBmobQuery.addWhereEqualTo("levelCode", 1);
//        } else if (newsTypeTag == NewsType.ACADEMY.getValue()) {
//            newsBmobQuery.addWhereEqualTo("levelCode", 2);
//        } else if (newsTypeTag == NewsType.SPECIALITY.getValue()) {
//            newsBmobQuery.addWhereEqualTo("levelCode", 4);
//        }

        newsBmobQuery.findObjects(mContext, new FindListener<News>() {
            @Override
            public void onSuccess(List<News> list) {
                MLog.i(TAG, "news:" + list.size());
                if (mOnNewsLoadListener != null) {
                    mOnNewsLoadListener.onSuccess();
                }
                getMvpView().setRecyclerNews(list);
            }

            @Override
            public void onError(int i, String s) {
                if (mOnNewsLoadListener != null) {
                    mOnNewsLoadListener.onFailed(s);
                }
            }
        });
    }

    @Deprecated
    private void upNewLevel(Context context) {

        NewsLevel newsLevelsc = new NewsLevel();
        newsLevelsc.setObjectId("mTL0XXX1");
        News n1 = new News();
        n1.setObjectId("0GLU888b");
        BmobRelation r1 = new BmobRelation();
        r1.add(n1);
        newsLevelsc.setNews(r1);
        newsLevelsc.update(context);

        NewsLevel newsLevelac = new NewsLevel();
        newsLevelac.setObjectId("P7hkMMM8");
        News n2 = new News();
        n2.setObjectId("56EA000C");
        BmobRelation r2 = new BmobRelation();
        r2.add(n2);
        newsLevelac.setNews(r2);
        newsLevelac.update(context);

        NewsLevel newsLevelspG = new NewsLevel();
        newsLevelspG.setObjectId("kz6SUUUZ");
        News n3 = new News();
        n3.setObjectId("o2S6IIIK");
        BmobRelation r3 = new BmobRelation();
        r3.add(n3);
        newsLevelspG.setNews(r3);
        newsLevelspG.update(context);

//        NewsLevel newsLevelch = new NewsLevel();
//        newsLevelspG.setObjectId("SvB8444A");
//        News n4 = new News();
//        n4.setObjectId("0GLU888b");
//        BmobRelation r4 = new BmobRelation();
//        r4.add(n4);
//        newsLevelch.setNews(r4);
    }

    /**
     * 新闻类型
     */
    public enum NewsType {
        NEW(0), HOT(1), SCHOOL(2), ACADEMY(3), SPECIALITY(4);
        private int value;

        NewsType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
