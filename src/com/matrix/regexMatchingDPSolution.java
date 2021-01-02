package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 1/31/20 at 18:00
 */
public class regexMatchingDPSolution {

    private Boolean[][] memo;

    public boolean isMatchWithRecursive(String s, String p) {
        boolean ans = isMatchRecursiveImpl(0, 0, s, p);
        return ans;
    }

    public boolean isMatchRecursiveImpl(int s_start, int p_start, String s, String p) {
        System.out.println("current s_start: " + s_start + " p_start: " + p_start);
        if (p_start == p.length()) {
            return s_start == s.length();
        }

        boolean first_match = s_start < s.length() && Arrays.asList('.', s.charAt(s_start)).contains(p.charAt(p_start));
        boolean rest_match;
        if (p_start + 1 < p.length() && p.charAt(p_start + 1) == '*') {
            // first check whether it matches the patten after '*'. a => a*a
            rest_match = isMatchRecursiveImpl(s_start, p_start+2, s, p);

            // then check whether the rest of the string matches with current pattern aa => a*a
            // this check should first guarantee that first_match is true, first_match is true also
            // means s_start < s.length();
            if (first_match) {
                rest_match = rest_match || isMatchRecursiveImpl(s_start + 1, p_start, s, p);
            }
            return rest_match;
        } else {
            return first_match && isMatchRecursiveImpl(s_start + 1, p_start + 1, s, p);
        }
    }

    public boolean isMatchDP(String s, String p) {
        this.memo = new Boolean[s.length()+1][p.length()+1];
        return isMatchDPImpl(0, 0, s, p);
    }

    private boolean isMatchDPImpl(int s_start, int p_start, String s, String p) {
        if (memo[s_start][p_start] != null) {
            return memo[s_start][p_start];
        }

        boolean cur_ans;

        if (p_start == p.length()) {
            cur_ans = s_start == s.length();
        } else {
            boolean first_match = s_start < s.length() && Arrays.asList('.', s.charAt(s_start)).contains(p.charAt(p_start));

            if (p_start + 1 < p.length() && p.charAt(p_start + 1) == '*') {
                cur_ans = isMatchDPImpl(s_start, p_start + 2, s, p);
                if (first_match) {
                    cur_ans = cur_ans || isMatchDPImpl(s_start + 1, p_start, s, p);
                }
            } else {
                if (first_match) {
                    cur_ans = isMatchDPImpl(s_start + 1, p_start + 1, s, p);
                } else {
                    cur_ans = false;
                }
            }
        }

        memo[s_start][p_start] = cur_ans;
        return cur_ans;
    }

    public static void main(String[] args) {
        regexMatchingDPSolution solution = new regexMatchingDPSolution();
//        System.out.println(solution.isMatchWithRecursive("aaaaaaaaaaaaab",
//                "a*a*a*a*a*a*a*a*a*a*c"
//        ));
        System.out.println(solution.isMatchDP("aaaaaaaac",
                "a*a*a*a*a*a*a*a*a*a*c"
        ));
    }
}
