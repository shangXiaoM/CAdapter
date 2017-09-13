package com.shangxiaom.commonlist.mvp.activity.presenter;

import com.shangxiaom.commonlist.mvp.IResultCallBack;
import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewPresenter;
import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;
import com.shangxiaom.commonlist.mvp.activity.model.iml.HomeModel;
import com.shangxiaom.commonlist.mvp.activity.view.IHomeActivityView;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.presenter
 * @ 日        期:2017/9/13 17:41
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class HomePresenter extends IViewPresenter<IHomeActivityView> {
    private IHomeModel mHomeModel;

    public HomePresenter() {
        mHomeModel = new HomeModel();
    }

    public void login() {
        mHomeModel.login(new IResultCallBack() {
            @Override
            public void start() {
            }

            @Override
            public void onSuccess(Object o) {
                mView.loginSuccess();
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }
}
