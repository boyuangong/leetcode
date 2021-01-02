package com.matrix;

/**
 * @author boyuangong created on 10/17/19 at 00:30
 */
public class maxChunksToSortSolution {
    public int maxChunksToSorted(int[] arr) {
        int ans = 0, max = 0;
        for (int i = 0; i < arr.length; ++i) {
            max = Math.max(max, arr[i]);
            if (max == i) ans++;
            System.out.println(ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        maxChunksToSortSolution sortSolution = new maxChunksToSortSolution();
        sortSolution.maxChunksToSorted(new int[]{0,4,2,5,3,1});
    }
}
