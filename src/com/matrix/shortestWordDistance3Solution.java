package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 10/7/19 at 23:18
 */
public class shortestWordDistance3Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        for (int i=0; i<words.length; i++) {
            ArrayList<Integer> list;
            if (!map.containsKey(words[i])) {
                list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            } else {
                list = map.get(words[i]);
                list.add(i);
                map.put(words[i], list);
            }
        }
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        int min = Integer.MAX_VALUE;
        for (int i: list1) {
            for (int j: list2) {
                if (i != j) {
                    min = Math.min(min, Math.abs(i - j));
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        shortestWordDistance3Solution solution = new shortestWordDistance3Solution();
//        int i = solution.shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding");
//        System.out.println(i);
        int j = solution.shortestWordDistance(new String[]{"a", "a"}, "a", "a");
        System.out.println(j);
    }
}
