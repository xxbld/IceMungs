package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 学校新闻
 */
public class News extends BmobObject {

    //标题
    private String newsTitle;
    //简介
    private String newsIntroduction;
    //内容
    private String newsContent;
    //位置
    private BmobGeoPoint newsLocation;
    //大图
    private BmobFile newsBigImage;
    //新闻归属
    private NewsLevel newsLevel;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsIntroduction() {
        return newsIntroduction;
    }

    public void setNewsIntroduction(String newsIntroduction) {
        this.newsIntroduction = newsIntroduction;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public BmobGeoPoint getNewsLocation() {
        return newsLocation;
    }

    public void setNewsLocation(BmobGeoPoint newsLocation) {
        this.newsLocation = newsLocation;
    }

    /**
     * 新闻图片
     * <p>可以使用 getBigImage().loadImageThumbnail() 方法来加载缩略图</p>
     */
    public BmobFile getNewsBigImage() {
        return newsBigImage;
    }

    public void setNewsBigImage(BmobFile newsBigImage) {
        this.newsBigImage = newsBigImage;
    }

    public NewsLevel getNewsLevel() {
        return newsLevel;
    }

    public void setNewsLevel(NewsLevel newsLevel) {
        this.newsLevel = newsLevel;
    }
}
