package com.matrix;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author boyuangong created on 11/24/19 at 17:50
 */
public class printHouseSolution {
    public int minCost(int[][] costs) {
        int[] pre_cost = new int[3];
        for (int i=0; i<costs.length; i++) {
            // initialize
            if (i == 0) {
                for (int j = 0; j < pre_cost.length; j++) {
                    pre_cost[j] = costs[i][j];
                }
                continue;
            }
            int[] tmp = new int[3];
            // otherwise find the minimum cost
            for (int j = 0; j < pre_cost.length; j ++) {
                tmp[j] = costs[i][j] + min_except_j(pre_cost, j);
            }
            for (int j=0; j < pre_cost.length; j++) {
                pre_cost[j] = tmp[j];
            }
        }


        return Arrays.stream(pre_cost).min().getAsInt();
    }

    public int min_except_j(int[] costs, int j) {
        int min = Integer.MAX_VALUE;
        for (int i=0; i<costs.length; i++) {
            if (i!=j && costs[i] < min) {
                min = costs[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new printHouseSolution().minCost(new int[][]{{17,2,17},{16,16,5},{14,3,19}}
        ));
    }
}
