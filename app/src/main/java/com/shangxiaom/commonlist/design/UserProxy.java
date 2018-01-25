/*
 * Copyright (c) 2018 - 1 - 25  10: 38:12
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.design;

public class UserProxy implements IBuy {
    private IBuy mIBuy; // 真实代理的对象

    public UserProxy(IBuy IBuy) {
        mIBuy = IBuy;
    }

    @Override
    public void buyHouse(long money) {
        long fee = (long) (money * 0.01); // 代理对象的一些附加福利
        System.out.println("静态代理中介，坑了" + fee + "中介费！");

        mIBuy.buyHouse(money + fee); // 真实对象执行
    }
}
