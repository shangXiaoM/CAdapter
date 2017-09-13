package com.shangxiaom.commonlist.mvp.activity.view;

import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewHandler;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.interfaces
 * @ 日        期:2017/9/13 17:12
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * home界面接口，包含所有home界面Presenter需要回调到Activity层展示的方法
 * **************************************************
 */
public interface IHomeActivityView extends IViewHandler {
    /**
     * 登录成功
     */
    void loginSuccess();
}
