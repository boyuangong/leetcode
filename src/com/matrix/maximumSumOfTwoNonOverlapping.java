package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 10/6/19 at 12:59
 */
public class maximumSumOfTwoNonOverlapping {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] prefix_sum = new int[A.length];
        prefix_sum[0] = A[0];
        for (int i=1; i<A.length; i++) {
            prefix_sum[i] = prefix_sum[i-1] + A[i];
        }

        // find maximum of the L.M group
        // initialize the sum from L+M-1
        int sum = prefix_sum[L+M-1];
        int max_l = prefix_sum[L-1];
        int max_m = prefix_sum[M-1];
        System.out.println(Arrays.toString(prefix_sum));
        System.out.println(max_l);
        System.out.println(max_m);
        System.out.println(sum);
        for (int i=L+M; i<A.length; i++) {
            System.out.println("-------------------------------");
            System.out.println("current i is " + i);
            int cur_m = prefix_sum[i-L] - prefix_sum[i-M-L];
            max_m = Math.max(max_m, cur_m);
            System.out.println(String.format("cur_m is %d, max_m is %d", cur_m, max_m));
            int cur_l = prefix_sum[i-M] - prefix_sum[i-M-L];
            max_l = Math.max(max_l, cur_l);
            System.out.println(String.format("cur_l is %d, max_l is %d", cur_l, max_l));
            int cur_sum_l = max_l + prefix_sum[i]-prefix_sum[i-M];
            int cur_sum_m = max_m + prefix_sum[i]-prefix_sum[i-L];
            System.out.println(String.format("cur_sum_l is %d, cur_sum_m is %d", cur_sum_l, cur_sum_m));
            sum = Math.max(sum, cur_sum_l);
            sum = Math.max(sum, cur_sum_m);
            System.out.println("sum is " + sum);
            System.out.println("-------------------------------");
        }

        return sum;

    }

    public static void main(String[] args) {
        maximumSumOfTwoNonOverlapping solution = new maximumSumOfTwoNonOverlapping();
        System.out.println(solution.maxSumTwoNoOverlap(new int[]{2,1,5,6,0,9,5,0,3,8}, 4 ,3));
        System.out.println(solution.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1 ,2));
    }

}
