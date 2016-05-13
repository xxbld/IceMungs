package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 学校
 */
public class School extends BmobObject {

    //          Jxust Id e40489eba1
//        JCAcademy Id 0e390593ab

    private String schoolName;
    private String schoolIntroduction;
    private String schoolAddress;
    private String schoolPhoneNumber;
    private String schoolEmail;
    private String schoolLogoImgUrl;
    private String schoolBgImgUrl;
    private BmobGeoPoint schoolLocation;

    private BmobRelation schoolMaps;  //1：多，一个学校可以有多个地图服务
    private BmobRelation academies;  //1：多，一个学校可以有多个学院

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolIntroduction() {
        return schoolIntroduction;
    }

    public void setSchoolIntroduction(String schoolIntroduction) {
        this.schoolIntroduction = schoolIntroduction;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolPhoneNumber() {
        return schoolPhoneNumber;
    }

    public void setSchoolPhoneNumber(String schoolPhoneNumber) {
        this.schoolPhoneNumber = schoolPhoneNumber;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getSchoolLogoImgUrl() {
        return schoolLogoImgUrl;
    }

    public void setSchoolLogoImgUrl(String schoolLogoImgUrl) {
        this.schoolLogoImgUrl = schoolLogoImgUrl;
    }

    public String getSchoolBgImgUrl() {
        return schoolBgImgUrl;
    }

    public void setSchoolBgImgUrl(String schoolBgImgUrl) {
        this.schoolBgImgUrl = schoolBgImgUrl;
    }

    public BmobGeoPoint getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(BmobGeoPoint schoolLocation) {
        this.schoolLocation = schoolLocation;
    }

    public BmobRelation getSchoolMaps() {
        return schoolMaps;
    }

    public void setSchoolMaps(BmobRelation schoolMaps) {
        this.schoolMaps = schoolMaps;
    }

    public BmobRelation getAcademies() {
        return academies;
    }

    public void setAcademies(BmobRelation academies) {
        this.academies = academies;
    }
}
