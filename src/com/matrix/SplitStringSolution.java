package com.matrix;

/**
 * @author boyuangong created on 5/8/21 at 13:20
 */
public class SplitStringSolution {
    public boolean splitString(String s) {
        if (s.length() <= 1) {
            return false;
        }
        long start = 0;
        int ind = 0;
        while (ind < s.length() && s.charAt(ind) == '0') {
            ind ++;
        }

        for (int i=ind; i<s.length()-1; i++) {
            start = start * 10 + s.charAt(i) - '0';
            System.out.println("------------------------");

            System.out.println(start);
            if (recur(start, i+1, s)) {
                return true;
            }
        }
        return false;
    }

    private boolean recur(long start, int ind , String s) {
        System.out.println("----");
        if (ind == s.length()) {
            return true;
        }
        long cur = 0;

        while (ind < s.length() && s.charAt(ind) == '0') {
            ind ++;
        }

        while (ind < s.length() && cur < start - 1) {
            cur = cur * 10 + s.charAt(ind) - '0';
            ind ++;
        }
        System.out.println(cur);
        return cur == start - 1 && recur(cur, ind, s);
    }

    public static void main(String[] args) {
        SplitStringSolution solution = new SplitStringSolution();
        System.out.println(solution.splitString("1234"));
    }
}
