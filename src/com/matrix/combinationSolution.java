package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 8/22/20 at 18:29
 */
public class combinationSolution {
    private List<List<Integer>> ans;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();

        dfs(k, new ArrayList<>(), n, 1);
        System.out.println(ans.toString());
        System.out.println(ans.size());
        return ans;
    }

    private void dfs(int k, List<Integer> list, int n, int idx) {

        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i=idx; i<=n-k+1; i++) {
            list.add(i);

            dfs(k-1, list, n, i+1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        combinationSolution combinationSolution = new combinationSolution();
        combinationSolution.combine(4, 2);
    }
}
