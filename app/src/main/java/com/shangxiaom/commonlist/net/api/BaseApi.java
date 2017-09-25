package com.shangxiaom.commonlist.net.api;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net.api
 * @ 日        期:2017/9/18 17:14
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public abstract class BaseApi<T> implements Function<BaseResultEntity<T>, T> {

    /*是否能取消加载框*/
    private boolean mCancelable;
    /*是否显示加载框*/
    private boolean mProgressShow;
    /*是否需要缓存处理*/
    private boolean mCache;
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置*/
    private String mMethod;
    /*有网情况下的本地缓存时间默认60秒*/
    private int mCookieNetWorkTime = 60;
    /*无网络的情况下本地缓存时间默认30天*/
    private int mCookieNoNetWorkTime = 24 * 60 * 60 * 30;

    public abstract Observable getObservable(Retrofit retrofit);

    public abstract T call(BaseResultEntity<T> httpResult);
}
