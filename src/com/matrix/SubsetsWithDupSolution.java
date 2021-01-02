package com.matrix;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 8/24/20 at 20:16
 */
public class SubsetsWithDupSolution {
    List<List<Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans =  new ArrayList<>();

        ans.add(new ArrayList<>());
        Arrays.sort(nums);
        addCurrentSubsets(nums, 0);
        System.out.println(ans);
        return ans;
    }

    public void addCurrentSubsets(int[] nums, int idx) {
        if (idx >= nums.length) {
            return;
        }
        List<Integer> cur_single = new ArrayList<>();
        cur_single.add(nums[idx]);
        if (!ans.contains(cur_single)) {
            ans.add(new ArrayList<>(cur_single));
        }
        for (int i=idx+1; i<nums.length; i++) {
            cur_single.add(nums[i]);
            ans.add(new ArrayList<>(cur_single));
        }
        addCurrentSubsets(nums, idx+1);
    }

    public static void main(String[] args) {
        SubsetsWithDupSolution subsetsWithDupSolution = new SubsetsWithDupSolution();
        subsetsWithDupSolution.subsetsWithDup(new int[]{1,2,3});
    }
}
