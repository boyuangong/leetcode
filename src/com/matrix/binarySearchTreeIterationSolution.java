package com.matrix;

/**
 * @author boyuangong created on 8/28/19 at 22:50
 */

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class binarySearchTreeIterationSolution {
    Stack<TreeNode> ps = new Stack<>();
    int next;
    TreeNode cur;
    public binarySearchTreeIterationSolution(TreeNode root) {
        while(root.left != null) {
            ps.push(root);
            cur = root.left;
        }
    }

    public int next() {
        // given the current root val
        next = cur.val;
        // if it doesn't have right child(it's not possible to have left child
        // because left child always comes first), we move to it's parent
        if(cur.right== null) {
            if (!ps.empty()) {
                cur = ps.pop();
            }
        } else {
            cur = cur.right;
            while(cur.left != null) {
                ps.push(cur);
                cur = cur.left;
            }
        }
        return next;
    }

    public boolean hasNext() {
        return !ps.empty();
    }
}
