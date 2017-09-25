package com.shangxiaom.commonlist.net.api;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net.api
 * @ 日        期:2017/9/18 17:28
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * @ 回调信息统一封装类
 * **************************************************
 */
public class BaseResultEntity<T> {
    // 判断标示
    private int resultCode;
    // 提示信息
    private String errorMsg;
    //显示数据（用户需要关心的数据）
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
