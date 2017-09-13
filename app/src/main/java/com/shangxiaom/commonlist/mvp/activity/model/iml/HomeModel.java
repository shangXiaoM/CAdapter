package com.shangxiaom.commonlist.mvp.activity.model.iml;

import com.shangxiaom.commonlist.mvp.IResultCallBack;
import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.model.iml
 * @ 日        期:2017/9/13 17:56
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class HomeModel implements IHomeModel {

    @Override
    public void login(IResultCallBack resultCallBack) {
        resultCallBack.onSuccess(null);
    }
}
