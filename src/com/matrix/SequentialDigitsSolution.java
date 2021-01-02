package com.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boyuangong created on 12/15/19 at 16:04
 */
public class SequentialDigitsSolution {
    public List<Integer> sequentialDigits(int low, int high) {
        int low_num = getNumberOfDigits(low);
        int low_first = getFirstNumberOfInteger(low);
        int high_num = getNumberOfDigits(high);
        int high_first = getFirstNumberOfInteger(high);
        List<Integer> ans = new ArrayList<Integer>();

        if (low_num == high_num) {
            int max = Math.min(high_first, 10-high_num);
            for (int i=low_first; i<=max; i++) {
                int g = generateSequentialDigits(i, low_num);
                if (g >= low && g <= high) {
                    ans.add(g);
                }
            }
            return ans;
        }


        // low_nums
        for (int i=low_first; i<=10-low_num; i++) {
            int g = generateSequentialDigits(i, low_num);
            if (g>= low) {
                ans.add(generateSequentialDigits(i, low_num));
            }
        }

        // high_nums
        for (int i=1; i<=high_first; i++) {
            if (i>10-high_num) {
                break;
            }
            int g = generateSequentialDigits(i, high_num);
            if ( g <= high) {
                ans.add(generateSequentialDigits(i, high_num));
            }
        }

        // for all that between low_num and high_num
        for (int i = low_num + 1; i<high_num; i++) {
            for (int j = 1; j<=10-i; j++) {
                ans.add(generateSequentialDigits(j, i));
            }
        }
        return ans;

    }

    public int getNumberOfDigits(int n) {
        int i = 0;
        while (n != 0) {
            n = n / 10;
            i ++;
        }
        return i;
    }

    public int getFirstNumberOfInteger(int n) {
        while (n != 0) {
            n = n / 10;
            if (n < 10) {
                return n;
            }
        }
        return n;
    }

    public int generateSequentialDigits(int first, int num) {
        int i = 0;
        for (int j=0; j<num; j++) {
            i = i * 10 + (first + j);
        }
        return i;
    }

    public static void main(String[] args) {
        SequentialDigitsSolution solution = new SequentialDigitsSolution();
        System.out.println(solution.sequentialDigits(1000, 130000).toString());
    }
}
