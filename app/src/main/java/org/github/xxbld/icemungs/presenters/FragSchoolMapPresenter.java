package org.github.xxbld.icemungs.presenters;

import android.content.Context;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemungs.data.models.School;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.views.IFragSchoolMapView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/3/14
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class FragSchoolMapPresenter extends BasePresenter<IFragSchoolMapView> {

    Context mContext;

    public FragSchoolMapPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(IFragSchoolMapView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void initialized() {
    }

    /**
     * 附近的学校
     *
     * @param point
     */
    public void queryNearSchool(final String queryStr, BmobGeoPoint point) {
        if (!queryStr.contains("学校") && !queryStr.contains("school")) {
            return;
        }
        BmobQuery<School> schoolBmobQuery = new BmobQuery<>();
        if (point != null) {
            schoolBmobQuery.addWhereNear("schoolLocation", point);
        }
        schoolBmobQuery.setLimit(20);
        schoolBmobQuery.findObjects(mContext, new FindListener<School>() {
            @Override
            public void onSuccess(List<School> list) {
                if (list != null && list.size() > 0) {
                    getMvpView().showTextQuery(queryStr, list);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    /**
     * 附近的人
     *
     * @param point
     */
    public void queryNearPerson(BmobGeoPoint point) {
        BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
        studentBmobQuery.addWhereNear("location", point);
        studentBmobQuery.findObjects(mContext, new FindListener<Student>() {
            @Override
            public void onSuccess(List<Student> list) {
                if (list != null && list.size() > 0) {
                    getMvpView().showNearPerson(list);
                }
            }

            @Override
            public void onError(int i, String s) {
            }
        });
    }
}
