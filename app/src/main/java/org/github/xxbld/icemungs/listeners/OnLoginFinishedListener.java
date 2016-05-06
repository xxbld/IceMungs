package org.github.xxbld.icemungs.listeners;

import org.github.xxbld.icemungs.data.models.Student;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface OnLoginFinishedListener {
    void onSuccess(Student student);

    void onUserNameErr(String msg);

    void onPasswordErr(String msg);

    void onFailure(int failureCode, String msg);
}
