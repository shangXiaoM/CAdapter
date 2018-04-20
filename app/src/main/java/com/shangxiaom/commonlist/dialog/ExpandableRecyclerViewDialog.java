/*
 * Copyright (c) 2018 - 4 - 20  11: 0:34
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.adapter.DividerItemDecoration;
import com.shangxiaom.commonlist.adapter.ExpandableRecyclerViewAdapter;
import com.shangxiaom.commonlist.bean.DataListTree;
import com.shangxiaom.commonlist.utils.WindowsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************
 * ******************_ooOoo_
 * *****************o8888888o
 * *****************88\" . \"88
 * *****************(| -_-  |)
 * *****************O\\  =  /O
 * **************____/`---'\\____
 * ************.'  \\\\|     |//  `.
 * ~~~~~~~~~~~/  \\\\|||  :  |||//  \\
 * ~~~~~~~~~~~/  _||||| -:- |||||-  \\
 * ~~~~~~~~~~~|   | \\\\\\  -  /// |   |
 * ~~~~~~~~~~~| \\_|  ''\\---/''  |   |
 * ~~~~~~~~~~~\\  .-\\__  `-`  ___/-. /
 * ~~~~~~~~~___`. .'  /--.--\\  `. . __
 * ~~~~~~.\"\" '<  `.___\\_<|>_/___.'  >'\"\".
 * ~~~~~| | :  `- \\`.;`\\ _ /`;.`/ - ` : | |
 * ~~~~~\\  \\ `-.   \\_ __\\ /__ _/   .-` /  /
 * ======`-.____`-.___\\_____/___.-`____.-'======
 * ******************`=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
 * ***************佛祖保佑*********永无BUG
 *
 * @ 项目名称: rkhy.com.ecg.dialog
 * @ 日        期:2018/4/19 11:26
 * @ 作        者:shangming
 * **************************************************
 */
public class ExpandableRecyclerViewDialog extends Dialog {
    private Context mContext;

    private RecyclerView mRecyclerView;

    private List<DataListTree<String, String>> mDataListTrees;

    public ExpandableRecyclerViewDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mDataListTrees = new ArrayList<>();
        List<String> A = new ArrayList<>();
        A.add("A1");
        A.add("A2");
        List<String> B = new ArrayList<>();
        B.add("B1");
        List<String> C = new ArrayList<>();
        C.add("C1");
        C.add("C2");
        C.add("C3");
        List<String> D = new ArrayList<>();
        D.add("D1");
        D.add("D2");
        D.add("D3");
        D.add("D4");
        D.add("D5");
        D.add("D6");
        D.add("D7");
        D.add("D8");
        D.add("D9");
        D.add("D10");
        D.add("D11");
        D.add("D12");
        D.add("D13");
        D.add("D14");
        D.add("D15");
        D.add("D16");
        D.add("D17");
        D.add("D18");
        mDataListTrees.add(new DataListTree<>("A", A));
        mDataListTrees.add(new DataListTree<>("B", B));
        mDataListTrees.add(new DataListTree<>("C", C));
        mDataListTrees.add(new DataListTree<>("D", D));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.test_expandable_recyclerview_dialog, null, false);
        setContentView(view);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.dimAmount = 0.5f;// 黑暗度
            lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            lp.width = WindowsUtils.getInstance().getWindowWidth() * 3 / 10;
            lp.height = WindowsUtils.getInstance().getWindowHeight() * 11 / 12;
            window.setAttributes(lp);
        }
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.diagnose_term_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        ExpandableRecyclerViewAdapter adapter = new ExpandableRecyclerViewAdapter(mContext);
        adapter.setData(mDataListTrees);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(adapter);
    }
}
