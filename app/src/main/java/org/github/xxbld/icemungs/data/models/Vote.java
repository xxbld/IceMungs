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
    //内容
    private String voteContent;
    //位置
    private BmobGeoPoint voteLocation;
    //大图
    private String voteImageUrl;
    private BmobDate startDate;  //开始日期
    private BmobDate endDate;    //结束日期
    private Boolean voteIsMutil; // 是否多选
    private List<String> voteItems; //选项

    private Student student;

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getVoteContent() {
        return voteContent;
    }

    public void setVoteContent(String voteContent) {
        this.voteContent = voteContent;
    }

    public BmobGeoPoint getVoteLocation() {
        return voteLocation;
    }

    public void setVoteLocation(BmobGeoPoint voteLocation) {
        this.voteLocation = voteLocation;
    }

    public String getVoteImageUrl() {
        return voteImageUrl;
    }

    public void setVoteImageUrl(String voteImageUrl) {
        this.voteImageUrl = voteImageUrl;
    }

    public BmobDate getStartDate() {
        return startDate;
    }

    public void setStartDate(BmobDate startDate) {
        this.startDate = startDate;
    }

    public BmobDate getEndDate() {
        return endDate;
    }

    public void setEndDate(BmobDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getVoteIsMutil() {
        return voteIsMutil;
    }

    public void setVoteIsMutil(Boolean voteIsMutil) {
        this.voteIsMutil = voteIsMutil;
    }

    public List<String> getVoteItems() {
        return voteItems;
    }

    public void setVoteItems(List<String> voteItems) {
        this.voteItems = voteItems;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
