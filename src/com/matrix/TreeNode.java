package com.matrix;

/**
 * @author boyuangong created on 8/31/19 at 10:56
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void printTreeNodePreorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        printTreeNodePreorder(treeNode.left);
        printTreeNodePreorder(treeNode.right);
    }

    public static void printTreeNodeInorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        printTreeNodeInorder(treeNode.left);
        System.out.println(treeNode.val);
        printTreeNodeInorder(treeNode.right);
    }

    public static void printTreeNodePostorder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        printTreeNodePostorder(treeNode.left);
        printTreeNodePostorder(treeNode.right);
        System.out.println(treeNode.val);
    }
}
