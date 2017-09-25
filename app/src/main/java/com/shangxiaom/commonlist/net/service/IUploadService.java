package com.shangxiaom.commonlist.net.service;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
public interface IUploadService {
    @Multipart
    @POST("上传的相对路径/使用@path实现")
    Flowable<ResponseBody> uploadImage(
            @Part("srcType") String mSrcType,
            @PartMap Map<String, RequestBody> file
    );
}
