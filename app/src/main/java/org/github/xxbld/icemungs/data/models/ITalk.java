package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 我有话说
 */
public class ITalk extends BmobObject {
    //标题
    private String iTalkTitle;
    //简介
    private String iTalkIntroduction;
    //内容
    private String iTalkContent;
    //位置
    private BmobGeoPoint iTalkLocation;
    //大图
    private BmobFile iTalkBigImage;
}
