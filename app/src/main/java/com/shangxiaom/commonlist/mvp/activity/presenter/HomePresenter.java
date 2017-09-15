package com.shangxiaom.commonlist.mvp.activity.presenter;

import android.net.Uri;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewPresenter;
import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;
import com.shangxiaom.commonlist.mvp.activity.model.iml.HomeModel;
import com.shangxiaom.commonlist.mvp.activity.view.IHomeActivityView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

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

    private List<Uri> mUploadErrorImages;

    public HomePresenter() {
        mHomeModel = new HomeModel(this);
        mUploadErrorImages = new ArrayList<>();
    }

    public void upload(List<Uri> images) {
        ingShow(R.string.please_wait);
        mUploadErrorImages.clear();
        mHomeModel.upload(images);
    }

    public void uploadProgressShow(int max) {
        mViewHandler.uploadProgressShow(max);
    }

    public void onProgressUpdate(int progress) {
        Observable.just(progress)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mViewHandler.onProgressUpdate(integer);
                    }
                });
    }

    public void uploadSuccess() {
        Observable.just("")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        mViewHandler.toastShow(R.string.images_upload_success);
                        mViewHandler.uploadProgressDismiss();
                        mViewHandler.uploadSuccess();
                    }
                });

    }

    public void uploadFail(List<Uri> images) {
        Observable.just(images)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Uri>>() {
                    @Override
                    public void accept(List<Uri> images) throws Exception {
                        mViewHandler.toastShow(R.string.images_upload_fail);
                        mViewHandler.uploadFail(images);
                        mViewHandler.uploadProgressDismiss();
                    }
                });
    }

    public void ingShow(int resId) {
        mViewHandler.ingShow(resId);
    }

    public void ingDismiss() {
        Observable.just("")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        mViewHandler.ingDismiss();
                    }
                });
    }
}
