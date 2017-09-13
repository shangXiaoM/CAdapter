package com.shangxiaom.commonlist.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.shangxiaom.commonlist.MyApplication;

import java.io.File;
import java.io.IOException;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.utils
 * @ 日        期:2017/9/12 18:15
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class FileUtil {

    private static final String CACHE_DIR = "cache";
    private static String rootPath = null;

    /**
     * 获取APP SD缓存信息路径
     */
    public static String getSDAppCachePath() {
        String path = MyApplication.getContext().getExternalCacheDir().getAbsolutePath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String getImagesCacheDir() {
        if (StringUtil.isEmpty(rootPath)) {
            rootPath = getSDAppCachePath() + File.separator + CACHE_DIR;
        }
        return rootPath;
    }

    public static String getImagePath(Uri imageFileUri) {
        if (null == imageFileUri) {
            return null;
        }
        String scheme = imageFileUri.getScheme();
        String data = null;
        if (null == scheme) {
            data = imageFileUri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = imageFileUri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = MyApplication.getContext().getContentResolver().query(imageFileUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static Bitmap getImage(Uri imageFileUri, Context context) {
        if (null == imageFileUri) {
            return null;
        }
        Bitmap image = null;
        try {
            image = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageFileUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
