package com.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boyuangong created on 9/8/19 at 21:10
 */
public class ZigZagConversionSolution {
    public String convert(String s, int numRows) {
        if(s == null || s.length() <= 2) {
            return s;
        }

        if(numRows == 1 || numRows>s.length()) {
            return s;
        }

        int n = numRows + numRows - 2;
        char[] schar = s.toCharArray();
        Map<Integer, ArrayList<Character>> map = new HashMap<>();
        for (int i = 0; i < schar.length; i++) {
            int r = i % n;
            if (r > numRows-1) {
                r = n - r;
            }

            if (!map.containsKey(r)) {
                ArrayList<Character> list = new ArrayList<>();
                list.add(schar[i]);
                map.put(r, list);
            } else {
                map.get(r).add(schar[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<numRows; i++) {
            ArrayList<Character> list = map.get(i);
            for(Character c: list) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigZagConversionSolution solution = new ZigZagConversionSolution();
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        System.out.println(solution.convert("A", 1));
        System.out.println(solution.convert("ABC", 4));
    }
}
