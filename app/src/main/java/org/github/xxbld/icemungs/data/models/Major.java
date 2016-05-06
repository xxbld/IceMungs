package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 修学专业
 */
public class Major extends BmobObject {

    private BmobDate inDate;  //开始专业修学日期
    private BmobDate outDate;  //修满专业修学日期
    private Boolean isMainMajor; //是否是主修专业

    private Student student;
    private Speciality speciality;
}
