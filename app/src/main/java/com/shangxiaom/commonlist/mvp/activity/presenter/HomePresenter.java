package com.shangxiaom.commonlist.mvp.activity.presenter;

import android.net.Uri;

import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewPresenter;
import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;
import com.shangxiaom.commonlist.mvp.activity.model.iml.HomeModel;
import com.shangxiaom.commonlist.mvp.activity.view.IHomeActivityView;

import java.util.List;

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
        mHomeModel = new HomeModel(this);
    }

    public void upload(List<Uri> images) {
        mViewHandler.uploadProgressShow(100);
        mHomeModel.upload(images);
    }

    public void onProgressUpdate(int progress) {
        mViewHandler.onProgressUpdate(progress);
    }

    public void uploadSuccess() {
        mViewHandler.uploadProgressDismiss();
        mViewHandler.uploadSuccess();
    }

    public void uploadFail(List<Uri> images) {
        mViewHandler.uploadFail(images);
        mViewHandler.uploadProgressDismiss();
    }
}
