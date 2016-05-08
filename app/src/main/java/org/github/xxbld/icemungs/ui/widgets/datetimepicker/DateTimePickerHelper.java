package org.github.xxbld.icemungs.ui.widgets.datetimepicker;

import android.app.DialogFragment;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.github.xxbld.icemungs.R;

import java.util.Calendar;

/**
 * Created by xxbld on 2016/5/7.
 * you can contact me at: 1024920618@qq.com
 *
 * @description : 使用DateTimePicker库
 */
public class DateTimePickerHelper {

    private DateTimePickerHelper() {
    }

    /**
     * picker 默认设置
     *
     * @param dialog
     */
    private static void setDefaultPicker(DialogFragment dialog) {
        if (dialog instanceof DatePickerDialog) {
            DatePickerDialog datePickerDialog = (DatePickerDialog) dialog;
            datePickerDialog.setAccentColor(dialog.getResources().getColor(R.color.colorAccent));
            datePickerDialog.showYearPickerFirst(true);
            datePickerDialog.dismissOnPause(true);
        }
        if (dialog instanceof TimePickerDialog) {
            TimePickerDialog timePickerDialog = (TimePickerDialog) dialog;
            timePickerDialog.setAccentColor(dialog.getResources().getColor(R.color.colorAccent));
            timePickerDialog.dismissOnPause(true);
        }

    }

    /**
     * get date pick dialog
     *
     * @param onDateSetListener
     * @return
     */
    public static DatePickerDialog getDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                onDateSetListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        setDefaultPicker(dpd);
        return dpd;
    }

    /**
     * get time pick dialog
     *
     * @param onTimeSetListener
     * @return
     */
    public static TimePickerDialog getTimePicker(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                onTimeSetListener,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true //24小时制
        );
        setDefaultPicker(tpd);
        return tpd;
    }
}
