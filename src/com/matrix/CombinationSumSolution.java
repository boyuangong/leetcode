package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 3/24/20 at 15:40
 */
public class CombinationSumSolution {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return ans;
        }
        Arrays.sort(candidates);

        dfsCombine(candidates, target, 0, new ArrayList<>());
        return ans;
    }

    public void dfsCombine(int[] candidates, int target, int index, List<Integer> list) {
        for (int i=index; i<candidates.length; i++) {
            List<Integer> cur_list = new ArrayList<>(list);
            if (candidates[i] > target) {
                return;
            }
            cur_list.add(candidates[i]);
            int left = target - candidates[i];
            if (left == 0) {
                ans.add(cur_list);
            } else {
                dfsCombine(candidates, left, i, cur_list);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSumSolution solution = new CombinationSumSolution();
        System.out.println(solution.combinationSum(new int[]{2,3,6,7,11}, 11));
    }
}
