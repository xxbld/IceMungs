package org.github.xxbld.icemungs.views;

import org.github.xxbld.icemung.base.mvp.IMvpView;
import org.github.xxbld.icemungs.data.models.Student;

import java.util.List;

/**
 * Created by xxbld on 2016/3/29
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface IFragSchoolMapView extends IMvpView {

    /**
     * 显示附近的人
     *
     * @param students
     */
    void showNearPerson(List<Student> students);
}
