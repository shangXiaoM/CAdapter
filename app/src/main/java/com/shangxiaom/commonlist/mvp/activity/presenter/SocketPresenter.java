package com.shangxiaom.commonlist.mvp.activity.presenter;

import com.shangxiaom.commonlist.R;
import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewPresenter;
import com.shangxiaom.commonlist.mvp.activity.view.ISocketActivityView;
import com.shangxiaom.commonlist.net.socket.SocketConnection;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.mvp.activity.presenter
 * @ 日        期:2017/11/3 14:35
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class SocketPresenter extends IViewPresenter<ISocketActivityView> {

    private SocketConnection mSocketConnection;

    public void connect(final String url) {
        mViewHandler.ingShow(R.string.please_wait);
        Observable
                .create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                        String[] strs = url.split(":");
                        mSocketConnection = SocketConnection.getInstance(SocketPresenter.this);
                        boolean isConnected = mSocketConnection.connect(strs[0], Integer.parseInt(strs[1]));
                        e.onNext(isConnected);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mViewHandler.ingDismiss();
                        if (aBoolean) {
                            mViewHandler.toastShow(R.string.socket_connected);
                        } else {
                            mViewHandler.toastShow(R.string.socket_not_connect);
                        }
                        mViewHandler.updateSocketState(aBoolean);
                    }
                });
    }

    public void send(final String msg) {
        if (mSocketConnection.isConnected()) {
            mViewHandler.updateSendText(msg);
            mSocketConnection.send(msg.getBytes());
        }
    }

    public void receive(byte[] data) {
        Observable
                .just(data)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<byte[]>() {
                    @Override
                    public void accept(byte[] bytes) throws Exception {
                        if (null != mViewHandler) {
                            mViewHandler.updateReceivedText(new String(bytes));
                        }
                    }
                });
    }

    public void disConnect() {
        mViewHandler.ingShow(R.string.please_wait);
        Observable
                .create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                        if (mSocketConnection.isConnected()) {
                            e.onNext(mSocketConnection.stop());
                        } else {
                            e.onNext(false);
                        }

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mViewHandler.ingDismiss();
                        if (!aBoolean) {
                            mViewHandler.toastShow(R.string.socket_dis_connect);
                        }
                        mViewHandler.updateSocketState(aBoolean);
                        mViewHandler.updateSendText(null);
                    }
                });
    }


}
