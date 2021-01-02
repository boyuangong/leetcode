package com.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boyuangong created on 7/18/19 at 21:33
 */
public class longestSubStringSolution {
    public static int longestSubString(String s) {
        int start = 0;
        int end = 0;
        int max = 0;
        if (s == null || s.length() == 0) {
            return 0;
        }
        List<Character> sub = new ArrayList<Character>();
        while(start < s.length()) {
            if(sub.size() == 0) {
                sub.add(s.charAt(end));
                end += 1;
                continue;
            }
//            System.out.println(end);
//            System.out.println("max" + max);
            if(sub.contains(s.charAt(end))) {
                // repeated char
                max = Math.max(max, sub.size());
                // remove the element before the first repeat element
                sub = sub.subList(sub.indexOf(s.charAt(end))+1, sub.size());
                sub.add(s.charAt(end));
                start += 1;
                end += 1;
            } else {
                sub.add(s.charAt(end));
                end += 1;
            }
            System.out.println("arraylist" + sub.toString());
            if (end >= s.length()) {
                return Math.max(max, sub.size());
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "abcababba";
        System.out.println(longestSubString(s));
    }
}
