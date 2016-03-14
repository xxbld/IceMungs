package org.github.xxbld.icemung.utils;

import junit.framework.TestCase;

/**
 * Created by xxbld on 2016/2/26
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class SDCardUtilTest extends TestCase {

    private static final String TAG = "SDCardUtilTest";

    public void testIsSDCardEnable() throws Exception {
        boolean isEnable = SDCardUtil.isSDCardEnable();
        MLog.i(TAG,"sdDirEnable"+isEnable);
    }

    public void testGetSDCardDir() throws Exception {
        String sdDir = SDCardUtil.getSDCardDir();
        MLog.i(TAG,"sdDir"+sdDir);
    }

    public void testGetSDCardUseableSize() throws Exception {
        long size = SDCardUtil.getSDCardUsableSize();
        MLog.i(TAG,"size"+size);
    }

    public void testGetRootDir() throws Exception {
        String rootDir = SDCardUtil.getRootDir();
        MLog.i(TAG,"rootDir"+rootDir);
    }

    public void testGetExternalCacheDir() throws Exception {
    }
}