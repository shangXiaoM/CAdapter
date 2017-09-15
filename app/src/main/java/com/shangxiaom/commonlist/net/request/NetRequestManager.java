package com.shangxiaom.commonlist.net.request;

import com.shangxiaom.commonlist.net.INetRequestService;
import com.shangxiaom.commonlist.utils.ConstantUtil;
import com.shangxiaom.commonlist.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net.request
 * @ 日        期:2017/9/15 14:26
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class NetRequestManager {
    private static NetRequestManager mInstance;
    private INetRequestService mNetRequestService;

    public static NetRequestManager getInstance() {
        if (null == mInstance) {
            mInstance = new NetRequestManager();
        }
        return mInstance;
    }

    public NetRequestManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(initClient())
                // 如果网络访问返回的字符串不是json数据格式，要使用下面的转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                // 如果网络访问返回的是json字符串，使用gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ConstantUtil.URL_BASE)
                .build();
        mNetRequestService = retrofit.create(INetRequestService.class);
    }

    /**
     * 创建OkHttpClient
     *
     * @return
     */
    private OkHttpClient initClient() {
        //新建一个文件用来缓存网络请求
        File cacheFile = new File(FileUtil.getHttpCacheDir());
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(ConstantUtil.NET_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //设置从主机读信息超时
        builder.readTimeout(ConstantUtil.NET_READ_WRITE_TIMEOUT, TimeUnit.SECONDS);
        //设置写信息超时
        builder.writeTimeout(ConstantUtil.NET_READ_WRITE_TIMEOUT, TimeUnit.SECONDS);
        //设置缓存文件
        builder.cache(new Cache(cacheFile, 10 * 1024 * 1024));
        //设置okhttp拦截器，为每一个retrofit2的网络请求都增加相同的head头信息，而不用每一个请求都写头信息
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .header("Cache-Control", String.format("public, max-age=%d", 60))
                        .removeHeader("Pragma")
                        .build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    public Flowable<ResponseBody> uploadImages(Map<String, RequestBody> images) {
        return mNetRequestService.uploadImage("Android", images);
    }
}
