package org.github.xxbld.icemungs.ui.chooseschool;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.School;

import java.util.Collection;

/**
 * Created by xxbld on 2016/5/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolChooseAdapter extends BaseRecyclerViewAdapter<School> {

    public SchoolChooseAdapter(RecyclerView v, Collection<School> datas) {
        super(v, datas, R.layout.item_choose_school_school);
    }

    @Override
    public void convert(final BaseRecyclerHolder holder, final School item, int position, boolean isScrolling) {
        holder.setText(R.id.school_name, item.getSchoolName());
        holder.setImageByUrl(R.id.school_logo, item.getSchoolLogoImgUrl());
        CheckBox checkBox = holder.getView(R.id.school_check);
        ImageButton expend = holder.getView(R.id.school_expand);
        expend.setVisibility(View.GONE);
    }
}
