package com.shangxiaom.commonlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称:rkhy.com.ecg.adatper
 * @ 日        期:2017/9/11 10:35
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public abstract class BaseCommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mLayoutInflater;
    protected List<T> mDatas;
    protected Context mContext;
    protected int mItemLayoutId;

    public BaseCommonAdapter(LayoutInflater layoutInflater, List<T> datas, Context context, int itemLayoutId) {
        mLayoutInflater = layoutInflater.from(context);
        mDatas = datas;
        mContext = context;
        mItemLayoutId = itemLayoutId;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if (null != mDatas) {
            return mDatas.size();
        }
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the commonlist.adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        if (null != mDatas) {
            return mDatas.get(position);
        }
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the commonlist.adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CommonViewHolder holder = getHolder(position, convertView, parent);
        if (0 < mDatas.size()) {
            convert(holder, mDatas.get(position));
        }
        return holder.getConvertView();
    }

    private CommonViewHolder getHolder(int position, View convertView, ViewGroup parent) {
        return CommonViewHolder.get(convertView, mContext, parent, mItemLayoutId, position);
    }

    public abstract void convert(CommonViewHolder holder, T item);
}
