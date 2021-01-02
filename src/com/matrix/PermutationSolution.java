package com.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boyuangong created on 3/29/20 at 17:01
 */
public class PermutationSolution {
    List<List<Integer>> ans;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> nums_list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            nums_list.add(nums[i]);
        }
        dfsPermutation(nums_list, new ArrayList<>());
        return ans;
    }

    public void dfsPermutation(List<Integer> nums_list, List<Integer> list) {
        if (nums_list.size() == 1) {
            list.add(nums_list.get(0));
            ans.add(list);
            return;
        }

        // otherwise go deep
        for (int i = 0; i < nums_list.size(); i++) {
            List<Integer> cur_list = new ArrayList<>(list);
            cur_list.add(nums_list.get(i));
            List<Integer> cur_nums_list = new ArrayList<>(nums_list);
            cur_nums_list.remove(i);
            dfsPermutation(cur_nums_list, cur_list);
        }
    }

    public static void main(String[] args) {
        PermutationSolution solution = new PermutationSolution();
        List<List<Integer>> list = solution.permute(new int[]{1,2,3});
        System.out.println(list.toString());
    }
}

