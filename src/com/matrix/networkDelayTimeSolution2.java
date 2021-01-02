package com.matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boyuangong created on 3/14/20 at 13:20
 */
public class networkDelayTimeSolution2 {
    int[] visited;
    Map<Integer, Map> dismaps;

    public int networkDelayTime(int[][] times, int N, int K) {
        // This problem doesn't make it clear whether the direction is 1 to 1.
        // For this solution, we assume the direction is not 1 to 1 and we want to find the shortest.
        this.visited = new int[N + 1];

        // preprocess to have a dist map to avoid go over array several times
        dismaps = new HashMap<>();

        for (int[] time : times) {
            Map<Integer, Integer> curmap = new HashMap<>();
            if (!dismaps.containsKey(time[0])) {
                curmap.put(time[1], time[2]);
            } else {
                curmap = dismaps.get(time[0]);
                curmap.put(time[1], time[2]);
            }
            dismaps.put(time[0], curmap);
        }

        dfs(K, 0);

        int max = 0;
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == 0 && i!=K) {
                return -1;
            }
            max = Math.max(max, visited[i]);
        }
        return max;
    }

    public void dfs(int start, int pre) {
        if (!dismaps.containsKey(start)) {
            // reach to an end
            return;
        }
        Map<Integer, Integer> curmap = dismaps.get(start);
        for (int i : curmap.keySet()) {
            int cur_dist = curmap.get(i) + pre;
            if (visited[i] == 0) {
                visited[i] = cur_dist;
            } else if (visited[i] > cur_dist) {

                visited[i] = cur_dist;
            } else {
                // we already viewd this path no need to perform dfs again
                continue;
            }
            dfs(i, visited[i]);
        }
        System.out.println(Arrays.toString(visited));
    }

    public static void main(String[] args) {
        networkDelayTimeSolution2 solution = new networkDelayTimeSolution2();
        int[][] times = new int[][]{{2,1,1}, {2,3,1}, {3,4,1}};
        int time = solution.networkDelayTime(times, 4, 2);
        System.out.println(time);
    }
}
