package com.shangxiaom.commonlist.net;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net
 * @ 日        期:2017/9/15 11:22
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface INetRequestService {
    // get请求，参数为basil2style
    @GET("basil2style")
    Observable<ResponseBody> getTestStr();     //返回的方法，返回的响应体使用了ResponseBody

    @Multipart
    @POST("上传的相对路径/使用@path实现")
    Flowable<ResponseBody> uploadImage(
            @Part("srcType") String mSrcType,
            @PartMap Map<String, RequestBody> file
    );
}
