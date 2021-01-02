package com.matrix;

import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author boyuangong created on 2/16/20 at 22:01
 */
public class longestValidParenthesesSolution {
    public int longestValidForCurrent(String s) {
        int max = 0;
        char[] schar = s.toCharArray();
        int cur_max = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < schar.length; i++) {
            if (schar[i] == ')') {
                if (cur_max != 0) {
                    if (stack.empty()) {
                        // we found a ")", but there's no match "(" before it. So
                        // the current valid stops here.
                        max = Math.max(max, cur_max);
                        cur_max = 0;
                    } else {
                        stack.pop();
                        cur_max++;
                    }
                }
            } else {
                stack.push(')');
                cur_max++;
            }
        }
        if (cur_max != 0) {
            max = Math.max(max, cur_max - stack.size());
        }
        return max;
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            System.out.println("current i: " + i);
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
            System.out.println(maxans);
            System.out.println(stack.toString());
        }
        return maxans;
    }

//    public int longestValidParentheses(String s) {
//        int max = 0;
//        for (int i=0; i<s.length(); i++) {
//            int cur_max = longestValidForCurrent(s.substring(i));
//        }
//    }

    public static void main(String[] args) {
        longestValidParenthesesSolution solution = new longestValidParenthesesSolution();
//        System.out.println(solution.longestValidForCurrent(")()())())))(()()()))()()())))))()"));
        // 0 1 2 3 4 5 6 7
        // ( ( ) ( ) ) ) )
        System.out.println(solution.longestValidParentheses(")))(()())))"));
    }
}