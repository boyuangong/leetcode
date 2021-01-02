package com.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boyuangong created on 5/31/20 at 17:51
 */
public class sprialMatrixSolution {
    List<Integer> list;

    public List<Integer> spiralOrder(int[][] matrix) {
        // convert the problem to print the most outside numbers of a matrix
        // Then recursively print the inside matrix until print all
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        // matrix m * n
        int m = matrix.length;
        int n = matrix[0].length;

        list = new ArrayList<>();
        int t = 0;
        while (m-1-t >= t && n-1-t >= t) {
            addList(matrix, t);
            t++;
        }
        System.out.println(list.toString());
        return list;
    }

    public void addList(int[][] matrix, int t) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int j = t; j <= n - 1 - t; j++) {
            list.add(matrix[t][j]);
        }
        for (int i = t + 1; i <= m - 1 - t; i++) {
            //skip the matrix[0][n-1]
            list.add(matrix[i][n - 1 - t]);

        }


        if (m - 1 - t != t) {
            for (int j = n - 2 - t; j >= t; j--) {
                list.add(matrix[m - 1 - t][j]);

            }
        }

        if (n-1-t != t) {
            for (int i = m - 2 - t; i >= t + 1; i--) {
                list.add(matrix[i][t]);

            }
        }
    }

    public static void main(String[] args) {
        sprialMatrixSolution solution = new sprialMatrixSolution();
        int[][] test = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int[][] test2 = new int[][]{{7}, {9}, {6}};
        int[][] test3 = new int[][]{{2,5,8}, {4,0,-1}};
        solution.spiralOrder(test3);
    }
}
