
package com.shangxiaom.commonlist.net;

public interface OnRequestProgressLinstener {
    /**
     * 更新进度
     *
     * @param progress  当前进度
     * @param totalLen  总进度
     * @param hasFinish 是否完成
     */
    void onProgress(long progress, long totalLen, boolean hasFinish);
}
