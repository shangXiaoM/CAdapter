package com.shangxiaom.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    public CommonViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setCheckBoxText(int viewId, String text, boolean checked) {
        CheckBox view = getView(viewId);
        view.setText(text);
        view.setChecked(checked);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public CommonViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param bm
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
