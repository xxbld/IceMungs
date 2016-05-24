package org.github.xxbld.icemungs.ui.schoolmap;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.toolkit.map.MapViewHelper;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;

import org.github.xxbld.icemung.utils.BitmapUtil;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.Constant;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.presenters.FragSchoolMapPresenter;
import org.github.xxbld.icemungs.ui.base.BaseFragment;
import org.github.xxbld.icemungs.views.IFragSchoolMapView;

import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by xxbld on 2016/5/6.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolMapFragment extends BaseFragment implements View.OnClickListener, IFragSchoolMapView {

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
    ArcGISTiledMapServiceLayer mBaseImageTiledLayer;
    GraphicsLayer mNearPersonLayer;//附近的人
    LocationDisplayManager mLocationDisplayManager;

    SearchView mSearchView;
    FragSchoolMapPresenter mFragSchoolMapPresenter;

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
            if (mLocationDisplayManager != null) {
                mLocationDisplayManager.resume();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.pause();
            if (mLocationDisplayManager != null) {
                mLocationDisplayManager.pause();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mFragSchoolMapPresenter != null) {
            mFragSchoolMapPresenter.detachView();
        }
        if (mLocationDisplayManager != null) {
            mLocationDisplayManager.stop();
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
//            mMapServerUrl = "http://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer";
//            mMapServerUrl = "http://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";
            mMapImageServerUrl = getArguments().getString(FRAG_IMG_MAP_SERVER_URL);
        }
        mFragSchoolMapPresenter = new FragSchoolMapPresenter(getActivity());
        mFragSchoolMapPresenter.attachView(this);
        mFragSchoolMapPresenter.initialized();

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
        mBaseImageTiledLayer = new ArcGISTiledMapServiceLayer(mMapImageServerUrl);
        mNearPersonLayer = new GraphicsLayer();
        mMapView.addLayer(mBaseTiledLayer, 0);
        mMapView.addLayer(mNearPersonLayer);
        // //设置地图初始范围：赣州章贡区
        Point p1 = GeometryEngine.project(114.8560242878, 25.8817603219, Constant.SR_WWMAS);
        Point p2 = GeometryEngine.project(114.9900673318, 25.7948780447, Constant.SR_WWMAS);
        final Envelope initExtent = new Envelope(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        mMapView.setMaxExtent(initExtent);
        mMapView.centerAt(initExtent.getCenter(), true);
        initLocationManager();
        mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                MLog.i(TAG, "status:" + status);
                if (status == STATUS.INITIALIZED) {
                    MLog.i(TAG, "SR:" + mMapView.getSpatialReference().getID());
                    MLog.i(TAG, "getScale():" + mMapView.getScale());
                } else if (status == STATUS.INITIALIZATION_FAILED) {

                } else if (status == STATUS.LAYER_LOADED) {
//                    toggleShowLoading(false, null);
                } else if (status == STATUS.LAYER_LOADING_FAILED) {
//                    showError("加载图层失败！！");
                }
            }
        });
    }

    /**
     * 定位设置
     */
    private void initLocationManager() {
        mLocationDisplayManager = mMapView.getLocationDisplayManager();
        mLocationDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.OFF);
        mLocationDisplayManager.setAllowNetworkLocation(true);
        mLocationDisplayManager.start();
    }

    /**
     * get Location GPS
     *
     * @return
     */
    public Location getLocation() {
        if (mLocationDisplayManager != null) {
            if (!mLocationDisplayManager.isStarted()) {
                mLocationDisplayManager.start();
            }
            return mLocationDisplayManager.getLocation();
        }
        return null;
    }

    /**
     * get location point of map's spatial reference
     *
     * @return
     */
    public Point getLocPoint() {
        if (mLocationDisplayManager != null) {
            if (!mLocationDisplayManager.isStarted()) {
                mLocationDisplayManager.start();
            }
            return mLocationDisplayManager.getPoint();
        }
        return null;
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
                if (mMapView.getLayerByURL(mMapServerUrl) != null) {
                    mMapView.removeLayer(0);
                    mMapView.addLayer(mBaseImageTiledLayer, 0);
                } else {
                    mMapView.removeLayer(0);
                    mMapView.addLayer(mBaseTiledLayer, 0);
                }
                break;
        }
    }


    /**
     * 搜索附近的人
     */
    public void searchNearPerson() {
        if (mFragSchoolMapPresenter != null) {
            Location location = getLocation();
            BmobGeoPoint bPoint = new BmobGeoPoint(location.getLongitude(), location.getLatitude());
            mFragSchoolMapPresenter.getNearPerson(bPoint);
        }
    }

    /**
     * 处理toolbar search view
     *
     * @param searchView
     */
    public void initSearchView(SearchView searchView) {

        this.mSearchView = searchView;
//        mSearchView.setIconified(true);
//        mSearchView.setIconifiedByDefault(true);
//        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    //=================
    @Override
    public void showNearPerson(List<Student> students) {
        mNearPersonLayer.removeAll();
        for (final Student student : students) {
            Glide.with(this).load(student.getIconUrl()).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>(50, 50) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    RoundedBitmapDrawable circleDrawable = BitmapUtil.getCircleDrawable(getActivity(), resource);
                    final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(circleDrawable);
//            pictureMarkerSymbol.setUrl(student.getIconUrl());
                    BmobGeoPoint studentGPSLocation = student.getLocation();
                    Point personLoc = GeometryEngine.project(studentGPSLocation.getLongitude(),
                            studentGPSLocation.getLatitude(), Constant.SR_WWMAS);
                    Graphic graphic = new Graphic(personLoc, pictureMarkerSymbol);
                    mNearPersonLayer.addGraphic(graphic);
                }
            });
        }
        mMapView.zoomToScale(getLocPoint(), mNearPersonLayer.getMaxScale());
    }
}
