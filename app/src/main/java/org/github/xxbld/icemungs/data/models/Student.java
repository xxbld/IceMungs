package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :用户表
 */
public class Student extends BmobUser {
    //BmobObject 默认属性
//    private String objectId;
//    private String createdAt;
//    private String updatedAt;
//    private BmobACL ACL;
//    private String _c_ = "";
//    protected static List<JSONObject> increments = new ArrayList();
//    private static JSONObject data;
    //BmobUser 默认属性
//    private String username;
//    private String password;
//    private String email;
//    private Boolean emailVerified;
//    private String sessionToken;
//    private String mobilePhoneNumber;
//    private Boolean mobilePhoneNumberVerified;
//    static JSONObject current;


    private final static Integer MIN_AGE = 0;
    private final static Integer MAX_AGE = 200;

    private Integer sex;  // 0 男，1 女
    private Integer age;  //age range [0 - 200]
    private BmobGeoPoint location;  //loc
    private String address;  //联系地址
    private BmobFile icon;  //头像

    private BmobRelation majors;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
//        boolean sexOk = sex == 0 || sex == 1;
//        if (!sexOk) {
//            sex = 0;
//        }
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
//        boolean ageOk = MIN_AGE <= age && age <= MAX_AGE;
//        if (!ageOk) {
//            age = Integer.valueOf((int) (Math.random() * MAX_AGE));
//        }
        this.age = age;
    }

    public BmobGeoPoint getLocation() {
        return location;
    }

    public void setLocation(BmobGeoPoint location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }

    public BmobRelation getMajors() {
        return majors;
    }

    public void setMajors(BmobRelation majors) {
        this.majors = majors;
    }
}