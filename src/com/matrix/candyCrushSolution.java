package com.matrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author boyuangong created on 9/24/19 at 21:51
 */
public class candyCrushSolution {
    public static void main(String[] args) {
//        candyCrushSolution candyCrushSolution = new candyCrushSolution();
//        int[][] ans = candyCrushSolution.candyCrush(new int[][]{{110, 5, 112, 113, 114}, {210, 211, 5, 213, 214},
//                {310, 311, 3, 313, 314}, {410, 411, 412, 5, 414}, {5, 1, 512, 3, 3}, {610, 4, 1, 613, 614}, {710, 1, 2, 713, 714},
//                {810, 1, 2, 1, 1}, {1, 1, 2, 2, 2}, {4, 1, 4, 4, 1014}});
//        System.out.println(Arrays.deepToString(ans));
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{2, 3});
        list.remove(new int[]{1, 2});
        System.out.println(list.size());
        ArrayList<Point> list2 = new ArrayList<>();
        list2.add(new Point(1, 2));
        list2.add(new Point(2, 3));
        list2.remove(new Point(1, 2));
        System.out.println(list2.size());
        for(Point point: list2) {
            if (point.x == 1 && point.y == 2) {
                list2.remove(point);
            }
        }
        System.out.println(list2.size());

    }

    public static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] candyCrush(int[][] board) {
        int limit = board.length * board[0].length / 3;
        while (limit > 0) {
            Set<int[]> flag = candy(board);
            if (flag.isEmpty()) {
                break;
            }
            candyClean(flag, board);
            crash(board);
            limit--;
        }
        return board;
    }

    private void crash(int[][] board) {
        for (int j = 0; j < board[0].length; j++) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != 0) {
                    stack.push(board[i][j]);
                }
            }
            for (int i = board.length - 1; i >= 0; i--) {
                if (!stack.empty()) {
                    board[i][j] = stack.pop();
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    private void candyClean(Set<int[]> flag, int[][] board) {
        for (int[] candy : flag) {
            board[candy[0]][candy[1]] = 0;
        }
        flag.clear();
    }

    private Set<int[]> candy(int[][] board) {
        Set<int[]> flag = new HashSet<>();
        int i = 0;
        int j = 0;

        // scan horizontally
        while (i < board.length) {
            while (j < board[1].length - 2) {
                if (board[i][j] != 0) {
                    j = scan_x(board, i, j, flag);
                } else {
                    j++;
                }
            }
            j = 0;
            i++;
        }

        i = 0;
        j = 0;
        // scan vertically
        while (j < board[1].length) {
            while (i < board.length - 2) {
                if (board[i][j] != 0) {
                    i = scan_y(board, i, j, flag);
                } else {
                    i++;
                }
            }
            i = 0;
            j++;
        }
        return flag;
    }

    private int scan_x(int[][] board, int x, int y, Set flag) {
        int count = 1;
        for (int i = y + 1; i < board[0].length; i++) {
            if (board[x][i] == board[x][y]) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 3) {
            int i = 0;
            while (i < count) {
                flag.add(new int[]{x, y + i});
                i++;
            }
        }
        return y + count;
    }

    private int scan_y(int[][] board, int x, int y, Set flag) {
        int count = 1;
        for (int i = x + 1; i < board.length; i++) {
            if (board[i][y] == board[x][y]) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 3) {
            int i = 0;
            while (i < count) {
                if (!flag.contains(new int[]{x + i, y}))
                    flag.add(new int[]{x + i, y});
                i++;
            }
        }
        return x + count;
    }
}
