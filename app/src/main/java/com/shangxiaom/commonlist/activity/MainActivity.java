package com.shangxiaom.commonlist.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.adapter.MainAdapter;
import com.shangxiaom.commonlist.bean.MainListItem;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private static final int IMAGE_TO_UPLOAD = 1;

    private MainAdapter<MainListItem> mListItemMainAdapter;
    private List<MainListItem> mListData = new ArrayList<>();
    private RxPermissions mRxPermissions;

    private ListView mListView;
    private List<Uri> mSelectedImages;

    /**
     * 设置布局文件
     *
     * @return
     */
    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化控件
     */
    @Override
    void bindView() {
        mListView = fvb(R.id.listview_main);
        mToolbar = fvb(R.id.toolbar_main_include);
    }

    @Override
    protected void initData() {
        super.initData();
        mListItemMainAdapter = new MainAdapter<>(this.getLayoutInflater(), mListData, this, R.layout.list_item_main);
        mListView.setAdapter(mListItemMainAdapter);
        mRxPermissions = new RxPermissions(this);
        mToolbar.setTitle("测试Title");
    }

    /**
     * 初始化相关监听事件
     */
    @Override
    void initListener() {
        setSupportActionBar(mToolbar);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "点击" + i, Toast.LENGTH_SHORT).show();
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.upload_menu_main:
                        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                .subscribe(new Consumer<Boolean>() {
                                    @Override
                                    public void accept(Boolean aBoolean) throws Exception {
                                        if (aBoolean) {
                                            chooseImages();
                                        } else {
                                            Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void chooseImages() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                .theme(R.style.Matisse_Zhihu)
                .countable(false)
                .maxSelectable(10)
                .imageEngine(new GlideEngine())
                .forResult(IMAGE_TO_UPLOAD);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_TO_UPLOAD:
                    mSelectedImages = Matisse.obtainResult(data);
                    Toast.makeText(MainActivity.this, "上传选中的图片", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
