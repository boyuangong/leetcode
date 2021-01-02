package com.matrix;

/**
 * @author boyuangong created on 1/7/20 at 23:53
 */
public class atoiSolution {
    public int atoi(String str) {
        char[] chars = str.toCharArray();
        boolean minus = false;
        long ans = 0;
        for(Character c : chars) {
            if (c == '-') {
                minus = true;
            } else if (Character.isDigit(c)) {
                ans = ans * 10;
                ans += Character.getNumericValue(c);
            } else if (c != ' ') {
                break;
            }
        }

        ans = minus? -ans: ans;
        int ansInt = 0;
        if (ans > Integer.MAX_VALUE) {
            ansInt = Integer.MAX_VALUE;
        } else if (ans < Integer.MIN_VALUE) {
            ansInt = Integer.MIN_VALUE;
        } else {
            ansInt = (int) ans;
        }

        return ansInt;
    }

    public static void main(String[] args) {
        System.out.println(new atoiSolution().atoi("+-2"));
    }
}
