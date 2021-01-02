package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 2/8/20 at 15:21
 */
public class fourSumSolution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for (int i=0; i<=nums.length-4; i++) {
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j=i+1; j<=nums.length - 2; j ++) {
                if (j>i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int l = j + 1;
                int r = nums.length-1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        while (l+1<=r && nums[l] == nums[l-1]) {
                            l++;
                        }
                        while (r-1>=l && nums[r] == nums[r+1]) {
                            r++;
                        }
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        fourSumSolution solution = new fourSumSolution();
//        System.out.println(solution.fourSum(new int[]{0,0,0,0}, 0));
//        System.out.println(solution.fourSum(new int[]{-1,0,1,2,-1,-4}, -1));
        System.out.println(solution.fourSum(new int[]{-5,5,4,-3,0,0,4,-2}, 4));
    }
}
