package com.matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 9/11/19 at 23:48
 */
public class threeSumSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return ans;
        }

        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++) {
            int left = i+1;
            int right = nums.length-1;
            int goal = -nums[i];
            while(left<right) {
                if(nums[left]+nums[right] < goal) {
                    left ++;
                } else if (nums[left] + nums[right] > goal) {
                    right --;
                } else {
                    if(ans.indexOf(Arrays.asList(nums[i], nums[left], nums[right])) < 0 ) {
                        ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    }

                    left++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        threeSumSolution solution = new threeSumSolution();
        System.out.println(solution.threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(solution.threeSum(new int[]{0,0,0,0,0}));
        System.out.println(solution.threeSum(new int[]{-2, 0, 1,1,2}));
    }
}
