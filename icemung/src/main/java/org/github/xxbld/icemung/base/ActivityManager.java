package org.github.xxbld.icemung.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/29.
 */
public class ActivityManager {
    private static String TAG = ActivityManager.class.getSimpleName();

    private static ActivityManager instance = null;
    private static List<Activity> mActivities = new LinkedList<>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (null == instance) {
            synchronized (ActivityManager.class) {
                if (null == instance) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     * Activities list size
     *
     * @return size
     */
    public int getActivitiesCount() {
        return mActivities.size();
    }

    /**
     * last index Activity of Activities list
     *
     * @return top activity
     */
    public synchronized Activity getTopActivity() {
        return getActivitiesCount() > 0 ? mActivities.get(getActivitiesCount() - 1) : null;
    }

    /**
     * add Activity to Activities list
     *
     * @param activity
     */
    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    /**
     * remove Activity of Activities list
     */
    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    /**
     * clear all from Activities list also do finish those
     */
    public synchronized void clearAllActivities() {
        for (int i = mActivities.size(); i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }
    /**
     * clear all from Activities list also do finish those beside the top one
     */
    public synchronized void clearAllActivitiesOnlyTop() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }
}
