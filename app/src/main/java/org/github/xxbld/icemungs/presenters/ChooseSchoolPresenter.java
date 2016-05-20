package org.github.xxbld.icemungs.presenters;

import android.content.Context;

import org.github.xxbld.icemung.base.mvp.BasePresenter;
import org.github.xxbld.icemungs.data.models.Academy;
import org.github.xxbld.icemungs.data.models.School;
import org.github.xxbld.icemungs.listeners.OnLoadDataListener;
import org.github.xxbld.icemungs.views.IChooseSchoolView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/5/19.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class ChooseSchoolPresenter extends BasePresenter<IChooseSchoolView> {

    private final Context mContext;
    OnLoadDataListener mOnLoadDataListener;


    public ChooseSchoolPresenter(Context context) {
        mContext = context;
    }

    public void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        mOnLoadDataListener = onLoadDataListener;
    }


    public void loadAcademy(String schoolID) {
        if (schoolID == null) {
            return;
        }
        School school = new School();
        school.setObjectId(schoolID);
        BmobQuery<Academy> academyBmobQuery = new BmobQuery<>();
        academyBmobQuery.addWhereEqualTo("school", new BmobPointer(school));
        academyBmobQuery.findObjects(mContext, new FindListener<Academy>() {
            @Override
            public void onSuccess(List<Academy> list) {
                if (mOnLoadDataListener != null) {
                    mOnLoadDataListener.onSuccess();
                }
                getMvpView().setRecycler(list);
            }

            @Override
            public void onError(int i, String s) {
                if (mOnLoadDataListener != null) {
                    mOnLoadDataListener.onFailed(s);
                }
            }
        });
    }

    /**
     * 全部学校
     */
    public void loadSchool() {
        loadSchoolNearBy(null);
    }

    /**
     * 附近的学校
     *
     * @param point
     */
    public void loadSchoolNearBy(BmobGeoPoint point) {
        BmobQuery<School> schoolBmobQuery = new BmobQuery<>();
        if (point != null) {
            schoolBmobQuery.addWhereNear("schoolLocation", point);
            schoolBmobQuery.setLimit(20);
        }
        schoolBmobQuery.findObjects(mContext, new FindListener<School>() {
            @Override
            public void onSuccess(List<School> list) {
                if (mOnLoadDataListener != null) {
                    mOnLoadDataListener.onSuccess();
                }
                getMvpView().setRecycler(list);
            }

            @Override
            public void onError(int i, String s) {
                if (mOnLoadDataListener != null) {
                    mOnLoadDataListener.onFailed(s);
                }
            }
        });
    }

    @Override
    public void initialized() {
        super.initialized();
    }

    @Override
    public void attachView(IChooseSchoolView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}
