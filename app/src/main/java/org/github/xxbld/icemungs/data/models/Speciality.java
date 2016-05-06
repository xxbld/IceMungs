package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 专业
 */
public class Speciality extends BmobObject {

    private String specialityName;
    private String specialityIntroduction;
    private String specialityAddress;
    private String specialityPhoneNumber;
    private BmobFile specialityLogoImg;
    private BmobGeoPoint specialityLocation;

    private Academy academy;
    private BmobRelation majors;

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityIntroduction() {
        return specialityIntroduction;
    }

    public void setSpecialityIntroduction(String specialityIntroduction) {
        this.specialityIntroduction = specialityIntroduction;
    }

    public String getSpecialityAddress() {
        return specialityAddress;
    }

    public void setSpecialityAddress(String specialityAddress) {
        this.specialityAddress = specialityAddress;
    }

    public String getSpecialityPhoneNumber() {
        return specialityPhoneNumber;
    }

    public void setSpecialityPhoneNumber(String specialityPhoneNumber) {
        this.specialityPhoneNumber = specialityPhoneNumber;
    }

    public BmobFile getSpecialityLogoImg() {
        return specialityLogoImg;
    }

    public void setSpecialityLogoImg(BmobFile specialityLogoImg) {
        this.specialityLogoImg = specialityLogoImg;
    }

    public BmobGeoPoint getSpecialityLocation() {
        return specialityLocation;
    }

    public void setSpecialityLocation(BmobGeoPoint specialityLocation) {
        this.specialityLocation = specialityLocation;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public BmobRelation getMajors() {
        return majors;
    }

    public void setMajors(BmobRelation majors) {
        this.majors = majors;
    }
}
