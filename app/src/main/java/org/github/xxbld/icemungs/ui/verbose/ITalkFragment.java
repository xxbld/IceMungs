package org.github.xxbld.icemungs.ui.verbose;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.ITalk;
import org.github.xxbld.icemungs.ui.base.BasePtrClassicFragment;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/5/18.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class ITalkFragment extends BasePtrClassicFragment {

    private BaseRecyclerViewAdapter<ITalk> mBaseRecyclerViewAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_ptr_base;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
    }

    @Override
    protected boolean isSetPtrLayout() {
        return true;
    }

    @Override
    protected void onPtrRefreshBegin() {
        queryITalk();
    }

    private void queryITalk() {
        BmobQuery<ITalk> iTalkBmobQuery = new BmobQuery<>();
        iTalkBmobQuery.include("student");
        iTalkBmobQuery.findObjects(getActivity(), new FindListener<ITalk>() {
            @Override
            public void onSuccess(List<ITalk> list) {
                if (mBaseRecyclerViewAdapter == null) {
                    mBaseRecyclerViewAdapter = new BaseRecyclerViewAdapter<ITalk>(mRecyclerView, list, R.layout.item_versobe_italk) {
                        @Override
                        public void convert(BaseRecyclerHolder holder, ITalk item, int position, boolean isScrolling) {
                            if (item.getStudent() != null) {
                                if (item.getStudent().getIconUrl() != null) {
                                    GlideHelper.tranCircleImage(cxt, item.getStudent().getIconUrl(), (ImageView) holder.getView(R.id.item_italk_icon));
                                } else {
                                    GlideHelper.tranCircleImage(cxt, R.drawable.ic_user, (ImageView) holder.getView(R.id.item_italk_icon));
                                }
                                holder.setText(R.id.item_italk_iname, item.getStudent().getUsername());
                                holder.setText(R.id.item_italk_content, item.getiTalkContent());
                            }
                            List<String> imageUrls = item.getiTalkImageUrls();
                            if (imageUrls != null && imageUrls.size() > 0) {
                                RecyclerView imgGrid = holder.getView(R.id.item_italk_images);
                                imgGrid.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                                imgGrid.setAdapter(new BaseRecyclerViewAdapter<String>(imgGrid, imageUrls, R.layout.item_versobe_italk) {
                                    @Override
                                    public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
                                        if (item != null) {
                                            GlideHelper.tranRoundImage(cxt, item, 8, (ImageView) holder.getView(R.id.item_italk_image));
                                        } else {
                                            GlideHelper.tranRoundImage(cxt, R.drawable.material_design_2, 8, (ImageView) holder.getView(R.id.item_italk_image));
                                        }
                                    }
                                });
                            }

                        }
                    };
                    mRecyclerView.setAdapter(mBaseRecyclerViewAdapter);
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                } else {
                    mBaseRecyclerViewAdapter.refresh(list);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @Override
    protected boolean isPtrAutoRefresh() {
        return true;
    }

    @Override
    protected long getPtrAutoRefreshDelayMillis() {
        return 0;
    }
}
