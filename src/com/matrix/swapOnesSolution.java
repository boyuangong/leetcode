package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 9/26/19 at 22:51
 */
public class swapOnesSolution {
    public int minSwaps(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }
        int ones = count_one(data);
        if (ones == 0 || ones == 1) {
            return 0;
        }
        System.out.println(ones);
        int ans = Integer.MAX_VALUE;
        int cur = 0;
        for (int i = 0; i < data.length - ones; i++) {
            if (i == 0) {
                cur = ones - count_one(Arrays.copyOfRange(data, 0, ones));
                if (cur < ans) {
                    ans = cur;
                }
                continue;
            }

            if(data[i-1] != data[i+ones-1]) {
                if(data[i+ones-1] == 1) {
                    cur --;
                } else {
                    cur ++;
                }
            }
            if (cur < ans) {
                ans = cur;
            }
            System.out.println(String.format("current i is %d, current cur is %d", i, cur));
        }
        return ans;
    }

    public int count_one(int[] data) {
        int count = 0;
        for (int i : data) {
            if (i == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        swapOnesSolution swapOnesSolution = new swapOnesSolution();
        List<List<Integer>> list = new ArrayList<>();

        System.out.println(swapOnesSolution.minSwaps(new int[]{1,0,1,0,1}));
    }
}
