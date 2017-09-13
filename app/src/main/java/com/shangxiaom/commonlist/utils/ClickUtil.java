package com.shangxiaom.commonlist.utils;

import android.content.Context;
import android.widget.Toast;

import com.shangxiaom.commonlist.R;


public class ClickUtil {
    private static long doubleClickTime;
    private static long finishClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long interval = time - doubleClickTime;
        if (0 < interval && 900 > interval) {
            doubleClickTime = time;
            return true;
        }
        doubleClickTime = time;
        return false;
    }

    public static boolean isFinishAPP(Context context) {
        long time = System.currentTimeMillis();
        if ((time - finishClickTime) > 2000) {
            ToastUtil.makeText(context, context.getResources().getString(R.string.app_finish_keydown), Toast.LENGTH_SHORT).show();
            finishClickTime = time;
            return false;
        } else {
            return true;
        }
    }

}
