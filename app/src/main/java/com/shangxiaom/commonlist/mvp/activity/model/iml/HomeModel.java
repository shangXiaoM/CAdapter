package com.shangxiaom.commonlist.mvp.activity.model.iml;

import android.net.Uri;
import android.os.SystemClock;

import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;
import com.shangxiaom.commonlist.mvp.activity.presenter.HomePresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
    private int count;
    private HomePresenter mHomePresenter;

    public HomeModel() {
    }

    public HomeModel(HomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
    }

    @Override
    public void upload(final List<Uri> images) {
        count = 0;
        Observable.just(images)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<List<Uri>, Observable<Uri>>() {
                    @Override
                    public Observable<Uri> apply(List<Uri> uris) throws Exception {
                        return Observable.fromIterable(uris);
                    }
                })
                .doOnNext(new Consumer<Uri>() {
                    @Override
                    public void accept(Uri uri) throws Exception {
                        SystemClock.sleep(1000);
                        count++;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Uri>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Uri uri) {
                        float temp = 100 * count / images.size();
                        mHomePresenter.onProgressUpdate((int) temp);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mHomePresenter.uploadFail(null);
                    }

                    @Override
                    public void onComplete() {
                        mHomePresenter.uploadSuccess();
                    }
                });
    }
}
