package org.github.xxbld.icemungs.ui.schoolmap;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.toolkit.map.MapViewHelper;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.Constant;
import org.github.xxbld.icemungs.ui.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/5/6.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolMapFragment extends BaseFragment implements View.OnClickListener {

    private static final String FRAG_MAP_SERVER_URL = "MapServer_REST";
    private static final String FRAG_IMG_MAP_SERVER_URL = "Img_MapServer_REST";

    @Bind(R.id.common_mapview)
    MapView mMapView;
    @Bind(R.id.map_root)
    View mRoot;
    @Bind(R.id.map_switch_layer)
    ImageButton mMapSwitchLayer;
    @Bind(R.id.map_zoom_in)
    ImageButton mMapZoomIn;
    @Bind(R.id.map_zoom_out)
    ImageButton mMapZoomOut;
    @Bind(R.id.map_location_me)
    ImageButton mMapLocationMe;

    private String mMapServerUrl; //底图
    private String mMapImageServerUrl; //影像底图

    MapViewHelper mMapViewHelper;
    ArcGISTiledMapServiceLayer mBaseTiledLayer;
    LocationDisplayManager mLocationDisplayManager;

    public SchoolMapFragment() {
    }

    /**
     * @param restMapServerUrl
     * @return
     */
    public static SchoolMapFragment newInstance(String restMapServerUrl, String restImgMapServerUrl) {
        SchoolMapFragment schoolMapFragment = new SchoolMapFragment();
        Bundle arguments = new Bundle();
        arguments.putString(FRAG_MAP_SERVER_URL, restMapServerUrl);
        arguments.putString(FRAG_IMG_MAP_SERVER_URL, restImgMapServerUrl);
        schoolMapFragment.setArguments(arguments);
        return schoolMapFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.unpause();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.pause();
        }
    }

    @Override
    protected View getLoadingTargetView() {
        return mRoot;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_schoolmap;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        if (getArguments() != null) {
            mMapServerUrl = getArguments().getString(FRAG_MAP_SERVER_URL);
            mMapImageServerUrl = getArguments().getString(FRAG_IMG_MAP_SERVER_URL);
        }
        initMap();
        mMapZoomIn.setOnClickListener(this);
        mMapZoomOut.setOnClickListener(this);
        mMapSwitchLayer.setOnClickListener(this);
        mMapLocationMe.setOnClickListener(this);
    }

    /**
     * 地图基本设置
     */
    private void initMap() {
//        toggleShowLoading(true, "正在初始化地图...");
        mMapViewHelper = new MapViewHelper(mMapView);
        mBaseTiledLayer = new ArcGISTiledMapServiceLayer(mMapServerUrl);
        mMapView.addLayer(mBaseTiledLayer, 0);
        // //设置地图初始范围：赣州章贡区
        Point p1 = GeometryEngine.project(114.8560242878, 25.8817603219, Constant.SR_WWMAS10);
        Point p2 = GeometryEngine.project(114.9900673318, 25.7948780447, Constant.SR_WWMAS10);
        final Envelope initExtent = new Envelope(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        mMapView.setMaxExtent(initExtent);
        initLocationManager();
        mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                MLog.i(TAG, "status:" + status);
                if (status == STATUS.LAYER_LOADED) {
//                    toggleShowLoading(false, null);
                } else if (status == STATUS.LAYER_LOADING_FAILED) {
//                    showError("加载图层失败！！");
                }
            }
        });
    }

    /**
     * 定位
     */
    private void initLocationManager() {
        mLocationDisplayManager = mMapView.getLocationDisplayManager();
        mLocationDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
        mLocationDisplayManager.setAllowNetworkLocation(true);
//        mLocationDisplayManager.start();
        mLocationDisplayManager.setLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                MLog.i(TAG, "lat:" + location.getLatitude() + "lon:" + location.getLongitude());
                mLocationDisplayManager.stop();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.map_location_me:
                if (!mLocationDisplayManager.isStarted()) {
                    mLocationDisplayManager.start();
                }
                break;
            case R.id.map_zoom_in:
                mMapView.zoomin();
                break;
            case R.id.map_zoom_out:
                mMapView.zoomout();
                break;
            case R.id.map_switch_layer:
                break;
        }
    }
}
