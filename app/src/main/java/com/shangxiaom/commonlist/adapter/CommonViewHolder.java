package com.shangxiaom.commonlist.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * **************************************************
 *
 * @ 项目名称:rkhy.com.ecg.adatper
 * @ 日        期:2017/9/11 10:02
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class CommonViewHolder {
    private SparseArray<View> mWidgetViews;
    private View mConvertView;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mWidgetViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 根据convertView，拿到一个viewHolder对象
     *
     * @param convertView
     * @param context
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder get(View convertView, Context context, ViewGroup parent, int layoutId, int position) {
        if (null == convertView) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId) {
        View view = mWidgetViews.get(viewId);
        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mWidgetViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public void setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public void setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public void setBackgroundRes(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(drawableId);
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public void setImageBitmap(int viewId, Bitmap bm) {
        Drawable drawable = new BitmapDrawable(bm);
        setImageBackground(viewId, drawable);
    }

    public void setImageBackground(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setBackground(drawable);
    }

    public View getConvertView() {
        return mConvertView;
    }
}
