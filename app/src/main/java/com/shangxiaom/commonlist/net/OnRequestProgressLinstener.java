package com.shangxiaom.commonlist.net;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.net
 * @ 日        期:2017/9/15 18:18
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface OnRequestProgressLinstener {
    /**
     * 更新进度
     * @param progress  当前进度
     * @param totalLen  总进度
     * @param hasFinish 是否完成
     */
    void onProgress(long progress, long totalLen, boolean hasFinish);
}
