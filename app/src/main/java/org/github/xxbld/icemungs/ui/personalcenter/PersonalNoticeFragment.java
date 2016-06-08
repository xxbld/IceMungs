package org.github.xxbld.icemungs.ui.personalcenter;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.ui.base.BaseFragment;

import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/6/4.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class PersonalNoticeFragment extends BaseFragment {
    public static final String STUDENT_ID = "student_id";

    @Bind(R.id.common_recycler_view)
    RecyclerView mCommonRecyclerView;
    @Bind(R.id.personal_root)
    LinearLayout mRoot;

    private String mStudentID;

    public PersonalNoticeFragment() {
    }

    public static PersonalNoticeFragment newInstance(String studentOID) {
        PersonalNoticeFragment personalNoticeFragment = new PersonalNoticeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(STUDENT_ID, studentOID);
        personalNoticeFragment.setArguments(bundle);
        return personalNoticeFragment;
    }

    @Override
    protected View getLoadingTargetView() {
        return mRoot;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        toggleShowLoading(true, "加载中...");
        Bundle arguments = getArguments();
        if (arguments != null) {
            mStudentID = arguments.getString(STUDENT_ID);
            MLog.i(TAG, "mStudentID" + mStudentID);
        }
        if (mStudentID == null) {
            Student currentUser = BmobUser.getCurrentUser(getActivity(), Student.class);
            mStudentID = currentUser.getObjectId();
        }
        BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
        studentBmobQuery.addWhereNotEqualTo("objectId", mStudentID);
        studentBmobQuery.findObjects(getActivity(), new FindListener<Student>() {
            @Override
            public void onSuccess(List<Student> list) {
                showUserNotice(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    private void showUserNotice(List<Student> notices) {
        mCommonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCommonRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mCommonRecyclerView.setAdapter(new BaseRecyclerViewAdapter<Student>(mCommonRecyclerView, notices, R.layout.item_personal_notice) {

            @Override
            public void convert(BaseRecyclerHolder holder, Student item, int position, boolean isScrolling) {
                if (item.getIconUrl() != null) {
                    GlideHelper.tranCircleImage(cxt, item.getIconUrl(), (ImageView) holder.getView(R.id.item_personal_notice_img));
                } else {
                    GlideHelper.tranCircleImage(cxt, R.drawable.ic_user, (ImageView) holder.getView(R.id.item_personal_notice_img));
                }
                holder.setText(R.id.item_personal_notice_username, item.getUsername());
            }
        });
        toggleShowLoading(false, null);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_personal_data;
    }
}
