package com.matrix;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author boyuangong created on 9/23/20 at 22:35
 */
public class LongestIncreasingPathInAMatrixSolution {
    Set<Pair<Integer, List<Integer>>> visited;
    int[][] depths;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        depths = new int[m][n];
        Arrays.fill(depths, 0);

        PriorityQueue<Pair<Integer, List<Integer>>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                pq.add(new Pair(matrix[i][j], Arrays.asList(i, j)));
            }
        }

        // visited only controls the element pop out from pq
        visited = new HashSet<>();

        while (!pq.isEmpty()) {
            Pair<Integer, List<Integer>> curpoint = pq.poll();
            if (!visited.contains(curpoint)) {
                dfs(curpoint, 1, matrix);
            }
        }

        int max = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                max = Math.max(max, depths[i][j]);
            }
        }
        return max;
    }

    private void dfs(Pair<Integer, List<Integer>> cur, int depth, int[][] matrix) {
        int val = cur.getKey();
        int x = cur.getValue().get(0);
        int y = cur.getValue().get(1);
        int m = matrix.length;
        int n = matrix[0].length;

        depths[x][y] = depth;
        visited.add(cur);
        if (x+1 < m) {
            gonext(x + 1, y, depth, matrix, val);
        }

        if (x-1 >= 0) {
            gonext(x - 1, y, depth, matrix, val);
        }

        if (y+1 < n) {
            gonext(x, y + 1, depth, matrix, val);
        }

        if (y-1 >= 0) {
            gonext(x, y - 1, depth, matrix, val);
        }
    }

    private void gonext(int x, int y, int depth, int[][] matrix, int val) {
        int next = matrix[x][y];
        if (next > val && depths[x][y] < depth + 1) {
            dfs(new Pair(next, Arrays.asList(x, y)), depth+1, matrix);
        }
    }
}
