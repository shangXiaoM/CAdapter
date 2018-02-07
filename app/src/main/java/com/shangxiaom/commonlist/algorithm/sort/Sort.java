/*
 * Copyright (c) 2018 - 2 - 7  9: 32:52
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.algorithm.sort;

/**
 * **************************************************
 *
 * @ 项目名称: com.shangxiaom.commonlist.algorithm.sort
 * @ 日        期:2018/2/7 9:32
 * @ 作        者:shangming
 * **************************************************
 */
public class Sort {

    /**
     * 直接插入排序算法实现
     * 从小到大，对整型数组进行排序
     *
     * @param data 待排序的数组
     */
    public static void directInsertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int x = data[i];
            int j;
            for (j = i; j > 0; j--) {
                if (data[j - 1] > x) { // 前一个值较大
                    data[j] = data[j--]; // 将较大的值往后移动
                }
            }
            data[j] = x;
        }
    }

    /**
     * 冒泡排序
     * 小->大
     *
     * @param data 待排序的数组
     */
    public static void bubbleSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序改进
     * 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。
     * 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。
     *
     * @param data 待排序的数组
     */
    public static void bubbleSortPos(int[] data) {
        int len = data.length;
        int i = len - 1; // 初始化最后一个遍历的位置
        while (i > 0) {
            int pos = 0; // 用于记录最后一次交换的位置
            for (int j = 0; j < i; j++) {   // 循环遍历只执行到上一次最后交换的位置
                if (data[j] > data[j + 1]) {
                    pos = j;
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
            i = pos;
        }
    }

    /**
     * 冒泡排序改进
     * 设置一个标识位，如果这一趟冒泡过程中，没有出现过交换，这说明数组中的数据已经是有序的了，可以直接退出排序过程
     *
     * @param data 待排序的数组
     */
    public static void bubbleSortFlag(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < len - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 冒泡排序改进
     * 双向冒泡排序：正向遍历一遍，冒泡出一个最大值，然后再反向遍历， 冒泡出一个最小值，循环操作，直到最小值位置=最大值位置
     *
     * @param data 待排序的数组
     */
    public static void bubbleSortDouble(int[] data) {
        int high = data.length - 1;
        int low = 0;
        int i;
        int temp;
        while (low < high) {
            for (i = low; i < high; i++) { // 正向遍历，找出极大值
                if (data[i] > data[i + 1]) {
                    temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                }
            }
            high--; // 最高位置下移一位
            for (i = high; i > low; i--) { // 方向遍历，找出极小值
                if (data[i] < data[i - 1]) {
                    temp = data[i];
                    data[i] = data[i - 1];
                    data[i - 1] = temp;
                }
            }
            low++; // 最低位置上移一位
        }
    }
}
