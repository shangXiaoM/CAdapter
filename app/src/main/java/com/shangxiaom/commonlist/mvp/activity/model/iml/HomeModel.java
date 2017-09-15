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
    private HomePresenter mHomePresenter;

    public HomeModel(HomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
    }

    @Override
    public boolean upload(final Uri image) {
        SystemClock.sleep(1000);
        // TODO 上传进度单独使用presenter
        return true;
    }
}
