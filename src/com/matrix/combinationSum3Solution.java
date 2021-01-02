package com.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boyuangong created on 10/9/19 at 22:16
 */
public class combinationSum3Solution {

    public static void main(String[] args) {
        combinationSum3Solution solution = new combinationSum3Solution();
        List<List<Integer>> lists = solution.combinationSum3(3, 9);
        System.out.println(lists.size());
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            List<Integer> cur = new ArrayList<>();
            combination(i, k, n, cur, ans);
        }

        return ans;
    }

    public void combination(int start, int size, int n, List<Integer> cur, List<List<Integer>> ans) {
        if (n <= 0) {
            return;
        }
        if (size == 1) {
            if (start == n) {
                cur.add(start);
                // find one combination
                ans.add(cur);
            }
            return;
        }

        if (size <= 0) {
            return;
        }

        cur.add(start);
        n -= start;
        size--;

        // k > 1
        for (int i = start + 1; i < 10; i++) {
            List<Integer> next_cur = new ArrayList<>(cur);
            combination(i, size, n, next_cur, ans);
        }
    }
}
