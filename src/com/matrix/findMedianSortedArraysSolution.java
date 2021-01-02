package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 7/17/19 at 20:50
 */
public class findMedianSortedArraysSolution {
    public static int median(int[] arr) {
        if(arr.length % 2 == 0) {
           return arr[(int)arr.length /2];
        } else {
            return arr[(int)(arr.length +1)/2];
        }
    }

    public static int reverse(int x) {
        long start = 0;
        int q = 10;
        while(x != 0){
            int rem = x%q;
            x = x/q;
            start = start*q + rem;
        }
        if(start> Integer.MAX_VALUE || start < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) start;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }
}
