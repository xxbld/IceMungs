package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 学院
 */
public class Academy extends BmobObject {
    private String academyName;
    private String academyIntroduction;
    private String academyAddress;
    private String academyPhoneNumber;
    private String academyEmail;
    private BmobFile academyLogoImg;
    private BmobGeoPoint academyLocation;

    private School school;
    private BmobRelation specialities;

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getAcademyIntroduction() {
        return academyIntroduction;
    }

    public void setAcademyIntroduction(String academyIntroduction) {
        this.academyIntroduction = academyIntroduction;
    }

    public String getAcademyAddress() {
        return academyAddress;
    }

    public void setAcademyAddress(String academyAddress) {
        this.academyAddress = academyAddress;
    }

    public String getAcademyPhoneNumber() {
        return academyPhoneNumber;
    }

    public void setAcademyPhoneNumber(String academyPhoneNumber) {
        this.academyPhoneNumber = academyPhoneNumber;
    }

    public String getAcademyEmail() {
        return academyEmail;
    }

    public void setAcademyEmail(String academyEmail) {
        this.academyEmail = academyEmail;
    }

    public BmobFile getAcademyLogoImg() {
        return academyLogoImg;
    }

    public void setAcademyLogoImg(BmobFile academyLogoImg) {
        this.academyLogoImg = academyLogoImg;
    }

    public BmobGeoPoint getAcademyLocation() {
        return academyLocation;
    }

    public void setAcademyLocation(BmobGeoPoint academyLocation) {
        this.academyLocation = academyLocation;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public BmobRelation getSpecialities() {
        return specialities;
    }

    public void setSpecialities(BmobRelation specialities) {
        this.specialities = specialities;
    }
}
