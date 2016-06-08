package org.github.xxbld.icemungs.ui.verbose;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerHolder;
import org.github.xxbld.icemung.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import org.github.xxbld.icemung.glide.GlideHelper;
import org.github.xxbld.icemung.utils.MLog;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.data.models.ITalk;
import org.github.xxbld.icemungs.data.models.Student;
import org.github.xxbld.icemungs.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xxbld on 2016/5/18.
 * you can contact me at: 1024920618@qq.com
 *
 * @description :
 */
public class ITalkFragment1 extends BaseFragment {

    @Bind(R.id.common_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.personal_root)
    LinearLayout mRoot;

    private BaseRecyclerViewAdapter<ITalk> mBaseRecyclerViewAdapter;
    private List<ITalk> mList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.frag_personal_data;
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        mBaseRecyclerViewAdapter = new BaseRecyclerViewAdapter<ITalk>(mRecyclerView, mList, R.layout.item_versobe_italk) {
            @Override
            public void convert(BaseRecyclerHolder holder, ITalk item, int position, boolean isScrolling) {
                Student student = null;
                if (item.getStudent() != null && item.getStudent().getIconUrl() != null) {
                    student = item.getStudent();
                } else {
                    student = BmobUser.getCurrentUser(getActivity(), Student.class);
                }
                if (student.getIconUrl() != null) {
                    GlideHelper.tranCircleImage(cxt, student.getIconUrl(), (ImageView) holder.getView(R.id.item_italk_icon));
                } else {
                    GlideHelper.tranCircleImage(cxt, R.drawable.ic_user, (ImageView) holder.getView(R.id.item_italk_icon));
                }
                holder.setText(R.id.item_italk_iname, item.getStudent().getUsername());
                holder.setText(R.id.item_italk_content, item.getiTalkContent());
                RecyclerView imgGrid = holder.getView(R.id.item_italk_images);
                List<String> imageUrls = item.getiTalkImageUrls();
                imageUrls = null;
                if (imageUrls != null && imageUrls.size() > 0) {
                    imgGrid.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                    imgGrid.setAdapter(new BaseRecyclerViewAdapter<String>(imgGrid, imageUrls, R.layout.item_versobe_italk_image) {
                        @Override
                        public void convert(BaseRecyclerHolder holder, String item, int position, boolean isScrolling) {
                            if (item != null) {
                                GlideHelper.tranRoundImage(cxt, item, 8, (ImageView) holder.getView(R.id.item_italk_image));
                            } else {
                                GlideHelper.tranRoundImage(cxt, R.drawable.material_design_2, 8, (ImageView) holder.getView(R.id.item_italk_image));
                            }
                        }
                    });
                } else {
//                    TODO 假的
                    imageUrls = new ArrayList<>();
                    if (position == 0) {
                    } else if (position == 1) {
                        imageUrls.addAll(getImageUrLs());
                    }
                    imgGrid.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                    imgGrid.setAdapter(new BaseRecyclerViewAdapter<String>(imgGrid, imageUrls, R.layout.item_versobe_italk_image) {
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
                holder.getView(R.id.item_italk_share).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder.getView(R.id.item_italk_comment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                holder.getView(R.id.item_italk_goodjob).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.isPressed()) {
                            v.setPressed(false);
                        } else {
                            v.setPressed(true);
                        }
                    }
                });
            }
        };
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mBaseRecyclerViewAdapter);
        queryITalk();
    }


    private String getImageUrL(int postion) {
        List<String> imageUrLs = getImageUrLs();
        if (postion > imageUrLs.size()) {
            postion = imageUrLs.size() - 1;
        }
        return imageUrLs.get(postion);
    }

    private List<String> getImageUrLs() {
        List<String> list = new ArrayList<>();
        list.add("http://bmob-cdn-1405.b0.upaiyun.com/2016/06/06/cdd87c9140847b2d80f1d1021b725c71.png");
        list.add("http://bmob-cdn-1405.b0.upaiyun.com/2016/06/06/a6ca1a0d40865515800eb63b40c691ac.png");
        list.add("http://bmob-cdn-1405.b0.upaiyun.com/2016/06/06/2a6c0a8940c7924f80c791958e116448.png");
        list.add("http://bmob-cdn-1405.b0.upaiyun.com/2016/06/06/b02bdc534054cfb880026ab3bd8cfbcd.png");
        return list;
    }


    private void queryITalk() {
        toggleShowLoading(true, "加载中...");
        BmobQuery<ITalk> iTalkBmobQuery = new BmobQuery<>();
//        iTalkBmobQuery.include("student");
//        iTalkBmobQuery.addWhereExists("student");
        iTalkBmobQuery.findObjects(getActivity(), new FindListener<ITalk>() {
            @Override
            public void onSuccess(List<ITalk> list) {
                MLog.i(TAG, "" + list.size());
                if (mBaseRecyclerViewAdapter != null && list != null && list.size() > 0) {
//                    mList.addAll(list);
//                    mBaseRecyclerViewAdapter.notifyDataSetChanged();
                    mBaseRecyclerViewAdapter.refresh(list);
                }
                toggleShowLoading(false, null);
            }

            @Override
            public void onError(int i, String s) {
                toggleShowEmpty(true, "数据为空...", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        queryITalk();
                    }
                });
            }
        });
    }

    @Override
    protected View getLoadingTargetView() {
        return mRoot;
    }
}
