package com.shangxiaom.commonlist.mvp.activity.view.iml;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.mvp.activity.interfaces.IBaseActivity;
import com.shangxiaom.commonlist.mvp.activity.presenter.SocketPresenter;
import com.shangxiaom.commonlist.mvp.activity.view.ISocketActivityView;
import com.shangxiaom.commonlist.utils.ProgressUtil;
import com.shangxiaom.commonlist.utils.StringUtil;
import com.shangxiaom.commonlist.utils.ToastUtil;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.view.iml
 * @ 日        期:2017/11/3 14:30
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class SocketActivityView extends IBaseActivity<ISocketActivityView, SocketPresenter> implements ISocketActivityView {

    private Button mConnectBtn;
    private Button mSendBtn;
    private EditText mSendContentET;
    private TextView mDisplayTV;
    private TextView mReceivedTV;
    private TextInputLayout mTextInputLayout;

    private boolean isConnected;

    @Override
    public SocketPresenter getPresenter() {
        return new SocketPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.socket_communication;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        mConnectBtn = fvb(R.id.socket_connect_btn);
        mSendBtn = fvb(R.id.socket_send_btn);
        mSendContentET = fvb(R.id.socket_send_content_ev);
        mDisplayTV = fvb(R.id.socket_display_tv);
        mDisplayTV.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTextInputLayout = fvb(R.id.socket_address_til);
        mReceivedTV = fvb(R.id.socket_received_msg);
        mReceivedTV.setMovementMethod(ScrollingMovementMethod.getInstance());
        initListener();
    }

    private void initListener() {
        mConnectBtn.setOnClickListener(mOnClickListener);
        mSendBtn.setOnClickListener(mOnClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            switch (v.getId()) {
                case R.id.socket_connect_btn:
                    inputMethodManager.hideSoftInputFromWindow(mTextInputLayout.getWindowToken(), 0);
                    if (isConnected) {
                        mPresenter.disConnect();
                    } else {
                        String url = mTextInputLayout.getEditText().getText().toString();
                        if (!StringUtil.isEmpty(url)) {
                            mPresenter.connect(url);
                        }
                    }
                    break;
                case R.id.socket_send_btn:
                    inputMethodManager.hideSoftInputFromWindow(mSendContentET.getWindowToken(), 0);
                    String msg = mSendContentET.getText().toString();
                    if (!StringUtil.isEmpty(msg)) {
                        mPresenter.send(msg);
                    }
                    break;
            }
        }
    };

    /**
     * 显示进度框
     *
     * @param resId 资源ID
     */
    @Override
    public void ingShow(int resId) {
        ProgressUtil.show(this, getString(resId), null, false, null);
    }

    /**
     * 关闭进度框
     */
    @Override
    public void ingDismiss() {
        ProgressUtil.dismiss();
    }

    /**
     * 显示toast信息
     *
     * @param resId 资源id
     */
    @Override
    public void toastShow(int resId) {
        ToastUtil.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 更新连接状态
     *
     * @param isConnected 是否已连接
     */
    @Override
    public void updateSocketState(boolean isConnected) {
        this.isConnected = isConnected;
        if (isConnected) {
            mConnectBtn.setText(R.string.socket_connected);
            mConnectBtn.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        } else {
            mConnectBtn.setText(R.string.socket_not_connect);
            mConnectBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    /**
     * 更新显示的已发送信息
     *
     * @param append 追加的信息
     */
    @Override
    public void updateSendText(String append) {
        if (StringUtil.isEmpty(append)) {
            mDisplayTV.setText("");
            mReceivedTV.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        String org = mDisplayTV.getText().toString();
        if (!StringUtil.isEmpty(org)) {
            sb.append(org);
        }
        sb.append(append).append("\r\n");
        mDisplayTV.setText(sb.toString());
        int offset = mDisplayTV.getLineCount() * mDisplayTV.getLineHeight();
        if (offset > mDisplayTV.getHeight()) {
            mDisplayTV.scrollTo(0, offset - mDisplayTV.getHeight());
        }
    }

    /**
     * @param msg
     */
    @Override
    public void updateReceivedText(String msg) {
        StringBuilder sb = new StringBuilder();
        String org = mReceivedTV.getText().toString();
        if (!StringUtil.isEmpty(org)) {
            sb.append(org);
        }
        sb.append(msg).append("\r\n");
        mReceivedTV.setText(sb.toString());
        int offset = mReceivedTV.getLineCount() * mReceivedTV.getLineHeight();
        if (offset > mReceivedTV.getHeight()) {
            mReceivedTV.scrollTo(0, offset - mReceivedTV.getHeight());
        }
    }
}
