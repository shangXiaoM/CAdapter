package com.shangxiaom.commonlist.mvp.fragment.view.imp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.adapter.HomeListAdapter;
import com.shangxiaom.commonlist.bean.HomeListItem;
import com.shangxiaom.commonlist.mvp.fragment.IBaseView;
import com.shangxiaom.commonlist.mvp.fragment.presenter.IMainPresenter;
import com.shangxiaom.commonlist.mvp.fragment.view.IMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.fragment.view.imp
 * @ 日        期:2017/9/12 14:16
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class MainView implements IBaseView, IMainView {
    private Context mContext;
    private IMainPresenter mIMainPresenter;
    private HomeListAdapter<HomeListItem> mListItemHomeListAdapter;

    private View mRootView;
    private ListView mListView;

    private Toolbar mToolbar;
    private List<HomeListItem> mListData = new ArrayList<>();

    /**
     * 是否显示进度
     *
     * @param visible
     */
    @Override
    public void onProgressShow(boolean visible) {

    }

    /**
     * toast信息
     *
     * @param resId
     */
    @Override
    public void toast(int resId) {

    }

    /**
     * 下载图片
     */
    @Override
    public void downloadImages() {

    }

    /**
     * 上传图片
     */
    @Override
    public void uploadImages() {

    }

    @Override
    public Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(IBaseView.LAYOUT_ID, R.layout.home_view);
        return bundle;
    }

    /**
     * 绑定控件
     *
     * @param view
     */
    @Override
    public void bindView(View view) {
        this.mRootView = view;
        this.mContext = view.getContext();
        mListItemHomeListAdapter = new HomeListAdapter<>(LayoutInflater.from(this.mContext), mListData, this.mContext, R.layout.list_item_main);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

    }
}
