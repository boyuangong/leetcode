package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 9/7/19 at 23:54
 */
public class NumMatrix {
    int[][] ints;

    public NumMatrix(int[][] matrix) {
        int[][] matrixSum;
        if (matrix == null || matrix.length == 0) {
            matrixSum = new int[][]{{0}};
        } else {
            matrixSum = new int[matrix.length + 1][matrix[0].length + 1];
            for (int[] sum : matrixSum) {
                Arrays.fill(sum, 0);
            }
            for (int i = 1; i <= matrix.length; i++) {
                for (int j = 1; j <= matrix[0].length; j++) {
                    matrixSum[i][j] = matrixSum[i - 1][j] + matrixSum[i][j - 1] + matrix[i - 1][j - 1]
                            - matrixSum[i - 1][j - 1];
                }
            }
        }
        this.ints = matrixSum;
        for (int[] sum : ints) {
            System.out.println(Arrays.toString(sum));
        }
    }



    public int sumRegion(int row1, int col1, int row2, int col2) {
        return ints[row2 + 1][col2 + 1] - ints[row2 + 1][col1] - ints[row1][col2 + 1]
                + ints[row1][col1];
    }
    public static void main(String[] args) {
        int[][] target = new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0,
                5}};
        NumMatrix matrix = new NumMatrix(target);
        System.out.println(matrix.sumRegion(2, 1, 4, 3));
        System.out.println(matrix.sumRegion(1, 1, 2, 2));
        System.out.println(matrix.sumRegion(1, 2, 2, 4));
    }
}
