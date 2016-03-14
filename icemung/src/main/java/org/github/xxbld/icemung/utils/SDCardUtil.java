package org.github.xxbld.icemung.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by xxbld on 2016/2/26
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class SDCardUtil {

    private SDCardUtil() {
    }


//    通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/{package_name}/files/ ，储存一些长时间保存的数据；
//
//    通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/{package_name}/cache/，储存临时缓存数据；
//
//    这两个目录分别对应 设置->应用->应用详情里面的”清除数据“与”清除缓存“选项。

    /**
     * 获取拓展存储Cache的绝对路径
     *
     * @param context
     */
    public static String getExternalCacheDir(Context context) {
        if (isSDCardEnable()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        File file = context.getExternalCacheDir();
        // In some case, even the sd card is mounted,
        // getExternalCacheDir will return null
        // may be it is nearly full.
        if (file != null) {
            sb.append(file.getAbsolutePath()).append(File.separator);
        } else {
            sb.append(Environment.getExternalStorageDirectory().getPath()).append("/Android/data/")
                    .append(context.getPackageName())
                    .append("/cache/").append(File.separator).toString();
        }
        return sb.toString();
    }


    /**
     * SD Card 是否可用
     * @return
     */
    public static boolean isSDCardEnable(){
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD Card 路径
     * @return
     */
    public static String getSDCardDir(){
        return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
    }

    /**
     *
     * @return
     */
    public static long getSDCardUsableSize(){
        if (isSDCardEnable()){
            StatFs statFs = new StatFs(getSDCardDir());
            long availableSize = statFs.getAvailableBlocks() - 4;
            long freeBlocks = statFs.getAvailableBlocks();
            return availableSize * freeBlocks;
        }
        return 0;
    }

    /**
     * 获取系统存取路径
     * @return
     */
    public static String getRootDir(){
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
