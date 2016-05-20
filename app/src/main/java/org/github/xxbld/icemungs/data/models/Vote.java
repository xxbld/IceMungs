package org.github.xxbld.icemungs.data.models;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 投票
 */
public class Vote extends BmobObject {
    //标题
    private String voteTitle;
    //简介
    private String voteIntroduction;
    //内容
    private String voteContent;
    //位置
    private BmobGeoPoint voteLocation;
    //大图
    private String voteBigImageUrl;
    private BmobDate startDate;  //开始日期
    private BmobDate endDate;    //结束日期
    private Boolean voteIsMutil; // 是否多选
    private List<String> voteItem; //选项
}
