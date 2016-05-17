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

    public final static Integer LEVELCODE_SCHOOL = 1;
    public final static Integer LEVELCODE_ACADEMY = 2;
    public final static Integer LEVELCODE_SPECIALITY = 4;

    private School school;
    private Academy academy;
    private Speciality speciality;
    private Integer levelCode;
    private BmobRelation news;//Relation 表示 1：多 的关联，

    public NewsLevel() {
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Integer getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(Integer levelCode) {
        //TODO 添加限制
        this.levelCode = levelCode;
    }

    public BmobRelation getNews() {
        return news;
    }

    public void setNews(BmobRelation news) {
        this.news = news;
    }
}
