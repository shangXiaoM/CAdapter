package com.shangxiaom.commonlist;

import com.shangxiaom.commonlist.algorithm.sort.Sort;
import com.shangxiaom.commonlist.design.DProxy;
import com.shangxiaom.commonlist.design.IBuy;
import com.shangxiaom.commonlist.design.User;
import com.shangxiaom.commonlist.design.UserProxy;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void buyHouse() {
        IBuy user = new User();
        user.buyHouse(10000);

        IBuy userProxy = new UserProxy(user);
        userProxy.buyHouse(10000);

        IBuy dProxy = (IBuy) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), new DProxy(user));
        dProxy.buyHouse(10000);
    }

    @Test
    public void sort() {
        int[] data = new int[100];
        Random rand = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(100);
        }
        System.out.println("排序之前:" + Arrays.toString(data));

        int[] temp1 = data;
        long time0 = System.currentTimeMillis();
        Sort.bubbleSort(temp1);
        long time1 = System.currentTimeMillis();
        long bubbletime = time1 - time0;
        System.out.println("bubbleSort冒泡：" + Arrays.toString(temp1));

        temp1 = data;
        time0 = System.currentTimeMillis();
        Sort.bubbleSortPos(temp1);
        time1 = System.currentTimeMillis();
        long bubblePostime = time1 - time0;
        System.out.println("bubbleSortPos冒泡：" + Arrays.toString(temp1));

        temp1 = data;
        time0 = System.currentTimeMillis();
        Sort.bubbleSortFlag(temp1);
        time1 = System.currentTimeMillis();
        long bubbleFlagtime = time1 - time0;
        System.out.println("bubbleSortFlag冒泡：" + Arrays.toString(temp1));

        time0 = System.currentTimeMillis();
        Sort.bubbleSortDouble(data);
        time1 = System.currentTimeMillis();
        long bubbleDoubletime = time1 - time0;
        System.out.println("bubbleSortDouble冒泡：" + Arrays.toString(data));

        System.out.println("冒泡时间：bubble:" + bubbletime + ",bubblePos:" + bubblePostime + ", bubbleFlag:" + bubbleFlagtime + ",bubbleDouble:" + bubbleDoubletime);

    }
}