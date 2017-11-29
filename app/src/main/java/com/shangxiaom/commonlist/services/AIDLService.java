package com.shangxiaom.commonlist.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.shangxiaom.commonlist.Iaidl;

/**
 * **************************************************
 *
 * @ 项目名称:com.shangxiaom.commonlist.services
 * @ 日        期:2017/9/25 14:43
 * @ 作        者:Administrator
 * @ Copyright (c) 2017, RencareHealth All Rights Reserved.
 * **************************************************
 */
public class AIDLService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    Iaidl.Stub mBinder = new Iaidl.Stub() {
        @Override
        public String getTestStr(String extra) throws RemoteException {
            return "这是测试RPC代码！" + extra;
        }

        @Override
        public void testAidl() throws RemoteException {

        }

        @Override
        public double getTestNum() throws RemoteException {
            return 1;
        }
    };
}
