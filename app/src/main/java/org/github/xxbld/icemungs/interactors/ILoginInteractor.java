package org.github.xxbld.icemungs.interactors;

import org.github.xxbld.icemungs.listeners.OnLoginFinishedListener;

/**
 * Created by xxbld on 2016/2/24
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public interface ILoginInteractor {

     void login(String userName,String pwd,OnLoginFinishedListener loginFinishedListener);
}
