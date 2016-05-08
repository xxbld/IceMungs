package org.github.xxbld.icemungs.ui.widgets.materialdialogs;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by xxbld on 2016/5/7.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 使用 MaterialDialog库
 */
public class MaterialDialogHelper {

    public static void showBaseDialog(Context context) {
        new MaterialDialog.Builder(context)
                .title("标题")
                .content("内容")
                .positiveText("同意")
                .negativeText("不同意")
                .show();
    }
}
