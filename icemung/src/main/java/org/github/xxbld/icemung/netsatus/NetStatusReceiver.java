package org.github.xxbld.icemung.netsatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import org.github.xxbld.icemung.utils.MLog;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/12/30.
 */
public class NetStatusReceiver extends BroadcastReceiver {

    //receiver action
    private final static String CUSTOM_NET_CHANGE_ACTION = "com.github.xxbld.templelibrary.net.conn.CONNECTIVITY_CHANGE";
    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private final static String TAG = NetStatusReceiver.class.getSimpleName();
    //one instance
    private static BroadcastReceiver mBroadcastReceiver;
    //observers
    private static ArrayList<NetStatusObserver> mNetStatusObservers = new ArrayList<NetStatusObserver>();

    private static boolean isNetAvailable = false;
    private static NetUtils.NetType mNetType;

    private static BroadcastReceiver getReceiver() {
        if (mBroadcastReceiver == null) {
            synchronized (NetStatusReceiver.class) {
                if (mBroadcastReceiver == null) {
                    mBroadcastReceiver = new NetStatusReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = NetStatusReceiver.this;
        String action = intent.getAction();
        if (action.equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION) || action.equalsIgnoreCase(CUSTOM_NET_CHANGE_ACTION)) {
            if (NetUtils.isNetWorkAvailable(context)) {
                MLog.i(TAG, "<----network connected---->");
                isNetAvailable = true;
                mNetType = NetUtils.getNetWorkType(context);
            } else {
                isNetAvailable = false;
                MLog.i(TAG, "<----network disconnected---->");
            }
            //通知observer
            notifyObserver();
        }
    }

    /**
     * register NetStatusReceiver
     *
     * @param context
     */
    public static void registerNetStatusReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CUSTOM_NET_CHANGE_ACTION);
        intentFilter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.getApplicationContext().registerReceiver(getReceiver(), intentFilter);
    }

    /**
     * unregister NetStatusReceiver
     *
     * @param context
     */
    public static void unRegisterNetStatusReceiver(Context context) {
        if (mBroadcastReceiver != null) {
            try {
                context.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {
                MLog.d(TAG, e.getMessage());
            }
        }
    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    public static NetUtils.NetType getNetType() {
        return mNetType;
    }

    /**
     * notice all observers the status of network
     */
    private void notifyObserver() {
        if (mNetStatusObservers != null && !mNetStatusObservers.isEmpty()) {
            for (NetStatusObserver netStatusObserver : mNetStatusObservers) {
                if (netStatusObserver != null) {
                    if (isNetworkAvailable()) {
                        netStatusObserver.onNetWorkConnected(getNetType());
                    } else {
                        netStatusObserver.onNetWorkDisConnected();
                    }
                }
            }
        }
    }

    /**
     * register NetStatusObserver
     *
     * @param netStatusObserver
     */
    public static void registerNetStatusObserver(NetStatusObserver netStatusObserver) {
        if (mNetStatusObservers == null) {
            mNetStatusObservers = new ArrayList<NetStatusObserver>();
        }
        mNetStatusObservers.add(netStatusObserver);
    }

    /**
     * unRegister NetStatusObserver
     *
     * @param netStatusObserver
     */
    public static void unRegisterNetStatusObserver(NetStatusObserver netStatusObserver) {
        if (mNetStatusObservers != null) {
            if (mNetStatusObservers.contains(netStatusObserver)) {
                mNetStatusObservers.remove(netStatusObserver);
            }
        }
    }
}
