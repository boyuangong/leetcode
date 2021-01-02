package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 9/12/19 at 23:37
 */
public class firstLastInSortedArraySolution {
    public static void main(String[] args) {
        firstLastInSortedArraySolution sortedArraySolution = new firstLastInSortedArraySolution();
//        System.out.println(Arrays.toString(sortedArraySolution.searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(sortedArraySolution.searchRange(new int[]{1,2,3,3,3,3,4,5,9}, 3)));
    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                // will have different logic for finding same
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (nums[mid] != target) {
            return new int[]{-1, -1};
        }
        // Now the mid is the target
        int start = mid;
        int end = mid;
        // find the start:
        int s_left = left;
        int s_right = mid;
        int e_left = mid;
        int e_right = right;
        System.out.println(mid);
        System.out.println(left);
        System.out.println(right);
        while (s_left <= s_right) {
            int s_mid = (s_left + s_right) / 2;
            if (nums[s_mid] == target) {
                System.out.println(start);
                start = s_mid;
                s_right = s_mid - 1;
            } else if (nums[s_mid] < target) {
                s_left = s_mid + 1;
            }
            // not possible for nums[s_mid] larger than target
        }
        while (e_left <= e_right) {
            int e_mid = (e_left + e_right) / 2;
            if (nums[e_mid] == target) {
                if (nums[e_right] == target) {
                    end = e_right;
                } else {
                    end = e_mid;
                }
                e_left = e_mid + 1;
            } else {
                e_right = e_mid - 1;
            }
        }
        return new int[]{start, end};
    }
}
