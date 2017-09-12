package com.shangxiaom.commonlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist
 * @ 日        期:2017/9/12 9:21
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutId());
        bindView();
        initData();
        initListener();
    }

    /**
     * 设置布局文件
     * @return
     */
    abstract int getLayoutId();

    /**
     * 初始化控件
     */
    abstract void bindView();

    /**
     * 初始化数据，根据具体需要来重写
     */
    protected void initData() {

    }

    /**
     * 初始化相关监听事件
     */
    abstract void initListener();

    @SuppressWarnings("unchecked")
    public <T extends View> T fvb(int resId) {
        return (T) super.findViewById(resId);
    }
}
