package org.github.xxbld.icemungs.ui.fragment;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/3/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class TestArcGisFragment extends BaseFragment {
    //        赣州位置 114.929473, 25.854084
    /**
     * ArcGis Onlie 矢量地图
     */
    public static final String TITLELAYERURL = "http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity_Mobile/MapServer";
    /**
     * ArcGis Onlie 栅格地图
     */
    public static final String TITLEIMAGEURL = "http://services.arcgisonline.com/arcgis/rest/services/World_Imagery/MapServer";

    @Bind(R.id.common_mapview)
    MapView mMapView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_mapview_test;
    }

    @Override
    protected void initViewsAndEvents() {
//        SpatialReference.WKID_WGS84
        ArcGISTiledMapServiceLayer tiledLayer = new ArcGISTiledMapServiceLayer(TITLELAYERURL);
        MLog.i(TAG, "isbasemap:" + tiledLayer.isWebMapBaselayer());
        mMapView.addLayer(tiledLayer);

        // //设置地图初始范围：赣州章贡区
        Point p1 = GeometryEngine.project(114.8560242878, 25.8817603219,
                SpatialReference.create(3857));
        Point p2 = GeometryEngine.project(114.9900673318, 25.7948780447,
                SpatialReference.create(3857));
        final Envelope initExtent = new Envelope(p1.getX(), p1.getY(),
                p2.getX(), p2.getY());
        mMapView.setMaxExtent(initExtent);

        mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                if (o instanceof MapView && o == mMapView) {
                    if (status == STATUS.INITIALIZED) {
                        MLog.i(TAG, "INITIALIZED");
                        getCenterP();
                    }
                }
                if (status == STATUS.LAYER_LOADED) {
                    MLog.i(TAG, "LAYER_LOADED");
                    getCenterP();
                    Point zoomPoint = (Point) GeometryEngine.project(new Point(114.929473, 25.854084),
                            SpatialReference.create(SpatialReference.WKID_WGS84),
                            mMapView.getSpatialReference());
                    mMapView.zoomToResolution(zoomPoint, 20.0);
//                    mMapView.centerAndZoom(114.929473, 25.854084, 16);
                }
            }
        });

    }

    private void getCenterP() {
        Point center = mMapView.getCenter();
        MLog.i(TAG, "CenterPoint:" + center.toString());
        SpatialReference spatialReference = mMapView.getSpatialReference();
        MLog.i(TAG, "SpatialReference:" + spatialReference.getID());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.unpause();
    }

}
