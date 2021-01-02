package com.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author boyuangong created on 10/26/19 at 16:44
 */
public class timeBasedKeyValueStoreSolution {
    //https://leetcode.com/problems/time-based-key-value-store/ 981
    /**
     * Initialize your data structure here.
     */
    private Map<String, Map<Integer, String>> map;

    public timeBasedKeyValueStoreSolution() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (map.containsKey(key)) {
            map.get(key).put(timestamp, value);
        } else {
            Map<Integer, String> cur_map = new TreeMap<>();
            cur_map.put(timestamp, value);
            map.put(key, cur_map);
        }
    }

    public String get(String key, int timestamp) {
        Map<Integer, String> cur_map = map.get(key);
        ArrayList<Integer> list = new ArrayList<>(cur_map.keySet());
//        Collections.sort(list);
        int i = Collections.binarySearch(list, timestamp);
        if (i >= 0) {
            return cur_map.get(list.get(i));
        } else if (i == -1) {
            return "";
        } else {
            return cur_map.get(list.get(-i - 2));
        }
    }

    public static void main(String[] args) {
        timeBasedKeyValueStoreSolution solution = new timeBasedKeyValueStoreSolution();
        solution.set("foo", "bar", 10);
        solution.set("foo", "bar2", 20);
        System.out.println(solution.get("foo", 5));
        System.out.println(solution.get("foo", 10));
        System.out.println(solution.get("foo", 15));
        System.out.println(solution.get("foo", 20));
        System.out.println(solution.get("foo", 25));
    }
}
