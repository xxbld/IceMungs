package org.github.xxbld.icemungs.data.models;

import cn.bmob.v3.BmobObject;

/**
 * Created by xxbld on 2016/5/3.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 学校地图服务
 */
public class SchoolMap extends BmobObject {

    //地图服务名称
    private String mapName;
    //地图服务缩略图
    private String mapThumbnails;
    //地图服务uri
    private String mapServerUri;
    //简介
    private String mapServerIntroduction;
    //类型
    private String mapServerType;
    //所属学校
    private School school;

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapThumbnails() {
        return mapThumbnails;
    }

    public void setMapThumbnails(String mapThumbnails) {
        this.mapThumbnails = mapThumbnails;
    }

    public String getMapServerUri() {
        return mapServerUri;
    }

    public void setMapServerUri(String mapServerUri) {
        this.mapServerUri = mapServerUri;
    }

    public String getMapServerIntroduction() {
        return mapServerIntroduction;
    }

    public void setMapServerIntroduction(String mapServerIntroduction) {
        this.mapServerIntroduction = mapServerIntroduction;
    }

    public String getMapServerType() {
        return mapServerType;
    }

    public void setMapServerType(String mapServerType) {
        this.mapServerType = mapServerType;
    }
}
