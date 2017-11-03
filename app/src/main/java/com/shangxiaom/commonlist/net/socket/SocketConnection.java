package com.shangxiaom.commonlist.net.socket;

import com.shangxiaom.commonlist.mvp.activity.interfaces.IViewPresenter;
import com.shangxiaom.commonlist.mvp.activity.presenter.SocketPresenter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * **************************************************
 *
 * @ 项目名称:com.rencarehealth.mirhythm.connection
 * @ 日        期:2017/10/30 17:31
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class SocketConnection {
    private static SocketConnection mInstance;
    private Socket mSocket;
    // 输入流
    private InputStream is;
    // 输出流
    private OutputStream os;

    // 线程池
    // 为了方便展示,此处直接采用线程池进行线程管理,而没有一个个开线程
    private ExecutorService mThreadPool = Executors.newFixedThreadPool(2);

    private boolean isConnected;

    private BlockingQueue<byte[]> mData = new LinkedBlockingQueue<>();

    private static SocketPresenter mSocketPresenter;

    public static SocketConnection getInstance(SocketPresenter socketPresenter) {
        if (null == mInstance) {
            mInstance = new SocketConnection();
        }
        mSocketPresenter = socketPresenter;
        return mInstance;
    }

    public boolean connect(String url, int port) throws IOException {
        if (null != mSocket && mSocket.isConnected()) {
            mSocket.close();
            mSocket = null;
        }
        mSocket = new Socket(url, port);
        isConnected = mSocket.isConnected();
        if (isConnected) {
            os = mSocket.getOutputStream();
            is = mSocket.getInputStream();
            mThreadPool.execute(sendRunnable);
            mThreadPool.execute(receiveRunnable);
        }
        return isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void send(byte[] data) {
        synchronized (mData) {
            mData.offer(data);
        }
    }

    public boolean stop() throws IOException {
        isConnected = false;
        // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
        if (null != os) {
            os.close();
            os = null;
        }

        if (null != is) {
            is.close();
            is = null;
        }

        // 关闭整个Socket连接
        if (null != mSocket) {
            mSocket.close();
        }
        return mSocket.isConnected();
    }

    private Runnable sendRunnable = new Runnable() {
        @Override
        public void run() {
            while (isConnected && null != os) {
                try {
                    byte[] temp = mData.poll();
                    if (null != temp) {
                        os.write(temp);
                        os.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    byte[] read = new byte[50];
    private Runnable receiveRunnable = new Runnable() {
        @Override
        public void run() {
            while (isConnected && null != is) {
                try {
                    if (is.read(read) > 0) {
                        mSocketPresenter.receive(read);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
