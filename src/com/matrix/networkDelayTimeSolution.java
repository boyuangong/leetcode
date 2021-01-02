package com.matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.Arrays;

/**
 * @author boyuangong created on 8/27/19 at 21:18
 */
public class networkDelayTimeSolution {
    public int networkDelayTime(int[][] times, int N, int K) {

        Set<int[]>[] graph = new HashSet[N+1];

        for(int i=0; i<N+1; i++) {
            graph[i] = new HashSet<int[]>();
        }

        for(int[] time: times) {
            graph[time[0]].add(time);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        Comparator<int[]> timeArrayComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[2] - a2[2];
            }
        };
        PriorityQueue queue = new PriorityQueue<int[]>(times.length, timeArrayComparator);

        // first build the queue with the start node
        for(int[] time: graph[K]) {
            queue.add(time);
        }
        while(queue.peek() != null) {
            // go through the childs

            int[] curTime = (int[])queue.poll();
            int curNode = curTime[1];
            int path = curTime[2];
            if(!map.containsKey(curNode)){
                map.put(curNode, path);
                max = Math.max(path, max);
            }
            if(map.size() == N-1) {
                // we went through all nodes
                break;
            }
            for(int[] time: graph[curNode]) {
                if(time[0] == curNode && time[1] != K) {
                    time[0] = K;
                    time[2] = path + time[2];
                    queue.add(time);
                }
            }
        }
        if(map.size() < N-1) {
            return -1;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new int[][]{{1,2,3}, {2,3,4}}.length);
        networkDelayTimeSolution solution = new networkDelayTimeSolution();
        int[][] times = new int[][]{{1,2,1}, {2,3,2}, {1,3,1}};
        int time = solution.networkDelayTime(times, 3, 2);
        System.out.println(time);
    }

}
