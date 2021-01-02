package com.matrix;

/**
 * @author boyuangong created on 9/16/20 at 22:35
 */
public class BinarySearchForLengthSolution {

    public int getNumOfS(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right - 1) {
            System.out.println(left);
            System.out.println(right);
            int mid = (left + right) / 2;
            if (arr[mid] == 0) {
                right = mid;
            } else {
                left = mid;
            }
        }

        System.out.println("-------");
        System.out.println(left);
        System.out.println(right);

        if (left == right - 1) {
            if (arr[right] == 1) {
                return right + 1;
            } else if (arr[left] == 1) {
                return left + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        BinarySearchForLengthSolution solution = new BinarySearchForLengthSolution();
        int i = solution.getNumOfS(new int[]{1, 1, 1, 1, 0});
        System.out.println(i);
    }
}
