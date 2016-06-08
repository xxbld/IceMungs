package org.github.xxbld.icemungs.data;

import com.esri.core.geometry.SpatialReference;

/**
 * Created by xxbld on 2016/3/28
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ： 常量
 */
public class Constant {

    /**
     * WKID_WGS84 4326
     */
    public static final SpatialReference SR_W = SpatialReference.create(SpatialReference.WKID_WGS84);
    /**
     * WKID_WGS84_WEB_MERCATOR 102113
     */
    public static final SpatialReference SR_WWM = SpatialReference.create(SpatialReference.WKID_WGS84_WEB_MERCATOR);
    /**
     * WKID_WGS84_WEB_MERCATOR_AUXILIARY_SPHERE  102100
     */
    public static final SpatialReference SR_WWMAS = SpatialReference.create(SpatialReference.WKID_WGS84_WEB_MERCATOR_AUXILIARY_SPHERE);
    /**
     * WKID_WGS84_WEB_MERCATOR_AUXILIARY_SPHERE_10  3857
     */
    public static final SpatialReference SR_WWMAS10 = SpatialReference.create(SpatialReference.WKID_WGS84_WEB_MERCATOR_AUXILIARY_SPHERE_10);

    /**
     * ArcGis Onlie 切片地图  http://www.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer
     */
    public static final String ARCGIS_MAPSERVER_TITLE_URL = "http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity_Mobile/MapServer";
    /**
     * ArcGis Onlie 影像地图
     */
    public static final String ACRGIS_MAPSERVER_TITLE_IMAGE_URL = "http://services.arcgisonline.com/arcgis/rest/services/World_Imagery/MapServer";

//    public static final String JXUST_MAPSERVER_TITLE_URL="http://localhost:6080/arcgis/rest/services/Jxust/MapServer";
}
