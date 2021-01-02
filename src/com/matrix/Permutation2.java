package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author boyuangong created on 8/16/20 at 16:21
 */
public class Permutation2 {
    List<List<Integer>> ans;

    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> numslist = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(numslist);
        backtracking(numslist, 0);
        return ans;
    }

    public void backtracking(List<Integer> nums, int first) {

        if (first == nums.size() - 1) {
            // reach the end of the nums, no need to do more
            // backtracking
//            if (ans.contains(nums)) {
//                return;
//            }
            ans.add(new ArrayList<>(nums));
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for (int i = first; i < nums.size(); i++) {
            if (visited.contains(nums.get(i))) {

            }
            if (!visited.contains(nums.get(i))) {
                visited.add(nums.get(i));
                Collections.swap(nums, first, i);
                backtracking(nums, first + 1);
                Collections.swap(nums, first, i);
            }
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,2,1,3,3};

        Permutation2 permutation2 = new Permutation2();
        List<List<Integer>> lists = permutation2.permuteUnique(test);
        System.out.println(lists.toString());
    }
}
