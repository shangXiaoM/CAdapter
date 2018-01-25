/*
 * Copyright (c) 2018 - 1 - 25  10: 56:13
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DProxy implements InvocationHandler {
    private IBuy mBuy;

    public DProxy(IBuy buy) {
        mBuy = buy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("buyHouse")) {
            long money = (long) args[0];
            long fee = (long) (money * 0.02);
            System.out.println("动态代理中介，坑了" + fee + "中介费！");
            long newMoney = money + fee;
            args[0] = newMoney;
            return method.invoke(mBuy, args);
        }
        return null;
    }
}
