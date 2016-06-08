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
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.toolkit.map.MapViewHelper;
import com.esri.android.toolkit.map.OnGraphicClickListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;

import org.github.xxbld.icemung.utils.BitmapUtil;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.Constant;
import org.github.xxbld.icemungs.data.models.School;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.presenters.FragSchoolMapPresenter;
import org.github.xxbld.icemungs.ui.base.BaseFragment;
import org.github.xxbld.icemungs.ui.personalcenter.PersonalCenterActivity;
import org.github.xxbld.icemungs.views.IFragSchoolMapView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by xxbld on 2016/5/6.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :校园地图
 */
public class SchoolMapFragment extends BaseFragment implements View.OnClickListener, IFragSchoolMapView {

    private static final String FRAG_MAP_SERVER_URL = "MapServer_REST";
    private static final String FRAG_IMG_MAP_SERVER_URL = "Img_MapServer_REST";
    /*附近的人图层属性 TAG */
    private static final String NEAR_PERSON_LAYER_ATTRIBUTES = "Near_Person_Layer_Attributes";
    private static final String TEXT_QUERY_ATTRIBUTES = "Text_Query_Attributes";

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
        //        This is SketchLayer GraphicClick
        mMapViewHelper.setShowGraphicCallout(true);
        mMapViewHelper.setOnGraphicClickListener(new OnGraphicClickListener() {
            @Override
            public void onGraphicClick(Graphic graphic) {
                MLog.i(TAG, "school click !");
            }
        });
        mMapView.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(float v, float v1) {
                Point point1 = mMapView.toMapPoint(v, v1);
                Point pLoc = (Point) GeometryEngine.project(point1, Constant.SR_WWMAS, Constant.SR_W);
                MLog.i(TAG, "Point:" + pLoc.toString());
                onNearPersonGraphicClicked(v, v1);
            }
        });

        /*MapViewHelper中已经设置了onTouchListener*/
//        mMapView.setOnTouchListener(new MapOnTouchListener(getActivity(), mMapView) {
//            @Override
//            public boolean onSingleTap(MotionEvent point) {
//                Point point1 = mMapView.toMapPoint(point.getX(), point.getY());
//                Point pLoc = (Point) GeometryEngine.project(point1, Constant.SR_WWMAS, Constant.SR_W);
//                MLog.i(TAG, "Point:" + pLoc.toString());
//                onNearPersonGraphicClicked(point.getX(), point.getY());
//                return super.onSingleTap(point);
//            }
//        });
    }

    /**
     * 附近的人被点击了
     *
     * @param x screen x
     * @param y screen x
     */
    private void onNearPersonGraphicClicked(float x, float y) {
        if (mNearPersonLayer != null && mNearPersonLayer.getGraphicIDs() != null) {
            int[] graphicIDs = mNearPersonLayer.getGraphicIDs(x, y, 10, 1);
            if (graphicIDs != null && graphicIDs.length > 0) {
                MLog.i(TAG, "nearPerson Id :" + graphicIDs[0]);
                Graphic graphic = mNearPersonLayer.getGraphic(graphicIDs[0]);
//                Map<String, Object> attributes = graphic.getAttributes();
//                Student attributeValue = (Student) attributes.get(NEAR_PERSON_LAYER_ATTRIBUTES);
                String attributeValue = (String) graphic.getAttributeValue(NEAR_PERSON_LAYER_ATTRIBUTES);
                Bundle bundle = new Bundle();
//                bundle.putString("studentId", attributeValue.getObjectId());
                bundle.putString("studentId", attributeValue);
                go(PersonalCenterActivity.class, bundle);
            }
        }
    }

    /**
     * 定位设置
     */
    private void initLocationManager() {
        mLocationDisplayManager = mMapView.getLocationDisplayManager();
        mLocationDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
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
                mLocationDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);
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


    public void removeAllGraphics() {
        if (mMapViewHelper != null) {
            mMapViewHelper.removeAllGraphics();
        }
        if (mNearPersonLayer != null) {
            mNearPersonLayer.removeAll();
        }
    }

    /**
     * 搜索附近的人
     */
    public void searchNearPerson() {
        if (mFragSchoolMapPresenter != null) {
            Location location = getLocation();
            if (location != null) {
                BmobGeoPoint bPoint = new BmobGeoPoint(location.getLongitude(), location.getLatitude());
                mFragSchoolMapPresenter.queryNearPerson(bPoint);
            }
        }
    }

    private SearchView.OnQueryTextListener mOnQueryTextListener;

    /**
     * 处理toolbar search view
     *
     * @param searchView
     */
    public void initSearchView(SearchView searchView) {
        if (mOnQueryTextListener == null) {
            this.mSearchView = searchView;
            mSearchView.setIconified(true);
            mSearchView.setIconifiedByDefault(true);
            mSearchView.setSubmitButtonEnabled(true);
            mOnQueryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    MLog.i(TAG, "query:" + query);
                    //搜索
                    if (mFragSchoolMapPresenter != null) {
                        Location location = getLocation();
                        if (location != null) {
                            BmobGeoPoint bPoint = new BmobGeoPoint(location.getLongitude(), location.getLatitude());
                            mFragSchoolMapPresenter.queryNearSchool(query, bPoint);
                        } else {
                            mFragSchoolMapPresenter.queryNearSchool(query, null);
                        }
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    MLog.i(TAG, "query:" + newText);
                    return false;
                }
            };
            mSearchView.setOnQueryTextListener(mOnQueryTextListener);
        }
    }

    //=================

    @Override
    public void showTextQuery(String queryStr, Object list) {
        removeAllGraphics();
        if (queryStr.contains("学校") || queryStr.contains("school")) {
            List<School> schools = (List<School>) list;
            for (final School school : schools) {
                Glide.with(this).load(school.getSchoolLogoImgUrl()).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>(50, 50) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        RoundedBitmapDrawable circleDrawable = BitmapUtil.getCircleDrawable(getActivity(), resource);
                        BmobGeoPoint schoolGPSLocation = school.getSchoolLocation();
                        mMapViewHelper.addMarkerGraphic(schoolGPSLocation.getLatitude(), schoolGPSLocation.getLongitude(), school.getSchoolName(), school.getSchoolAddress(), null, circleDrawable, true, 0);
                    }
                });
            }
        }
    }

    @Override
    public void showNearPerson(List<Student> students) {
//        mNearPersonLayer.removeAll();
        removeAllGraphics();
        for (final Student student : students) {
            Glide.with(this).load(student.getIconUrl()).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>(50, 50) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    RoundedBitmapDrawable circleDrawable = BitmapUtil.getCircleDrawable(getActivity(), resource);
                    final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(circleDrawable);
                    BmobGeoPoint studentGPSLocation = student.getLocation();
                    //投影转换
                    Point personLoc = GeometryEngine.project(studentGPSLocation.getLongitude(),
                            studentGPSLocation.getLatitude(), Constant.SR_WWMAS);
                    //setAttributes
                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put(NEAR_PERSON_LAYER_ATTRIBUTES, student.getObjectId());
                    Graphic graphic = new Graphic(personLoc, pictureMarkerSymbol, attributes);
                    mNearPersonLayer.addGraphic(graphic);
                }
            });
        }
//        mMapView.addLayer(mNearPersonLayer);
        mMapView.zoomToScale(getLocPoint(), mNearPersonLayer.getMaxScale());
    }
}
