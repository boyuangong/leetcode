package com.matrix;

import java.util.Stack;

/**
 * @author boyuangong created on 9/15/19 at 16:51
 */
public class rotatedSortedArrayIISolution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            System.out.println(mid);
            System.out.println(left);
            System.out.println(right);
            System.out.println("---------------");
            if(nums[mid] == target) {
                return true;
            }
            if(nums[mid] < target) {
                // 1 2 5 7 0 1
                while(left < nums.length && nums[left] == nums[mid]) {
                    left = left + 1;
                }
                if(nums[left] > nums[mid] && nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                while(right > 0 && nums[right] == nums[mid]) {
                    right = right - 1;
                }
                if(nums[mid] >= nums[right] && nums[left] > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        System.out.println(left);
        System.out.println(right);
        if(left >= 0 && left < nums.length && nums[left] == target) {
            return true;
        }
        if(right >= 0 && right < nums.length && nums[right] == target) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        rotatedSortedArrayIISolution sortedArrayIISolution = new rotatedSortedArrayIISolution();
        System.out.println(sortedArrayIISolution.search(new int[]{1,1}, 0));
        Stack<Integer> s = new Stack<>();
    }
}
