package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author boyuangong created on 8/29/20 at 16:06
 */
public class MHTSolution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            return Arrays.asList(0);
        }
        // format the node
        for (int i = 0; i<edges.length; i++) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            int first = edges[i][0];
            int second = edges[i][1];
            if (map.containsKey(first)) {
                list1 = map.get(first);
            }
            list1.add(second);
            map.put(first, list1);

            if (map.containsKey(second)) {
                list2 = map.get(second);
            }
            list2.add(first);
            map.put(second, list2);
        }

        int[] adjs = new int[n];

        Queue<Integer> q = new LinkedList<>();
        // start from all leaves
        for (int i=0; i<n; i++) {
            int size = map.get(i).size();
            adjs[i] = size;
            if (size == 1) {
                q.add(i);
            }
        }

        int V = n;
        while(V > 2) {
            int size = q.size();
            int i = 0;
            while(i < size) {
                i ++;
                V -= 1;
                int curleave = q.poll();
                List<Integer> curadj = map.get(curleave);
                for (int j: curadj) {
                    System.out.println(j);
                    adjs[j] = adjs[j] - 1;
                    if (adjs[j] == 1) {

                        q.add(j);
                    }
                }
            }
        }

        while(!q.isEmpty()) {
            ans.add(q.poll());
        }
        System.out.println(ans.toString());
        return ans;
    }


    public static void main(String[] args) {
        MHTSolution solution = new MHTSolution();
        solution.findMinHeightTrees(6, new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}});
    }
}
