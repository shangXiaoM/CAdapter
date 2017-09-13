package com.shangxiaom.commonlist.bean;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.bean
 * @ 日        期:2017/9/11 11:52
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class HomeListItem {
    private String mTitle;
    private String mContent;
    private Bitmap mBitmap;
    private URL mURL;

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

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public URL getURL() {
        return mURL;
    }

    public void setURL(URL URL) {
        mURL = URL;
    }
}
