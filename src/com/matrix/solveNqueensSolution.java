package com.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 5/31/20 at 12:10
 */
public class solveNqueensSolution {

    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + this.x + " ," + this.y + ")";
        }
    }

    List<List<String>> ans;
    List<List<Point>> pointsList;

    public List<List<String>> solveNQueens(int n) {
        //Backtracking
        ans = new ArrayList<>();
        pointsList = new ArrayList<>();
        backTracking(new ArrayList<>(), new HashMap<>(), n, 0);
        System.out.println(pointsList.toString());
        convertPointsToList(n);
        printBoards(ans);
        return ans;
    }

    private void backTracking(List<Point> aggregatePoints, Map<Integer, List<Integer>> grayLocationMap, int n, int col) {

        System.out.println("current col: " + col);
        // start with the left upper most point
        // track the point vertically
        for (int i=0; i<n; i++) {
            System.out.println("current col: " + col);
            System.out.println("current i: " + i);
            System.out.println("current grayLocationMap:" + grayLocationMap.toString());
            if (grayLocationMap.containsKey(col) && grayLocationMap.get(col).contains(i)) {
                continue;
            }

            Point curPoint = new Point(i, col);

            List<Point> curPoints = new ArrayList<>(aggregatePoints);

            curPoints.add(curPoint);

            if (col == n-1) {
                pointsList.add(curPoints);
                continue;
            }

            System.out.println(curPoints);

            Map<Integer, List<Integer>> curGrayLocationMap = copy(grayLocationMap);

            // find all the greyPoints the current point introduced
            for(int j=0; j<n; j++) {

                List<Integer> listRow = new ArrayList<>();

                if (curGrayLocationMap.containsKey(j)) {
                    listRow = curGrayLocationMap.get(j);
                }
                listRow.add(i);
                curGrayLocationMap.put(j, listRow);


                List<Integer> listCol = new ArrayList<>();
                if (curGrayLocationMap.containsKey(col)) {
                    listCol = curGrayLocationMap.get(col);
                }
                listCol.add(j);
                curGrayLocationMap.put(col, listCol);

            }

            int x = i;
            int y = col;
            while (x<n && y<n) {
                x ++;
                y ++;
                List<Integer> list = new ArrayList<>();
                if (curGrayLocationMap.containsKey(y)) {
                    list = curGrayLocationMap.get(y);
                }
                list.add(x);
                curGrayLocationMap.put(y, list);
            }

            x = i;
            y = col;

            while (x+1<n && y-1>=0) {
                x ++;
                y --;
                List<Integer> list = new ArrayList<>();
                if (curGrayLocationMap.containsKey(y)) {
                    list = curGrayLocationMap.get(y);
                }
                list.add(x);
                curGrayLocationMap.put(y, list);
            }

            x = i;
            y = col;
            while (x-1>=0 && y+1<n) {
                x --;
                y ++;
                List<Integer> list = new ArrayList<>();
                if (curGrayLocationMap.containsKey(y)) {
                    list = curGrayLocationMap.get(y);
                }
                list.add(x);
                curGrayLocationMap.put(y, list);
            }

            x = i;
            y = col;
            while (x-1>=0 && y-1>=0) {
                x --;
                y --;
                List<Integer> list = new ArrayList<>();
                if (curGrayLocationMap.containsKey(y)) {
                    list = curGrayLocationMap.get(y);
                }
                list.add(x);
                curGrayLocationMap.put(y, list);
            }
            System.out.println("current map for point " + i + " " + col + curGrayLocationMap + " for point ");

            backTracking(curPoints, curGrayLocationMap, n, col + 1);
        }
    }

    private void convertPointsToList(int n) {
        for(List<Point> points: pointsList) {
            List<String> curList = new ArrayList<>();
            for (int i=0; i<n; i++) {
                String cur = "";
                for (int j=0; j<n; j++) {
                    if (queenPoint(i, j, points)) {
                        cur += 'Q';
                    } else {
                        cur += "X";
                    }
                }
                curList.add(cur);
            }
            ans.add(curList);
        }
    }

    private boolean queenPoint(int i, int j, List<Point> points) {
        return points.stream().anyMatch(point -> point.x == i && point.y == j);
    }

    private void printBoards(List<List<String>> stringsList) {
        int i = 0;
        for (List<String> strings: stringsList) {
            System.out.println(String.format("Solution %d ---------", i));
            for (String str: strings) {
                System.out.println("|" + str + "|");
            }
            i++;
        }
    }

    private HashMap<Integer, List<Integer>> copy(Map<Integer, List<Integer>> original) {
        HashMap<Integer, List<Integer>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : original.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }

    public static void main(String[] args) {
        solveNqueensSolution solveNqueensSolution = new solveNqueensSolution();
        solveNqueensSolution.solveNQueens(9);
    }
}
