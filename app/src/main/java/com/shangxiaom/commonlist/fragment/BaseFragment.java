package com.shangxiaom.commonlist.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
}
