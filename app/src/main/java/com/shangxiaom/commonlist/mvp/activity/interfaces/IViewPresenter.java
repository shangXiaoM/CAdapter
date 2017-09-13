package com.shangxiaom.commonlist.mvp.activity.interfaces;

import android.content.Context;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp
 * @ 日        期:2017/9/13 16:00
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public abstract class IViewPresenter<T extends IViewHandler> {
    public T mView; //这个是要回调的接口的对象
    public Context mContext;
    protected String token = "token";

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
