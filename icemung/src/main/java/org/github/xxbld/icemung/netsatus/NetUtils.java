package org.github.xxbld.icemung.netsatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;

import java.util.Locale;

/**
 * Created by Administrator on 2015/12/30.
 */
public class NetUtils {
    /**
     * net type
     */
    public static enum NetType {
        WIFI, CMNET, CMWAP, NONE
    }

    /**
     * 网络是否可用
     *
     * @param context
     * @return true、false
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager cmr = getConnectivityManager(context);
        NetworkInfo[] networkInfos = cmr.getAllNetworkInfo();
        if (networkInfos != null) {
            for (NetworkInfo info : networkInfos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 当前网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager cmr = getConnectivityManager(context);
        NetworkInfo networkInfo = cmr.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 手机流量是否连接上
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager cmr = getConnectivityManager(context);
        NetworkInfo mobileNetInfo = cmr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetInfo != null) {
            return mobileNetInfo.isAvailable();
        }
        return false;
    }

    /**
     * Wifi是否连接上
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cmr = getConnectivityManager(context);
        NetworkInfo wifiNetInfo = cmr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetInfo != null) {
            return wifiNetInfo.isAvailable();
        }
        return false;
    }


    /**
     * 获取当前网络类型
     *
     * @param context
     * @return
     */
    public static NetType getNetWorkType(Context context) {
        ConnectivityManager cmr = getConnectivityManager(context);
        NetworkInfo networkInfo = cmr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }
        int netType = networkInfo.getType();
        if (netType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (netType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }

    /**
     * 当前网络是否用户流量敏感、大数据传输时应该判断
     *
     * @param context
     * @return
     */
    public static boolean isActiveNetworkMetered(Context context) {
        ConnectivityManager cmr = getConnectivityManager(context);
        return ConnectivityManagerCompat.isActiveNetworkMetered(cmr);
    }

    /**
     * getConnectivityManager
     *
     * @param context
     * @return
     */
    public static ConnectivityManager getConnectivityManager(Context context) {
        if (context != null) {
            return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        throw new IllegalArgumentException("please provide a context argument");
    }
}
