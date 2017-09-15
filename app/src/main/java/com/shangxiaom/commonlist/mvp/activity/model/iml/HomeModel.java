package com.shangxiaom.commonlist.mvp.activity.model.iml;

import android.net.Uri;

import com.shangxiaom.commonlist.mvp.activity.model.IHomeModel;
import com.shangxiaom.commonlist.mvp.activity.presenter.HomePresenter;
import com.shangxiaom.commonlist.net.OnRequestProgressLinstener;
import com.shangxiaom.commonlist.net.request.NetRequestManager;
import com.shangxiaom.commonlist.net.request.ProgressRequestBody;
import com.shangxiaom.commonlist.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

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
    private HomePresenter mHomePresenter;
    private NetRequestManager mNetRequestManager;

    private int mMaxProgress;
    private int mCurrentProgress;

    public HomeModel(HomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
        mNetRequestManager = NetRequestManager.getInstance();
    }

    @Override
    public void upload(List<Uri> images) {
        mMaxProgress = 0;
        mCurrentProgress = 0;
        Flowable.just(images)
                .subscribeOn(Schedulers.io())
                .map(new Function<List<Uri>, List<File>>() {
                    @Override
                    public List<File> apply(List<Uri> uris) throws Exception {
                        List<File> files = new ArrayList<>();
                        for (Uri uri : uris) {
                            String path = FileUtil.getImagePath(uri);
                            if (null != path) {
                                File file = new File(path);
                                mMaxProgress += file.length();
                                files.add(file);
                            }
                        }
                        return files;
                    }
                })
                .map(new Function<List<File>, Map<String, RequestBody>>() {
                    @Override
                    public Map<String, RequestBody> apply(List<File> files) throws Exception {
                        Map<String, RequestBody> map = new HashMap<>();
                        for (File f : files) {
                            ProgressRequestBody progressRequestBody = new ProgressRequestBody(f, new OnRequestProgressLinstener() {
                                @Override
                                public void onProgress(long progress, long totalLen, boolean hasFinish) {
                                    mCurrentProgress += progress;
                                    if (mCurrentProgress >= mMaxProgress) {
                                        mHomePresenter.uploadSuccess();
                                    } else {
                                        mHomePresenter.onProgressUpdate(mCurrentProgress);
                                    }
                                }
                            });
                            mMaxProgress += progressRequestBody.contentLength();
                            map.put(f.getName(), progressRequestBody);
                        }
                        return map;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Map<String, RequestBody>>() {
                    @Override
                    public void accept(Map<String, RequestBody> stringRequestBodyMap) throws Exception {
                        mHomePresenter.ingDismiss();
                        mHomePresenter.uploadProgressShow(mMaxProgress);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Map<String, RequestBody>>() {
                    @Override
                    public void accept(Map<String, RequestBody> map) throws Exception {
                        mNetRequestManager.uploadImages(map)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<ResponseBody>() {
                                    @Override
                                    public void accept(ResponseBody responseBody) throws Exception {
                                        String str = responseBody.string();
                                    }
                                });
                    }
                });
    }
}
