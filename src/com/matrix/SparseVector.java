package com.matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * @author boyuangong created on 4/15/21 at 23:50
 */
public class SparseVector {
    public Map<Integer, Integer> nonZeros;


    SparseVector(int[] nums) {
        nonZeros = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                nonZeros.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry: nonZeros.entrySet()) {
            int key = entry.getKey();
            if (vec.nonZeros.containsKey(key)) {
                ans += entry.getValue() * vec.nonZeros.get(key);
            }
        }
        return ans;
    }
}
