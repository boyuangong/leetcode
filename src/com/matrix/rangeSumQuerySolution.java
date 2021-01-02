package com.matrix;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author boyuangong created on 9/3/19 at 23:44
 */
public class rangeSumQuerySolution {
    int[] sums;
    public rangeSumQuerySolution(int[] nums) {
        sums = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            if(i == 0){
                sums[i] = nums[i];
            } else {
                sums[i] = nums[i] + sums[i-1];
            }
        }
        System.out.println(Arrays.toString(sums));
    }

    public int sumRange(int i, int j) {
        if(i>0) {
            return sums[j] - sums[i-1];
        }
        return sums[j];
    }

    public static void main(String[] args) {
        rangeSumQuerySolution solution = new rangeSumQuerySolution(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(solution.sumRange(2, 5));
    }
}
