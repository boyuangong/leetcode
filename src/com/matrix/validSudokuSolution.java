package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 2/12/20 at 00:55
 */
public class validSudokuSolution {
    public boolean isValidSudoku(char[][] board) {
        // judge from the point distance
        Map<Character, List<Point>> map = new HashMap<>();
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.println("current i " + i + "  current j " + j);
                char c = board[i][j];
                System.out.println(map.toString());
                if (c != '.') {
                    if (!map.containsKey(c)) {
                        List<Point> new_list = new ArrayList<>();
                        new_list.add(new Point(i, j));
                        map.put(c, new_list);
                    } else {
                        List<Point> list = map.get(c);
                        Point p = new Point(i, j);
                        if (validCheck(list, p)) {
                            list.add(p);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean validCheck(List<Point> list, Point p) {
        for (Point p_c : list) {
            Boolean valid = true;
            if (p_c.x == p.x) {
                System.out.println("equal x " + p.x);
                valid = false;
            }
            if (p_c.y == p.y) {
                System.out.println("equal y " + p.y);
                valid = false;
            }
            if (Math.abs(p_c.x - p.x) <= 2 && Math.abs(p_c.y - p.y) <= 2 && belongToSameSubBox(p_c, p)) {
                System.out.println("x, y in same sub box " + p_c.x + "  " + p_c.y);
                System.out.println("x, y is " + p.x + " " + p.y);
                valid = false;
            }
            if (!valid) {
                return valid;
            }
        }
        return true;
    }

    public boolean belongToSameSubBox(Point p_c, Point p) {
        int p_c_x = p_c.x / 3;
        int p_c_y = p_c.y / 3;
        int p_x = p.x / 3;
        int p_y = p.y / 3;
        System.out.println(" " + p_c_x + " " + p_c_y + " "  + p_x + " "  + p_y);
        System.out.println(p_c_x == p_x && p_x == p_y);
        return p_c_x == p_x && p_c_y == p_y;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x: " + this.x + ", y: " + y;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        char[][] board2 = new char[][]{
                {'.','.','5','.','.','.','.','.','6'},
                {'.','.','.','.','1','4','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','9','2','.','.'},
                {'5','.','.','.','.','2','.','.','.'},
                {'.','.','.','.','.','.','.','3','.'},
                {'.','.','.','5','4','.','.','.','.'},
                {'3','.','.','.','.','.','4','2','.'},
                {'.','.','.','2','7','.','6','.','.'}};
//        System.out.println(4/3);
//        System.out.println(5/3);
//        System.out.println(3/3);
//        System.out.println(6/3);
//        System.out.println(new validSudokuSolution().isValidSudoku(board));
        System.out.println(new validSudokuSolution().isValidSudoku(board2));
    }
}
