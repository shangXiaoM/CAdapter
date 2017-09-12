package com.shangxiaom.commonlist.activity;

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
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final int IMAGE_TO_UPLOAD = 1;

    private MainAdapter<MainListItem> mListItemMainAdapter;
    private List<MainListItem> mListData = new ArrayList<>();

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
                        chooseImages();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void chooseImages() {
        Matisse.from(MainActivity.this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                .theme(R.style.Matisse_Zhihu)
                .countable(false)
                .maxSelectable(10)
                .imageEngine(new PicassoEngine())
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
