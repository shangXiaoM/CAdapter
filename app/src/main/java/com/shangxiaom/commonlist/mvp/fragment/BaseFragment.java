package com.shangxiaom.commonlist.mvp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.fragment
 * @ 日        期:2017/9/12 11:44
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
@SuppressLint("ValidFragment")
public class BaseFragment extends Fragment {

    IBaseView mIBaseView;

    public BaseFragment(IBaseView iBaseView) {
        this.mIBaseView = iBaseView;
        if (null != iBaseView) {
            Bundle bundle = iBaseView.getBundle();
            if (null != bundle) {
                setArguments(bundle);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (null != bundle) {
            int layoutId = bundle.getInt(IBaseView.LAYOUT_ID);
            View view = inflater.inflate(layoutId, container);
            if (null != this.mIBaseView) {
                this.mIBaseView.bindView(view);
            }
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (null != mIBaseView) {
            mIBaseView.onHiddenChanged(hidden);
        }
        super.onHiddenChanged(hidden);
    }
}
