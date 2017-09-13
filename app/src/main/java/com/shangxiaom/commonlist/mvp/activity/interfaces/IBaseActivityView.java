package com.shangxiaom.commonlist.mvp.activity.interfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp
 * @ 日        期:2017/9/13 16:02
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * 抽取一些方法，用于在BaseActivity和BaseFragment里去实现
 * **************************************************
 */
public interface IBaseActivityView<K extends IViewHandler, T extends IViewPresenter<K>> {

    T getPresenter();   //Presenter对象

    int getLayoutId();//布局id

    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);   //创建View

    void bindView(Bundle savedInstanceState);   //onCreate（）执行

    View getView(); //获取根View
}
