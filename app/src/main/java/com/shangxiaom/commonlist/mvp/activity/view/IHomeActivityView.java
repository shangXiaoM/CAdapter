package com.shangxiaom.commonlist.mvp.activity.view;

import android.net.Uri;

import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewHandler;

import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.interfaces
 * @ 日        期:2017/9/13 17:12
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * home界面接口，包含所有home界面Presenter需要回调到Activity层展示的方法
 * **************************************************
 */
public interface IHomeActivityView extends IViewHandler {

    /**
     * 显示上传进度
     * @param maxSize   进度最大值
     */
    void uploadProgressShow(int maxSize);

    /**
     * 更新上传进度
     * @param current   当前进度
     */
    void onProgressUpdate(int current);

    /**
     * 上传成功
     */
    void uploadSuccess();

    /**
     * 上传失败
     * @param images   上传失败的图片uri
     */
    void uploadFail(List<Uri> images);

    /**
     * 关闭上传进度款
     */
    void uploadProgressDismiss();

    /**
     * 显示进度框
     * @param resId 资源ID
     */
    void ingShow(int resId);

    /**
     * 关闭进度框
     */
    void ingDismiss();

    /**
     * 显示toast信息
     * @param resId 资源id
     */
    void toastShow(int resId);
}
