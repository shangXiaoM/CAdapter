package com.shangxiaom.commonlist;

import android.app.Application;
import android.content.Context;

import com.shangxiaom.commonlist.log.CrashHandler;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist
 * @ 日        期:2017/9/11 16:13
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class MyApplication extends Application {
    public static MyApplication instance;

    /**
     * 获取APPlication的APP
     *
     * @return
     */
    public static Context getContext() {
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        instance = this;
    }
}
