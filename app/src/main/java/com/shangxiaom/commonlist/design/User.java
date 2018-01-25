/*
 * Copyright (c) 2018 - 1 - 25  10: 31:27
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.design;

public class User implements IBuy {
    @Override
    public void buyHouse(long money) {
        System.out.println("买房子，花了"+ money+"元！");
    }
}
