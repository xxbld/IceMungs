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

    public Major() {
    }

    public BmobDate getInDate() {
        return inDate;
    }

    public void setInDate(BmobDate inDate) {
        this.inDate = inDate;
    }

    public BmobDate getOutDate() {
        return outDate;
    }

    public void setOutDate(BmobDate outDate) {
        this.outDate = outDate;
    }

    public Boolean getMainMajor() {
        return isMainMajor;
    }

    public void setMainMajor(Boolean mainMajor) {
        isMainMajor = mainMajor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Major{" +
                "inDate=" + inDate +
                ", outDate=" + outDate +
                ", isMainMajor=" + isMainMajor +
                ", student=" + student +
                ", speciality=" + speciality +
                '}';
    }
}
