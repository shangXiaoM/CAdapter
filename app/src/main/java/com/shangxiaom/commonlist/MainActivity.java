package com.shangxiaom.commonlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.shangxiaom.adapter.MainAdapter;
import com.shangxiaom.bean.MainListItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainAdapter<MainListItem> mListItemMainAdapter;
    private List<MainListItem> mListData;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview_main);
        initData();
        mListItemMainAdapter = new MainAdapter<>(this.getLayoutInflater(), mListData, this, R.layout.list_item_main);
        mListView.setAdapter(mListItemMainAdapter);
    }

    private void initData() {
        mListData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MainListItem mainListItem = new MainListItem();
            mainListItem.setTitle("标题" + (i + 1));
            mainListItem.setContent("标题" + (i + 1) + "的测试内容");
            mainListItem.setCheckStr("选中" + (i + 1));
            if ((i % 2) == 0) {
                mainListItem.setChecked(true);
                mainListItem.setImageId(R.mipmap.ic_cloud_download_black);
            } else {
                mainListItem.setChecked(false);
                mainListItem.setImageId(R.mipmap.ic_launcher);
            }
            mListData.add(mainListItem);
        }
    }
}
