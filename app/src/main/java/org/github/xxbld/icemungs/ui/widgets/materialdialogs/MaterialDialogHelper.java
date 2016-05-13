package org.github.xxbld.icemungs.ui.widgets.materialdialogs;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by xxbld on 2016/5/7.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 使用 MaterialDialog库
 */
public class MaterialDialogHelper {

    public void showBaseDialog(Context context) {
        new MaterialDialog.Builder(context)
                .title("标题")
                .content("内容")
                .positiveText("同意")
                .negativeText("不同意")
                .show();
    }


    /**
     *
     * @param context
     * @param title
     * @param content
     * @param positiveText
     * @param negativeText
     * @param cancelListener
     * @return
     */
    public static MaterialDialog getBaseDialog(Context context, String title, String content, String positiveText, String negativeText, DialogInterface.OnCancelListener cancelListener) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .cancelListener(cancelListener)
                .build();
    }

    /**
     * 获取登录提示对话框
     *
     * @param context
     * @return
     */
    public static MaterialDialog getLoginAlertDialog(Context context) {
        return getProgressDialog(context, "正在登录", "请稍等...", true);
    }

    /**
     * 获取注册提示对话框
     *
     * @param context
     * @return
     */
    public static MaterialDialog getRegisterAlertDialog(Context context) {
        return getProgressDialog(context, "正在注册", "请稍等...", true);
    }

    public static MaterialDialog getProgressDialog(Context context, String title, String content, boolean horizontal) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(horizontal)
                .build();
    }
}
