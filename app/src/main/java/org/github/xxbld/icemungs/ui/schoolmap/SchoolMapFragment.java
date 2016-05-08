package org.github.xxbld.icemungs.ui.schoolmap;

import android.os.Bundle;

import org.github.xxbld.icemungs.ui.base.BaseFragment;

/**
 * Created by xxbld on 2016/5/6.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolMapFragment extends BaseFragment {

    private static final String FRAG_MAP_SERVER_URL = "MapServer_REST";
    private static final String FRAG_IMG_MAP_SERVER_URL = "Img_MapServer_REST";


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


}
