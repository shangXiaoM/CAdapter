package com.shangxiaom.commonlist.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * 显示进度
 */
public class ProgressUtil {

    private static ProgressDialog mDialog;

    public static void show(Context context, String title, String msg, boolean isCancel, final DialogInterface.OnCancelListener cancelCallback) {
        dismiss();
        mDialog = new ProgressDialog(context);
        if (!StringUtil.isEmpty(title)) {
            mDialog.setTitle(title);
        }
        mDialog.setMessage(msg);
        mDialog.setCancelable(isCancel);
        mDialog.setCanceledOnTouchOutside(isCancel);
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (cancelCallback != null)
                    cancelCallback.onCancel(dialog);
                mDialog = null;
            }
        });
        mDialog.show();
    }

    public static void setMessage(String msg) {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.setMessage(msg);
        }
    }

    public static boolean isShowing() {
        if (null != mDialog) {
            return mDialog.isShowing();
        }
        return false;
    }

    public static void dismiss() {
        if (isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }
}
