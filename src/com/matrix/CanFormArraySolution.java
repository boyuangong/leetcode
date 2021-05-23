package com.matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boyuangong created on 1/23/21 at 17:54
 */
public class CanFormArraySolution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] ar : pieces) {
            map.put(ar[0], ar);
        }
        int  i = 0;
        System.out.println(map.toString());
        while(i < arr.length) {
            System.out.println(i);
            int cur = arr[i];
            if (!map.containsKey(cur)){
                return false;
            }
            int[] curar = map.get(cur);
            System.out.println(Arrays.toString(curar));
            for (int j = 0; j<curar.length; j++) {
                System.out.println(j);
                System.out.println(i);
                cur = arr[i];
                int jint = curar[j];
//                System.out.println(jint);
//                System.out.println(cur);
                if (cur != jint) {
                    return false;
                } else {
                    i++;
                    System.out.println(i);
                }
            }
        }
        System.out.println(true);
        return true;
    }

    public static void main(String[] args) {
        CanFormArraySolution solution = new CanFormArraySolution();
        solution.canFormArray(new int[]{12,21,11,22}, new int[][]{{12, 21}, {1, 2}});
    }
}
