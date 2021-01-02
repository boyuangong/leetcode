package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 10/9/19 at 00:22
 */
public class arrayNestingSolution {
    public int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i=0; i<nums.length; i++) {
            System.out.println("-------------" + i);
            int j = i;
            int count = 0;
            while (nums[j] >= 0) {
                // -1 represents visited
                int next = nums[j];
                nums[j] = -1;
                System.out.println(Arrays.toString(nums));
                j = next;
                count ++;
                System.out.println(j);
                System.out.println(nums[j]);
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        arrayNestingSolution solution = new arrayNestingSolution();
        int j = solution.arrayNesting(new int[]{5,4,0,3,1,6,2});
        System.out.println(j);
    }
}
