package com.matrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 9/3/20 at 21:14
 */
public class FindItinerarySolution {
    LinkedList<String> ans;

    Map<String, LinkedList<String>> map;
    public List<String> findItinerary(List<List<String>> tickets) {
        ans = new LinkedList<>();
        map = new HashMap<>();
        for (List<String> ticket: tickets) {
            String start = ticket.get(0);
            String end = ticket.get(1);
            LinkedList<String> des = new LinkedList<>();
            if (map.containsKey(start)) {
                des = map.get(start);
            }
            des.add(end);
            map.put(start, des);
        }

        map.forEach((k, v) -> Collections.sort(v));

        String head = "JFK";
        DFS(head);
        return ans;
    }

    private void DFS(String head) {
        if (map.containsKey(head)) {
            LinkedList<String> ends = map.get(head);
            while(ends.size() > 0) {
                String next = ends.pollFirst();
                DFS(next);
            }
        }
        ans.addFirst(head);
    }

    public static void main(String[] args) {
        FindItinerarySolution solution = new FindItinerarySolution();
    }
}
