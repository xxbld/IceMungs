package org.github.xxbld.icemungs.views;

import org.github.xxbld.icemung.base.mvp.IMvpView;
import org.github.xxbld.icemungs.data.models.News;

import java.util.List;

/**
 * Created by xxbld on 2016/5/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public interface IFragNewsView extends IMvpView {

    /**
     * 为recyclerView设置新闻
     */
    void setRecyclerNews(List<News> newses);

}
