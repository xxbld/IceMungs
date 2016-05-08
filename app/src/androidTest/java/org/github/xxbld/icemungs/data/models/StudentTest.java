package org.github.xxbld.icemungs.data.models;

import android.test.AndroidTestCase;

import cn.bmob.v3.BmobUser;

/**
 * Created by xxbld on 2016/5/5.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class StudentTest extends AndroidTestCase {
    public void updateStudent() {
        Student currentUser = BmobUser.getCurrentUser(getContext(), Student.class);
        currentUser.setAge(21);
        currentUser.setSex(0);
        currentUser.setAddress("江西省南昌市");
//        getContext().getResources().getDrawable(R.drawable.ic_user);
//        currentUser.setIconUrl(new BmobFile(new File()));
        currentUser.update(getContext());
    }

}