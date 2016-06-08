package org.github.xxbld.icemungs.data.models;

import java.util.List;

import cn.bmob.v3.BmobObject;
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
    //内容
    private String iTalkContent;
    //位置
    private BmobGeoPoint iTalkLocation;
    //图
    private List<String> iTalkImageUrls;

    private Student student;

    public String getiTalkTitle() {
        return iTalkTitle;
    }

    public void setiTalkTitle(String iTalkTitle) {
        this.iTalkTitle = iTalkTitle;
    }

    public String getiTalkContent() {
        return iTalkContent;
    }

    public void setiTalkContent(String iTalkContent) {
        this.iTalkContent = iTalkContent;
    }

    public BmobGeoPoint getiTalkLocation() {
        return iTalkLocation;
    }

    public void setiTalkLocation(BmobGeoPoint iTalkLocation) {
        this.iTalkLocation = iTalkLocation;
    }

    public List<String> getiTalkImageUrls() {
        return iTalkImageUrls;
    }

    public void setiTalkImageUrls(List<String> iTalkImageUrls) {
        this.iTalkImageUrls = iTalkImageUrls;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
