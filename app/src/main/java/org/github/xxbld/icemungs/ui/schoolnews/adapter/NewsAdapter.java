package org.github.xxbld.icemungs.ui.schoolnews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.News;

import java.util.Collection;

/**
 * Created by xxbld on 2016/5/15.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : news adapter
 */
public class NewsAdapter extends BaseRecyclerViewAdapter<News> {
    private static final int IMG_RADIUS = 6;
    private static final int DEFAULT_IMG_RES = R.drawable.bamboo;

    public NewsAdapter(RecyclerView v, Collection<News> datas) {
        super(v, datas, R.layout.item_news);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, News item, int position, boolean isScrolling) {
        holder.setText(R.id.news_title, item.getNewsTitle());
        holder.setText(R.id.news_content, item.getNewsIntroduction());
        if (item.getNewsImageUrl() != null) {
            GlideHelper.tranRoundImage(cxt, item.getNewsImageUrl(), IMG_RADIUS, (ImageView) holder.getView(R.id.news_image));
        } else {
            GlideHelper.tranRoundImage(cxt, DEFAULT_IMG_RES, IMG_RADIUS, (ImageView) holder.getView(R.id.news_image));
        }
        final ImageView collection = holder.getView(R.id.news_collection);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collection.setImageResource(R.drawable.ic_star_pink_600_18dp);
            }
        });
    }

}
