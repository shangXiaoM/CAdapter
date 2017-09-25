package com.shangxiaom.commonlist.net.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net
 * @ 日        期:2017/9/18 17:42
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface ITestService {
    // get请求，参数为basil2style
    @GET("basil2style")
    Observable<ResponseBody> getTestStr();     //返回的方法，返回的响应体使用了ResponseBody
}
