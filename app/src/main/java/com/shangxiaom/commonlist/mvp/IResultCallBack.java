package com.shangxiaom.commonlist.mvp;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp
 * @ 日        期:2017/9/13 17:53
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface IResultCallBack {
    void start();

    void onSuccess(Object o);

    void onFail(Exception e);
}
