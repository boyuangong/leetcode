package com.matrix;

/**
 * @author boyuangong created on 9/15/19 at 15:36
 */
public class rotatedSortedArraySolution {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                if(nums[mid] < nums[left] && nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // nums[mid] > target
                if(nums[mid] > nums[right] && nums[left] > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        if(left >= 0 && left < nums.length &&nums[left] == target) {
            return left;
        }
        if(right >= 0 && right < nums.length && nums[right] == target) {
            return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        rotatedSortedArraySolution sortedArraySolution = new rotatedSortedArraySolution();
        System.out.println(sortedArraySolution.search(new int[]{1,3}, 0));
    }
}
