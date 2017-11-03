package com.shangxiaom.commonlist.mvp.activity.interfaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shangxiaom.commonlist.utils.ClickUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp
 * @ 日        期:2017/9/13 16:08
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public abstract class IBaseActivity<K extends IViewHandler, T extends IViewPresenter<K>> extends AppCompatActivity implements IBaseActivityView<K, T>, IViewHandler {
    /**
     * 根视图
     */
    protected View mRootView;
    protected Context mContext;

    /**
     * 动态权限申请
     */
    protected RxPermissions mRxPermissions;
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mRxPermissions = new RxPermissions(this);
        mRxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            init(savedInstanceState);
                        } else {
                            allFinish();
                        }
                    }
                });
    }

    private void init(Bundle savedInstanceState) {
        //初始化RootView
        createView(null, null, savedInstanceState);
        //设置布局
        setContentView(mRootView);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attach((K) this);
            mPresenter.mContext = this;
        }
        bindView(savedInstanceState);
        getIntentData(getIntent());
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(this).inflate(getLayoutId(), null);

        return mRootView;
    }

    /**
     * 获取传值
     *
     * @param intent
     */
    protected void getIntentData(Intent intent) {
        // 避免从桌面启动程序后，会重新实例化入口类的activity
        if (!this.isTaskRoot()) {
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) { // 从桌面启动的应用
                    finish();
                    return;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ClickUtil.isFinishAPP(this)) {
                allFinish();
            }
        }
        return true;
    }

    private void allFinish() {
        finish();
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public View getView() {
        return mRootView;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T fvb(int resId) {
        return (T) mRootView.findViewById(resId);
    }
}
