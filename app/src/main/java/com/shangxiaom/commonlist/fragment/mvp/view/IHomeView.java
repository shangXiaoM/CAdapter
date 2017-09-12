package com.shangxiaom.commonlist.fragment.mvp.view;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.fragment.mvp
 * @ 日        期:2017/9/12 12:00
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface IHomeView {

    /**
     * 是否显示进度
     * @param visible
     */
    void onProgressShow(boolean visible);

    /**
     * toast信息
     * @param resId
     */
    void toast(int resId);

    /**
     * 下载图片
     */
    void downloadImages();

    /**
     * 上传图片
     */
    void uploadImages();
}
