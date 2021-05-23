package com.matrix;

import java.util.Arrays;
import java.util.HashMap;

import static com.matrix.TreeNode.printTreeNodeInorder;
import static com.matrix.TreeNode.printTreeNodePreorder;

/**
 * @author boyuangong created on 2/7/21 at 13:09
 */
public class ConstructNewTreePreorderInorderSolution {
    int[] preorder;
    int preorderIndex;
    HashMap<Integer, Integer> indexmap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        this.preorder = preorder;
        indexmap = new HashMap<>();

        return buildHelper(inorder);

    }

    private TreeNode buildHelper(int[] inorder) {
        if (inorder.length == 0) {
            return null;
        }
        if (preorderIndex >= preorder.length) {
            return null;
        }

        System.out.println(preorderIndex);

        int rootval = preorder[preorderIndex];
        preorderIndex ++;
        TreeNode root = new TreeNode(rootval, null, null);

        int index = getIndex(inorder, rootval);

        int[] left = new int[]{};
        int[] right = new int[]{};
        if (index > 0) {
            left = Arrays.copyOfRange(inorder, 0, index);
        }

        if (index < inorder.length - 1) {
            right = Arrays.copyOfRange(inorder, index+1, inorder.length);
        }

        System.out.println(index);
        System.out.println(rootval);
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        root.left = buildHelper(left);
        root.right = buildHelper(right);
        return root;
    }

    private int getIndex(int[] nums, int get) {
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == get) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ConstructNewTreePreorderInorderSolution solution = new ConstructNewTreePreorderInorderSolution();
        TreeNode test = solution.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        printTreeNodePreorder(test);
        System.out.println("----");
        printTreeNodeInorder(test);
    }


}
