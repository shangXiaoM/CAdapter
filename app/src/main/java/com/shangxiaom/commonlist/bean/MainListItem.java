package com.shangxiaom.commonlist.bean;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.bean
 * @ 日        期:2017/9/11 11:52
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class MainListItem {
    private String mTitle;
    private String mContent;
    private int mImageId;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }
}
