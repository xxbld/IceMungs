package org.github.xxbld.icemungs.ui.personalcenter;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.data.models.notbmob.PersonalDataItem;
import org.github.xxbld.icemungs.ui.base.BaseFragment;
import org.github.xxbld.icemungs.ui.widgets.datetimepicker.DateTimePickerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by xxbld on 2016/6/4.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 个人资料
 */
public class PersonalDataFragment extends BaseFragment {

    public static final String STUDENT_ID = "student_id";

    @Bind(R.id.common_recycler_view)
    RecyclerView mCommonRecyclerView;
    @Bind(R.id.personal_root)
    LinearLayout mRoot;

    private String mStudentID;
    private Student mStudent;
    private DatePickerDialog mDatePicker;

    public PersonalDataFragment() {
    }

    public static PersonalDataFragment newInstance(String studentOID) {
        PersonalDataFragment personalDataFragment = new PersonalDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString(STUDENT_ID, studentOID);
        personalDataFragment.setArguments(bundle);
        return personalDataFragment;
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
        }
        mDatePicker = DateTimePickerHelper.getDatePicker(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

            }
        });
        Student currentUser = BmobUser.getCurrentUser(getActivity(), Student.class);
        if (mStudentID == null || mStudentID.equals(currentUser.getObjectId())) {
            mStudent = currentUser;
            showUserData();
        } else {
            BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
            studentBmobQuery.include("school,academy");
            studentBmobQuery.getObject(getActivity(), mStudentID, new GetListener<Student>() {
                @Override
                public void onSuccess(Student student) {
                    mStudent = student;
                    showUserData();
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
    }

    private void showUserData() {
        List<PersonalDataItem> items = new ArrayList<>();
        PersonalDataItem item0 = new PersonalDataItem("用户名", mStudent.getUsername());
        items.add(item0);
        String sex = "男";
        if (mStudent.getSex() == 1) {
            sex = "女";
        }
        PersonalDataItem item1 = new PersonalDataItem("性别", sex);
        items.add(item1);
        PersonalDataItem item2 = new PersonalDataItem("年龄", mStudent.getAge().toString());
        items.add(item2);
        PersonalDataItem item3 = new PersonalDataItem("生日", mStudent.getBirthday().getDate());
        items.add(item3);
        PersonalDataItem item4 = new PersonalDataItem("地址", mStudent.getAddress());
        items.add(item4);
        if (mStudent.getSchool() != null && mStudent.getSchool().getObjectId() != null) {
            PersonalDataItem item5 = new PersonalDataItem("学校", mStudent.getSchool().getSchoolName());
            items.add(item5);
        }
        if (mStudent.getAcademy() != null && mStudent.getAcademy().getObjectId() != null) {
            PersonalDataItem item6 = new PersonalDataItem("学院", mStudent.getAcademy().getAcademyName());
            items.add(item6);
        }
        mCommonRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCommonRecyclerView.setItemAnimator(new DefaultItemAnimator());
        BaseRecyclerViewAdapter<PersonalDataItem> baseRecyclerViewAdapter = new BaseRecyclerViewAdapter<PersonalDataItem>(mCommonRecyclerView, items, R.layout.item_personal_data) {

            @Override
            public void convert(BaseRecyclerHolder holder, PersonalDataItem item, int position, boolean isScrolling) {
                holder.setText(R.id.item_personal_data1, item.item1);
                holder.setText(R.id.item_personal_data2, item.item2);
            }

        };
        mCommonRecyclerView.setAdapter(baseRecyclerViewAdapter);
        baseRecyclerViewAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                if (position == 3) {
                    mDatePicker.show(getActivity().getFragmentManager(), null);
                }
            }
        });
        toggleShowLoading(false, null);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_personal_data;
    }
}
