package com.shangxiaom.commonlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.shangxiaom.commonlist.bean.MainListItem;
import com.shangxiaom.commonlist.R;

import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.adapter
 * @ 日        期:2017/9/11 11:40
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class MainAdapter<T> extends BaseCommonAdapter<T> {

    public MainAdapter(LayoutInflater layoutInflater, List<T> datas, Context context, int itemLayoutId) {
        super(layoutInflater, datas, context, itemLayoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, T item) {
        MainListItem listItem = (MainListItem) item;
        holder.setText(R.id.list_item_title, listItem.getTitle());
        holder.setText(R.id.list_item_content, listItem.getContent());
        holder.setBackgroundRes(R.id.list_item_image, listItem.getImageId());
    }
}
