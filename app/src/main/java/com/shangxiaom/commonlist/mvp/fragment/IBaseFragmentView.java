package com.shangxiaom.commonlist.mvp.fragment;

import android.os.Bundle;
import android.view.View;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.fragment
 * @ 日        期:2017/9/12 11:45
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public interface IBaseFragmentView {
    String LAYOUT_ID = "layout_id";

    Bundle getBundle();

    /**
     * 绑定控件
     *
     * @param view
     */
    void bindView(View view);

    void onHiddenChanged(boolean hidden);
}
