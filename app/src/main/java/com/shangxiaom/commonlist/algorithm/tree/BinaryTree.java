/*
 * Copyright (c) 2018 - 1 - 24  10: 33:54
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.algorithm.tree;

import android.util.Log;

public class BinaryTree {
    private static TreeNode mRoot;

    public static void insert(int value) {
        TreeNode newNode = new TreeNode(value);
        if (null == mRoot) {
            mRoot = newNode;
        } else {
            TreeNode currentNode = mRoot;
            TreeNode parentNode;

            while (null != currentNode) {
                parentNode = currentNode; // 记录当前节点为父节点
                if (value > currentNode.getValue()) { //在右边插入
                    currentNode = currentNode.getRight();
                    if (null == currentNode) {
                        parentNode.setRight(newNode);
                    }
                } else { //在左边插入
                    currentNode = currentNode.getLeft();
                    if (null == currentNode) {
                        parentNode.setLeft(newNode);
                    }
                }
            }
        }
    }

    public static TreeNode find(int key) {
        TreeNode currentNode = mRoot;
        if (null != currentNode) {
            while (key != currentNode.getValue()) {
                if (key > currentNode.getValue()) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode = currentNode.getLeft();
                }
                if (null == currentNode) {
                    return null;
                }
            }
            if (currentNode.isDeleted()) {
                return null;
            }
        }
        return currentNode;
    }

    /**
     * 先序遍历
     *
     * @param preNode
     */
    public static void preOrder(TreeNode preNode) {
        if (null != preNode) {
            Log.d("preOrder", preNode.getValue() + "");
            preOrder(preNode.getLeft());
            preOrder(preNode.getRight());
        }
    }

    /**
     * 中序遍历
     *
     * @param midNode
     */
    public static void midOrder(TreeNode midNode) {
        if (null != midNode) {
            midOrder(midNode.getLeft());
            Log.d("midOrder", midNode.getValue() + "");
            midOrder(midNode.getRight());
        }
    }

    /**
     * 中序遍历
     *
     * @param postNode
     */
    public static void postOrder(TreeNode postNode) {
        if (null != postNode) {
            postOrder(postNode.getRight());
            Log.d("postOrder", postNode.getValue() + "");
            postOrder(postNode.getLeft());
        }
    }
}
