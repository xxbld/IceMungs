package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 新闻层级
 */
public class NewsLevel extends BmobObject {

//    private final static Integer

    private School school;
    private Academy academy;
    private Speciality speciality;
    private Integer levelCode;
    private BmobRelation news;//Relation 表示 1：多 的关联，
}
