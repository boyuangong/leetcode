package com.matrix;

import java.util.Arrays;

/**
 * @author boyuangong created on 9/3/19 at 19:23
 */
public class kClosestPointsSolution {

    public int[][] kClosestPoints(int[][] points, int K) {
        if(points.length<K) {
            return points;
        }
        int[][] ans = new int[K][points[0].length];

        sortPoints(points);
        System.out.println(Arrays.deepToString(points));
        for(int i=0; i<K; i++) {
            ans[i] = points[i];
        }
        return ans;
    }

    public void sortPoints(int[][] points) {
        quickSortPoints(points, 0, points.length-1);
    }

    public void quickSortPoints(int[][] points, int start, int end){
        if(start<end) {
            int mid = partition(points, start, end);
            System.out.println(mid);
            System.out.println(start);
            System.out.println(end);
            System.out.println("----------");
            System.out.println(Arrays.deepToString(points));
            System.out.println("----------");
            quickSortPoints(points, start, mid - 1);
            quickSortPoints(points, mid+1, end);

        }
    }

    public int partition(int[][] points, int start, int end) {
        double dist_end = Dist(points[end]);
        int first = start - 1;
        int second = start;
        while(second<=end-1) {
            if(Dist(points[second]) <= dist_end) {
                first ++;
                exchange(points, first, second);
            }
            second++;
        }

        exchange(points, first+1, end);
        return first+1;
    }

    public double Dist(int[] point) {
        return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }

    public void exchange(int[][] points, int first, int second) {
        int[] tmp = points[first];
        points[first] = points[second];
        points[second] = tmp;
    }

    public void printDistance(int[][] points) {
        for(int i=0;i<points.length;i++) {
            System.out.println(Dist(points[i]));
        }
    }

    public static void main(String[] args) {
        kClosestPointsSolution solution = new kClosestPointsSolution();
        int[][] points = new int[][]{{-95,76},{17,7},{-55,-58},{53,20},{-69,-8},{-57,87},{-2,-42},{-10,-87},{-36,-57},{97,-39},{97,49}};
        solution.printDistance(points);
        System.out.println(Arrays.deepToString(solution.kClosestPoints(points, 5)));
    }


}
