package org.github.xxbld.icemungs.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.github.xxbld.icemung.utils.StatusBarUtil;
import org.github.xxbld.icemungs.R;
import org.github.xxbld.icemungs.ui.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by xxbld on 2016/2/19
 * you can contact me at: 1024920618@qq.com
 *
 * @descript ：
 */
public class TestActivity1 extends BaseActivity {
    @Bind(R.id.btn1)
    Button mBtn1;
    @Bind(R.id.btn2)
    Button mBtn2;
    @Bind(R.id.linear)
    LinearLayout mLayout;

    @Override
    protected int getContentViewLayoutResID() {
        return R.layout.activity_test1;
    }

    @Override
    protected void initViewsAndEvents(){

        StatusBarUtil.setTranslucent(this);

       final SystemBarTintManager tintManager= new SystemBarTintManager(TestActivity1.this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透明
                mLayout.setBackgroundResource(R.mipmap.material_design_3);
                tintManager.setTintColor(getResources().getColor(R.color.md_red_A400));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //颜色
                mLayout.setBackgroundResource(0);
                tintManager.setTintColor(Color.parseColor("#FF4081"));
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
    }


}
