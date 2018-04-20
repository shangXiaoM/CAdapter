/*
 * Copyright (c) 2018 - 4 - 20  11: 8:18
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.shangxiaom.commonlist.MyApplication;

public class WindowsUtils {

    private static WindowsUtils instance;

    private Context mContext;
    private WindowManager mWindowManager;
    private DisplayMetrics mMetrics;

    public WindowsUtils() {
        this.mContext = MyApplication.getContext();
        mWindowManager = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        mMetrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(mMetrics);
    }

    public static WindowsUtils getInstance() {
        if (instance == null) {
            synchronized (WindowsUtils.class) {
                if (instance == null) {
                    instance = new WindowsUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 得到density
     *
     * @return
     */
    public float getDensity() {
        return mMetrics.density;
    }

    /**
     * 得到屏幕的高度
     *
     * @return
     */
    public int getWindowHeight() {
        return mMetrics.heightPixels;
    }

    /**
     * 得到屏幕的宽度
     *
     * @return
     */
    public int getWindowWidth() {
        return mMetrics.widthPixels;
    }

    public DisplayMetrics getmMetrics() {
        return mMetrics;
    }

    private float getWindowSize() {
        float density = mMetrics.density;
        float xdpi = mMetrics.xdpi;
        // float ydpi = mMetrics.ydpi;
        float widthPixels = mMetrics.widthPixels;
        float heightPixels = mMetrics.heightPixels;
        float z = (float) Math.sqrt(Math.pow(widthPixels, 2)
                + Math.pow(heightPixels, 2));
        float size = z / (xdpi);
        return size;
    }

    /**
     * 判断是否为大屏幕还是小屏幕
     *
     * @return
     */
    public boolean isSmallWindow() {
        float size = getWindowSize();
        if (size > 6.0f) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否为横屏
     *
     * @return
     */
    public boolean isHorizontal() {
        if (getWindowWidth() > getWindowHeight()) {
            return true;
        }
        return false;
    }

    /**
     * 固定屏幕的方向
     */
    public void fixOrientation() {
        Activity mActivity = (Activity) mContext;

        if (isHorizontal()) {
            mActivity
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else {
            mActivity
                    .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
    }

    /**
     * 设置屏幕的方向为Sensor
     */
    public void setOrientationToSensor() {
        Activity mActivity = (Activity) mContext;
        mActivity
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    /**
     * 获取一个小网格的大小
     *
     * @return
     */
    public float getSampleCellWidth() {
        float cellWidth = 0f;
        int window_w = 0;
        if (isHorizontal()) {
            window_w = getWindowHeight();
        } else {
            window_w = getWindowWidth();
        }
        int num = 255;
        if (window_w < 600) {
            num = 190;
        }
        cellWidth = window_w / num;
        return cellWidth;
    }

    public void setWrapDialog(Dialog dialog) {
        if (dialog != null) {
            dialog.getWindow().setLayout((int) (mMetrics.widthPixels * 0.96), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
