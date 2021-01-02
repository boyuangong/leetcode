package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 1/8/20 at 23:45
 */
public class regexMatchingWrongSolution {
    public boolean isMatch(String s, String p) {
        char[][] pattern_array = splitToPatternArray(p);

        int p_index = 0;

        for (int i = 0; i < s.length(); i++) {
            while (true) {
                int match_result = matches(s.toCharArray()[i], p_index, pattern_array);
                if (match_result == 2) {
                    break;
                } else if (match_result == 1) {
                    p_index++;
                    break;
                } else if (match_result == -1) {
                    return false;
                } else {
                    p_index++;
                }
            }
        }

        if (leftPattern(p_index, pattern_array)) {
            return false;
        }

        return true;
    }

    // * means repeat, - means not repeat
    public char[][] splitToPatternArray(String p) {
        List<char[]> patterns = new ArrayList<>();
        char[] p_char = p.toCharArray();
        for (int i = 0; i < p_char.length - 1; i++) {
            if (p_char[i + 1] == '*') {
                patterns.add(new char[]{p_char[i], '*'});
                i++;
            } else {
                patterns.add(new char[]{p_char[i], '-'});
            }
        }

        if (p_char[p_char.length - 1] != '*') {
            patterns.add(new char[]{p_char[p_char.length - 1], '-'});
        }

        char[][] ans = new char[patterns.size()][];
        for (int i = 0; i < patterns.size(); i++) {
            ans[i] = patterns.get(i);
        }
        return ans;
    }

    // returns: 2. matches and keep the index (a* -> a)
    // 1. matches but increase the index (a- -> a)
    // 0. doesn't match but increase the index (c* -> a)
    // -1. doesn't match and return false(c- -> a);
    public int matches(Character c, int p_index, char[][] pattern_array) {
        if (p_index >= pattern_array.length) {
            return -1;
        }

        if (pattern_array[p_index][0] == c || pattern_array[p_index][0] == '.') {
            if (pattern_array[p_index][1] == '*') {
                return 2;
            } else {
                return 1;
            }
        } else {
            if (pattern_array[p_index][1] == '*') {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public boolean leftPattern(int index, char[][] pattern_array) {
        if (index == pattern_array.length) {
            return false;
        }

        for (int i = index; i < pattern_array.length; i++) {
            if (pattern_array[i][1] == '-') {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        regexMatchingWrongSolution solution = new regexMatchingWrongSolution();
        System.out.println(Arrays.deepToString(solution.splitToPatternArray("aa")));
        System.out.println(solution.isMatch("ab", ".*"));
        System.out.println(solution.isMatch("ab", ".*c"));
    }
}
