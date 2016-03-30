package org.github.xxbld.icemungs.ui.adapter;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.github.xxbld.icemung.utils.MLog;

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
    private boolean isFirstLoad = true;
    private Fragment mCurrentFragment = null;
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
     * NavFragment Adapter
     * <p>此方法须在 onCreateOptionsMenu中(或之后)使用才能获取到menu</p>
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
        this.switchFragment(itemId);
    }

    /**
     * switch Fragment
     *
     * @param itemId
     */
    private void switchFragment(int itemId) {
        Object[] item = getItem(itemId);
        if (item == null) {
            return;
        }
        showFragment(getFragmentTransaction(), item);
        mNav.setCheckedItem(itemId);
        mCurrentSelectedItemId = itemId;
    }

    private void showFragment(FragmentTransaction transaction, Object[] item) {
        if (item[0] == null) {
            return;
        }
        if (item[1] == null) {
            item[1] = RES_ID_NULL;
        }
        if (item[2] == null) {
            item[2] = RES_ID_NULL;
        }
        showFragment(transaction, (Fragment) item[0], (Integer) item[1], (Integer) item[2]);
    }

    private void showFragment(FragmentTransaction transaction, Fragment fragment, int titleResId, int toolBarMenuResId) {
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
        transaction.commit();
        setToolBar(mMenu, fragment, titleResId, toolBarMenuResId);
        mCurrentFragment = fragment;
    }

    /**
     * set toolbar
     *
     * @param menu
     * @param fragment
     * @param titleResId
     * @param toolBarMenuResId
     */
    private void setToolBar(Menu menu, Fragment fragment, int titleResId, int toolBarMenuResId) {
        if (menu == null) {
            MLog.i(TAG, "menu null !");
            return;
        }
        //settoolbar
        if (titleResId != RES_ID_NULL) {
//            MLog.i(TAG, "set Title :" + titleResId);
            mActivity.getSupportActionBar().setTitle(mActivity.getResources().getString(titleResId));
        }
        //set toolbar menu
        menu.clear();
        if (toolBarMenuResId != RES_ID_NULL) {
//            MLog.i(TAG, "set Menu :" + toolBarMenuResId);
            mActivity.getMenuInflater().inflate(toolBarMenuResId, menu);
        }
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

    public boolean isContainItem(int itemId) {
        return mFragmentMap.containsKey(itemId);
    }

    public interface OnNavSelectedListener {
        boolean onNavSelected(MenuItem item);
    }
}
