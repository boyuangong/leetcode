package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author boyuangong created on 9/2/20 at 22:20
 */
public class CourseSchedule2Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] dimension = new int[numCourses];
        Map<Integer, List<Integer>> relations = new HashMap<>();
        Queue<Integer> ansq = new LinkedList<>();

        Arrays.fill(dimension, 0);
        for (int i=0; i<prerequisites.length; i++) {
            int a1 = prerequisites[i][0];
            int a2 = prerequisites[i][1];
            List<Integer> list = new ArrayList<>();
            if(relations.containsKey(a2)) {
                list = relations.get(a2);
            }
            list.add(a1);
            relations.put(a2, list);
            dimension[a1] += 1;
        }

        Queue<Integer> zeroq = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (dimension[i] == 0) {
                zeroq.add(i);
            }
        }

        // get the dimension 0, reduce the dimension on their child node
        // get the dimension 0 nodes again.
        while(!zeroq.isEmpty()) {
            int curN = zeroq.poll();
            ansq.add(curN);
            dimension[curN] = dimension[curN] - 1;
            if (!relations.containsKey(curN)) continue;
            List<Integer> nextNs = relations.get(curN);
            for (Integer nextN : nextNs) {
                dimension[nextN] = dimension[nextN] - 1;
                if (dimension[nextN] == 0) {
                    zeroq.add(nextN);
                }
            }
        }

        if (ansq.size() != numCourses) {
            return new int[]{};
        }

        int[] ans = new int[ansq.size()];
        for (int i=0; i<ans.length; i++) {
            ans[i] = ansq.poll();
        }

        return ans;
    }

    public static void main(String[] args) {
        CourseSchedule2Solution solution = new CourseSchedule2Solution();
        solution.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}});
        solution.findOrder(1, new int[][]{});
        solution.findOrder(3, new int[][]{{1,0},{1,2},{0,1}});
    }
}
