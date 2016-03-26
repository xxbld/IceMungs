package org.github.xxbld.icemungs.ui.fragment;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;

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
    //        mapoptions.MapType="Streets"
    //        mapoptions.ZoomLevel="16"
    //        mapoptions.center="34.056215, -117.195668"

    /**
     * ArcGis Onlie 矢量地图
     */
    public static final String TITLELAYERURL = "http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity_Mobile/MapServer";
    /**
     * ArcGis Onlie 栅格地图
     */
    public static final String TITLEIMAGEURL = "http://services.arcgisonline.com/arcgis/rest/services/World_Imagery/MapServer";

    @Bind(R.id.mapview)
    MapView mMapView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_mapview_test;
    }

    @Override
    protected void initViewsAndEvents() {
//        SpatialReference.WKID_WGS84
        ArcGISTiledMapServiceLayer tiledLayer = new ArcGISTiledMapServiceLayer(TITLELAYERURL);
        mMapView.addLayer(tiledLayer);
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
