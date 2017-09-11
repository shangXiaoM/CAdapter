package com.shangxiaom.bean;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.bean
 * @ 日        期:2017/9/11 11:52
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class MainListItem {
    private String mTitle;
    private String mContent;
    private String mCheckStr;
    private boolean mChecked;
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

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    public String getCheckStr() {
        return mCheckStr;
    }

    public void setCheckStr(String checkStr) {
        mCheckStr = checkStr;
    }
}
