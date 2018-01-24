/*
 * Copyright (c) 2018 - 1 - 24  10: 33:54
 * com.ShangXiaoM.github
 */

package com.shangxiaom.commonlist.algorithm.tree;

/**
 * 树节点
 */
public class TreeNode {
    private TreeNode mLeft;
    private TreeNode mRight;
    private int mValue;
    private boolean isDeleted;

    public TreeNode(int value) {
        this(null,null,value,false);
    }

    public TreeNode(TreeNode left, TreeNode right, int value, boolean isDeleted) {
        mLeft = left;
        mRight = right;
        mValue = value;
        this.isDeleted = isDeleted;
    }

    public TreeNode getLeft() {
        return mLeft;
    }

    public void setLeft(TreeNode left) {
        mLeft = left;
    }

    public TreeNode getRight() {
        return mRight;
    }

    public void setRight(TreeNode right) {
        mRight = right;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
