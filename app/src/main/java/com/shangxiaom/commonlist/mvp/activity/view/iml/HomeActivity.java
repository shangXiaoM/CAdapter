package com.shangxiaom.commonlist.mvp.activity.view.iml;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.adapter.HomeListAdapter;
import com.shangxiaom.commonlist.bean.HomeListItem;
import com.shangxiaom.commonlist.mvp.activity.interfaces.IBaseActivity;
import com.shangxiaom.commonlist.mvp.activity.presenter.HomePresenter;
import com.shangxiaom.commonlist.mvp.activity.view.IHomeActivityView;
import com.shangxiaom.commonlist.utils.FileUtil;
import com.shangxiaom.commonlist.utils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.view.iml
 * @ 日        期:2017/9/13 17:41
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class HomeActivity extends IBaseActivity<IHomeActivityView, HomePresenter> implements IHomeActivityView {

    private static final int IMAGE_TO_UPLOAD = 1;

    private Toolbar mToolbar;
    private ListView mListView;
    private SmartRefreshLayout mSmartRefreshLayout;

    private List<Uri> mSelectedImages;
    private HomeListAdapter<HomeListItem> mHomeListAdapter;
    private List<HomeListItem> mListData = new ArrayList<>();

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {
        ToastUtil.makeText(mContext, "login success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        mToolbar = fvb(R.id.toolbar_home_include);
        mListView = fvb(R.id.listview_home);
        mSmartRefreshLayout = fvb(R.id.refresh_layout_home);
        mToolbar.setTitle("Test mvp");
        setSupportActionBar(mToolbar);
        initListener();
    }


    private void initListener() {
        mHomeListAdapter = new HomeListAdapter<>(this.getLayoutInflater(), mListData, this, R.layout.list_item_main);
        mListView.setAdapter(mHomeListAdapter);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Matisse.from(HomeActivity.this)
                        .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                        .theme(R.style.Matisse_Zhihu)
                        .countable(false)
                        .maxSelectable(10)
                        .imageEngine(new GlideEngine())
                        .forResult(IMAGE_TO_UPLOAD);
                return false;
            }
        });
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.login();
                mSmartRefreshLayout.finishRefresh(2000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_TO_UPLOAD:
                    mSelectedImages = Matisse.obtainResult(data);
                    for (int i = 0; i < mSelectedImages.size(); i++) {
                        Bitmap bitmap = FileUtil.getImage(mSelectedImages.get(i), HomeActivity.this);
                        HomeListItem homeListItem = new HomeListItem();
                        homeListItem.setTitle("Title" + (i + 1));
                        homeListItem.setContent("Title" + (i + 1) + "Test!");
                        homeListItem.setBitmap(bitmap);
                        mListData.add(homeListItem);
                    }
                    mHomeListAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
