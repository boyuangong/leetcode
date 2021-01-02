package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 6/21/20 at 01:19
 */
public class TreeAncestor {
    int[][] binaryUp;

    public TreeAncestor(int n, int[] parent) {
        int m = (int) Math.ceil(Math.log(n)/Math.log(2));
        binaryUp = new int[n][m];
        for (int[] up : binaryUp) {
            Arrays.fill(up, -1);
        }
        for (int i = 1; i < parent.length; i++) {
            try {
                int searchUp = parent[i];
                binaryUp[i][0] = searchUp;

                if (searchUp >= 0) {
                    searchUp = parent[searchUp];
                }
                int j = 1;
                while (searchUp >= 0) {
                    binaryUp[i][j] = searchUp;
                    if (binaryUp[searchUp][1] != -1) {
                        searchUp = binaryUp[searchUp][1];
                        j++;
                    } else {
                        break;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(i);
                throw e;
            }
        }
        System.out.println(Arrays.deepToString(binaryUp));
    }

    public int getKthAncestor(int node, int k) {
        int diff = Integer.MAX_VALUE;
        if (k > binaryUp.length) {
            return -1;
        }
        while (diff > 0) {
            int lookup = (int) Math.floor(Math.log(k)/Math.log(2));
            diff = (int) (k - Math.pow(2, lookup));
            k = diff;
            node = binaryUp[node][lookup];
            if (node == -1) {
                return -1;
            }
        }

        return node;
    }

    public static void main(String[] args) {
        int n = 50000;
        int[] test_p = new int[n];
        for (int i = 0; i < test_p.length; i++) {
            test_p[i] = (i - 1) / 2;
        }
        test_p[0] = -1;
        System.out.println(Arrays.toString(test_p));
//        System.out.println(LargeInputsConstant.treeAncestorParent.length);
//        int[] parent = LargeInputsConstant.treeAncestorParent;
        TreeAncestor obj = new TreeAncestor(n, test_p);

        int[][] tests = new int[][]{{50, 3}, {5, 2}, {6, 3}};

        for (int i = 0; i < tests.length; i++) {
            int[] test = tests[i];
            int ans = obj.getKthAncestor(test[0], test[1]);
            System.out.println(ans);
        }
    }
}
