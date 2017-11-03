package com.shangxiaom.commonlist.mvp.activity.view;

import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewHandler;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.view
 * @ 日        期:2017/11/3 14:29
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface ISocketActivityView extends IViewHandler {
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

    /**
     * 更新连接状态
     * @param isConnected   是否已连接
     */
    void updateSocketState(boolean isConnected);

    /**
     * 更新显示的已发送信息
     * @param append    追加的信息
     */
    void updateSendText(String append);

    /**
     *
     * @param msg
     */
    void updateReceivedText(String msg);
}
