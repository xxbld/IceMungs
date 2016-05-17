package org.github.xxbld.icemungs.ui.schoolnews;

import android.support.v7.widget.RecyclerView;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemungs.data.models.School;

import java.util.Collection;

/**
 * Created by xxbld on 2016/5/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolNewAdapter extends BaseRecyclerViewAdapter<School> {

    public SchoolNewAdapter(RecyclerView v, Collection<School> datas, int itemLayoutId) {
        super(v, datas, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, School item, int position, boolean isScrolling) {

    }
}
