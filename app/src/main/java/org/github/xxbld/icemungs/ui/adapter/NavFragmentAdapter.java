package org.github.xxbld.icemungs.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemung.utils.TextUtil;

import java.util.Map;

/**
 * Created by xxbld on 2016/3/28
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：Adapter for NavigationView 's  Single Checked Item  simple to switch Fragments ;
 * if you also need setting menu when the fragments changed ,Then this Adapter must be inited at  Activity's onCreateOptionsMenu Method
 */
public class NavFragmentAdapter implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = NavFragmentAdapter.class.getSimpleName();
    /**
     * 资源id为空时 id的值
     */
    private static final int RES_ID_NULL = 0;
    /**
     * 当前应用名
     */
    private static String sThisAppLabelName;

    private FragmentManager mFragmentManager;
    private int mFragContainerViewId;
    /**
     * object[0]=(Fragment)fragment;object[1]=(Integer)title;object[2]=(Integer)toolBarMenuResId
     */
    private Map<Integer, Object[]> mFragmentMap;

    private NavigationView mNav;
    private AppCompatActivity mActivity;
    private Menu mMenu;

    private OnNavSelectedListener mListener;
    private Fragment mCurrentFragment = null;
    private boolean isFirstLoad = true;
    private int mCurrentSelectedItemId = RES_ID_NULL;

    /**
     * NavFragment Adapter without mind to set menu
     *
     * @param fm
     * @param fragmentContainerId
     * @param fragmentsMap
     * @param nav
     */
    public NavFragmentAdapter(FragmentManager fm, int fragmentContainerId, Map<Integer, Object[]> fragmentsMap, NavigationView nav) {
        this(fm, fragmentContainerId, fragmentsMap, nav, null);
    }

    /**
     * NavFragment
     * <p>如果想要在切换Fragments时更改Menu;此方法须在 onCreateOptionsMenu中(或之后)使用才能获取到menu</p>
     *
     * @param fm
     * @param fragmentContainerId
     * @param fragmentsMap
     * @param nav
     * @param menu
     */
    public NavFragmentAdapter(FragmentManager fm, int fragmentContainerId, Map<Integer, Object[]> fragmentsMap, NavigationView nav, Menu menu) {
        this.mFragmentManager = fm;
        this.mFragContainerViewId = fragmentContainerId;
        this.mFragmentMap = fragmentsMap;
        this.mNav = nav;
        this.mActivity = (AppCompatActivity) mNav.getContext();
        this.mMenu = menu;
        this.sThisAppLabelName = getThisAppLabelName();
        mNav.setNavigationItemSelectedListener(this);
    }

    public void setOnNavSelectedListener(OnNavSelectedListener listener) {
        this.mListener = listener;
    }

    public Fragment getItemFragment(int itemId) {
        return (Fragment) getItem(itemId)[0];
    }

    public int getItemCount() {
        return mFragmentMap.size();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switchToItem(itemId);
        if (mListener != null) {
            return mListener.onNavSelected(item);
        }
        return false;
    }

    /**
     * 切换到id =xx 的item
     *
     * @param itemId
     */
    public void switchToItem(int itemId) {
        if (mCurrentSelectedItemId == itemId) {
            return;
        }
        if (isFirstLoad) {
            if (!isContainItem(itemId)) {
                itemId = (Integer) mFragmentMap.keySet().toArray()[0];
            }
            isFirstLoad = false;
        }
        switchFragment(itemId);
    }

    /**
     * switch Fragment
     *
     * @param itemId
     */
    private void switchFragment(int itemId) {
        Object[] item = getItem(itemId);
        if (item == null || item[0] == null || !(item[0] instanceof Fragment)) {
            return;
        }
        showFragment(getFragmentTransaction(), item);
        mNav.setCheckedItem(itemId);
        mCurrentSelectedItemId = itemId;
        if (mListener != null) {
            mListener.onSwitchFragmentSuccess(mCurrentSelectedItemId);
        }
    }

    private void showFragment(FragmentTransaction transaction, Object[] item) {
//        if (item[0] == null) {
//            return;
//        }
        showFragment(transaction, (Fragment) item[0], item[1], item[2]);
    }


    private void showFragment(FragmentTransaction transaction, Fragment fragment, Object title, Object toolBarMenuRes) {
        if (mCurrentFragment != null) {
            mCurrentFragment.onPause();
            transaction.hide(mCurrentFragment);
        }
        if (fragment.isAdded()) {
            fragment.onResume();
        } else {
            transaction.add(mFragContainerViewId, fragment);
        }
        transaction.show(fragment);
//        transaction.replace(mFragContainerViewId, fragment);
        transaction.commit();
        setToolBar(mMenu, title, toolBarMenuRes);
        mCurrentFragment = fragment;
    }

    /**
     * set toolbar
     *
     * @param menu
     * @param oTitle
     * @param toolBarMenuRes
     */
    private void setToolBar(Menu menu, Object oTitle, Object toolBarMenuRes) {
        setToolbarTitle(oTitle);
        if (menu == null) {
            MLog.i(TAG, "menu is null !");
            return;
        }
        setToolbarMenu(menu, toolBarMenuRes);

    }

    private void setToolbarMenu(Menu menu, Object toolBarMenuRes) {
        //set toolbar menu
        menu.clear();
        if (toolBarMenuRes != null && toolBarMenuRes instanceof Integer) {
            int toolBarMenuResId = (int) toolBarMenuRes;
            if (toolBarMenuResId != RES_ID_NULL) {
                try {
                    mActivity.getMenuInflater().inflate(toolBarMenuResId, menu);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setToolbarTitle(Object oTitle) {
        String title = null;
        if (oTitle == null) {
            //取应用名
            title = sThisAppLabelName;
        } else {
            if (oTitle instanceof Integer) {
                try {
                    title = mActivity.getResources().getString((Integer) oTitle);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (title == null) {
                        title = sThisAppLabelName;
                    }
                }
            } else {
                if (TextUtil.isEmpty((String) oTitle)) {
                    title = sThisAppLabelName;
                } else {
                    title = (String) oTitle;
                }
            }
        }
        mActivity.getSupportActionBar().setTitle(title);
    }

    /**
     * get this app name
     *
     * @return
     */
    private String getThisAppLabelName() {
        return getAppLabelName(mActivity);
    }

    private String getAppLabelName(Context context) {
        int labelRes = context.getApplicationInfo().labelRes;
        return context.getResources().getString(labelRes);
    }

    /**
     * get item by id
     *
     * @param itemId
     * @return
     */
    public Object[] getItem(int itemId) {
        if (!isContainItem(itemId)) {
            MLog.i(TAG, "itemId = " + itemId + " is not contain this item in map collect !");
//            throw new IllegalArgumentException("is not contain this item in map collect !");
            return null;
        }
        Object[] item = mFragmentMap.get(itemId);
//        MLog.i(TAG, "item length :" + item.length);
//        MLog.i(TAG, "key :" + itemId);
//        MLog.i(TAG, "frag :" + item[0]);
//        MLog.i(TAG, "titId :" + item[1]);
//        MLog.i(TAG, "menu :" + item[2]);
        return item;
    }

    private FragmentTransaction getFragmentTransaction() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        //can add animation here
        transaction.setCustomAnimations(org.github.xxbld.icemung.R.anim.right_in, org.github.xxbld.icemung.R.anim.left_out);
        return transaction;
    }

    /**
     * is item contain in Map Collect
     *
     * @param itemId
     * @return
     */
    public boolean isContainItem(int itemId) {
        return mFragmentMap.containsKey(itemId);
    }

    public interface OnNavSelectedListener {
        /**
         * when item clicked or checked
         *
         * @param item
         * @return
         */
        boolean onNavSelected(MenuItem item);

        /**
         * while switch success callback
         *
         * @param currentItemId
         */
        void onSwitchFragmentSuccess(int currentItemId);
    }
}
