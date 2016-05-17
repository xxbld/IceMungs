package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 专业
 */
public class Speciality extends BmobObject {
    //字段长度不能超过20
    private String specialName;
    private String specialIntroduction;
    private String specialAddress;
    private String specialPhoneNumber;
    private String specialEmail;
    private String specialLogoImgUrl;
    private String specialBgImgUrl;
    private BmobGeoPoint specialLocation;

    private Academy academy;
    private NewsLevel newsLevel;
    private BmobRelation majors;

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public String getSpecialIntroduction() {
        return specialIntroduction;
    }

    public void setSpecialIntroduction(String specialIntroduction) {
        this.specialIntroduction = specialIntroduction;
    }

    public String getSpecialAddress() {
        return specialAddress;
    }

    public void setSpecialAddress(String specialAddress) {
        this.specialAddress = specialAddress;
    }

    public String getSpecialPhoneNumber() {
        return specialPhoneNumber;
    }

    public void setSpecialPhoneNumber(String specialPhoneNumber) {
        this.specialPhoneNumber = specialPhoneNumber;
    }

    public String getSpecialEmail() {
        return specialEmail;
    }

    public void setSpecialEmail(String specialEmail) {
        this.specialEmail = specialEmail;
    }

    public String getSpecialLogoImgUrl() {
        return specialLogoImgUrl;
    }

    public void setSpecialLogoImgUrl(String specialLogoImgUrl) {
        this.specialLogoImgUrl = specialLogoImgUrl;
    }

    public String getSpecialBgImgUrl() {
        return specialBgImgUrl;
    }

    public void setSpecialBgImgUrl(String specialBgImgUrl) {
        this.specialBgImgUrl = specialBgImgUrl;
    }

    public BmobGeoPoint getSpecialLocation() {
        return specialLocation;
    }

    public void setSpecialLocation(BmobGeoPoint specialLocation) {
        this.specialLocation = specialLocation;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public NewsLevel getNewsLevel() {
        return newsLevel;
    }

    public void setNewsLevel(NewsLevel newsLevel) {
        this.newsLevel = newsLevel;
    }

    public BmobRelation getMajors() {
        return majors;
    }

    public void setMajors(BmobRelation majors) {
        this.majors = majors;
    }
}
