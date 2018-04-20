/*
 * Copyright (c) 2018 - 4 - 20  11: 1:1
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.bean;

/**
 * **************************************************
 *
 * @ 项目名称: rkhy.com.ecg.view.ExpandableRecyclerView
 * @ 日        期:2018/4/18 17:12
 * @ 作        者:shangming
 * **************************************************
 */
public class ItemStatus {
    public static final int VIEW_TYPE_GROUP_ITEM = 0;
    public static final int VIEW_TYPE_SUB_ITEM = 1;

    private int mViewType; // item类型：group or sub
    private int mGroupItemIndex; // 一级列表索引
    private int mSubItemIndex = -1; // 二级列表索引

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }

    public int getGroupItemIndex() {
        return mGroupItemIndex;
    }

    public void setGroupItemIndex(int groupItemIndex) {
        mGroupItemIndex = groupItemIndex;
    }

    public int getSubItemIndex() {
        return mSubItemIndex;
    }

    public void setSubItemIndex(int subItemIndex) {
        mSubItemIndex = subItemIndex;
    }
}
