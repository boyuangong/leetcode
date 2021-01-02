package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 12/4/19 at 22:03
 */
public class nextPermutationSolution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // start from the right to get the first decrease order number
        int ind = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i;
                break;
            }
        }
        if (ind == -1) {
            partial_reverse_sort(nums, 0);
            return;
        }
        // there's a number decrease
        // find the first number larger than this number
        int ind_min = -1;
        for (int i = nums.length - 1; i > ind; i--) {
            if (nums[i] > nums[ind]) {
                ind_min = i;
                break;
            }
        }
        // swap these two
        swap(nums, ind, ind_min);
        // sort the reset, remeber it's always a decrease order
        partial_reverse_sort(nums, ind+1);
        System.out.println(Arrays.toString(nums));
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void partial_reverse_sort(int[] nums, int index) {
        int mid = (index + nums.length - 1) / 2;
        int i = 0;
        while (index <= mid) {
            swap(nums, index, nums.length - 1 - i);
            index++;
            i++;
        }
    }

    public static void main(String[] args) {
        nextPermutationSolution solution = new nextPermutationSolution();
        solution.nextPermutation(new int[]{1, 3, 2});
    }
}

