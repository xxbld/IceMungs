package org.github.xxbld.icemungs.ui.chooseschool;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.Academy;
import org.github.xxbld.icemungs.data.models.School;
import org.github.xxbld.icemungs.presenters.ChooseSchoolPresenter;
import org.github.xxbld.icemungs.views.IChooseSchoolView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xxbld on 2016/5/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class SchoolChooseAdapter extends BaseRecyclerViewAdapter<School> {

    ChooseSchoolPresenter mChooseSchoolPresenter;

    public SchoolChooseAdapter(RecyclerView v, Collection<School> datas, ChooseSchoolPresenter chooseSchoolPresenter) {
        super(v, datas, R.layout.item_school);
        this.mChooseSchoolPresenter = chooseSchoolPresenter;
    }

    @Override
    public void convert(final BaseRecyclerHolder holder, final School item, int position, boolean isScrolling) {
        holder.setText(R.id.school_name, item.getSchoolName());
        holder.setImageByUrl(R.id.school_logo, item.getSchoolLogoImgUrl());
        final CheckBox checkBox = holder.getView(R.id.school_check);
        final ImageButton expend = holder.getView(R.id.school_expand);
        final ExpandableRelativeLayout expandableRelativeLayout = holder.getView(R.id.school_expand_layout);
        expend.setVisibility(View.VISIBLE);
        expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expend.isPressed()) {
                    expend.setPressed(false);
                    expandableRelativeLayout.toggle();
                } else {
                    if (checkBox.isChecked()) {
                        mChooseSchoolPresenter.loadAcademy(item.getObjectId());
                        expend.setPressed(true);
                        expandableRelativeLayout.expand();
                        RecyclerView recyclerAcademy = holder.getView(R.id.academy_item);
                        recyclerAcademy.setLayoutManager(new LinearLayoutManager(cxt, LinearLayoutManager.VERTICAL, false));
                        AcademyAdapter mAcademyAdapter = new AcademyAdapter(recyclerAcademy, new ArrayList<Academy>());
                        recyclerAcademy.setAdapter(mAcademyAdapter);
                    }
                }
            }
        });

    }

    public class AcademyAdapter extends BaseRecyclerViewAdapter<Academy> implements IChooseSchoolView {

        public AcademyAdapter(RecyclerView v, Collection<Academy> datas) {
            super(v, datas, R.layout.item_school);
        }

        @Override
        public void convert(BaseRecyclerHolder holder, Academy item, int position, boolean isScrolling) {
            holder.setText(R.id.school_name, item.getAcademyName());
            holder.setImageByUrl(R.id.school_logo, item.getAcademyLogoImgUrl());
            final CheckBox checkBox = holder.getView(R.id.school_check);
            final ImageButton expend = holder.getView(R.id.school_expand);
            final ExpandableRelativeLayout expandableRelativeLayout = holder.getView(R.id.school_expand_layout);
            expend.setVisibility(View.VISIBLE);
        }

        @Override
        public void setRecycler(Object object) {
            if (object != null) {
                List<Academy> academies = (List<Academy>) object;
                this.refresh(academies);
            }
        }

        @Override
        public void showLoading(String msg) {

        }

        @Override
        public void hideLoading() {

        }

        @Override
        public void showError(String msg) {

        }

        @Override
        public void showException(String msg) {

        }

        @Override
        public void showNetError() {

        }
    }
}
