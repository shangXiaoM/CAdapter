package com.shangxiaom.commonlist.mvp.activity.presenter;

import android.net.Uri;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewPresenter;
import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;
import com.shangxiaom.commonlist.mvp.activity.model.iml.HomeModel;
import com.shangxiaom.commonlist.mvp.activity.view.IHomeActivityView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    private int mTestProgress;

    public HomePresenter() {
        mHomeModel = new HomeModel(this);
        mUploadErrorImages = new ArrayList<>();
    }

    public void upload(List<Uri> images) {
        uploadProgressShow(images.size());
        mUploadErrorImages.clear();
        mTestProgress = 0;
        Observable.just(images)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<Uri>, ObservableSource<Uri>>() {
                    @Override
                    public ObservableSource<Uri> apply(@NonNull List<Uri> uris) throws Exception {
                        return Observable.fromIterable(uris);
                    }
                })
                .map(new Function<Uri, Uri>() {
                    @Override
                    public Uri apply(@NonNull Uri uri) throws Exception {
                        if (!mHomeModel.upload(uri)) {
                            mUploadErrorImages.add(uri);
                        }
                        return uri;
                    }
                })
                .subscribe(new Observer<Uri>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Uri uri) {
                        mTestProgress++;
                        onProgressUpdate(mTestProgress);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        if (0 == mUploadErrorImages.size()) {
                            uploadSuccess();
                        } else {
                            uploadFail(mUploadErrorImages);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (0 == mUploadErrorImages.size()) {
                            uploadSuccess();
                        } else {
                            uploadFail(mUploadErrorImages);
                        }
                    }
                });
    }

    public void uploadProgressShow(int max) {
        mViewHandler.uploadProgressShow(max);
    }

    public void onProgressUpdate(int progress) {
        Flowable.just(progress)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mViewHandler.onProgressUpdate(integer);
                    }
                });
    }

    public void uploadSuccess() {
        Flowable.just("")
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
        Flowable.just(images)
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
}
