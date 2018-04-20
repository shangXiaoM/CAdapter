/*
 * Copyright (c) 2018 - 4 - 20  11: 1:1
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.bean;

import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称: rkhy.com.ecg.view.ExpandableRecyclerView
 * @ 日        期:2018/4/18 17:02
 * @ 作        者:shangming
 * **************************************************
 */
public class DataListTree<K,V> {
    private K mGroupItem;
    private List<V> mSubItem;

    public DataListTree(K groupItem, List<V> subItem) {
        mGroupItem = groupItem;
        mSubItem = subItem;
    }

    public K getGroupItem() {
        return mGroupItem;
    }

    public List<V> getSubItem() {
        return mSubItem;
    }
}
