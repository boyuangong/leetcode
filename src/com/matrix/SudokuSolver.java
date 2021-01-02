package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author boyuangong created on 8/17/20 at 16:01
 */
public class SudokuSolver {
    HashMap<Integer, List<Character>> rowc = new HashMap<>();
    HashMap<Integer, List<Character>> colc = new HashMap<>();
    HashMap<Integer, List<Character>> boxc = new HashMap<>();

    boolean solved = false;

    public void solveSudoku(char[][] board) {
        int n = board.length;
        initialCs(n);
        System.out.println(rowc.toString());
        System.out.println(colc.toString());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    putCs(i, j, board);
                }
            }
        }

        backtracking(0, board);
    }

    private void initialCs(int n) {
        initialC(n, rowc);
        initialC(n, colc);
        initialC(n, boxc);
    }

    private void initialC(int n, HashMap<Integer, List<Character>> map) {
        for (int i = 0; i < n; i++) {
            List<Character> list = new ArrayList<>();
            map.put(i, list);
        }
    }

    private void putCs(int i, int j, char[][] board) {
        putC(i, board[i][j], rowc);
        putC(j, board[i][j], colc);
        int position = i / 3 * 3 + j / 3;
        putC(position, board[i][j], boxc);
    }

    private void putC(int index, Character c, HashMap<Integer, List<Character>> map) {
        List<Character> listcur = map.get(index);
        listcur.add(c);
        map.put(index, listcur);
    }

    private void removeCs(int i, int j, char[][] board) {
        removeC(i, board[i][j], rowc);
        removeC(j, board[i][j], colc);
        int position = i / 3 * 3 + j / 3;
        removeC(position, board[i][j], boxc);
    }

    private void removeC(int index, Character c, HashMap<Integer, List<Character>> map) {
        List<Character> listcur = map.get(index);
        listcur.remove(c);
        map.put(index, listcur);
    }

    private void backtracking(int ind, char[][] board) {
        int n = board.length;
        if (ind == n * n) {
            solved = true;
            return;
        }
        int i = ind / n;
        int j = ind % n;
        if (board[i][j] != '.') {
            int newInd = ind + 1;
            backtracking(newInd, board);
            return;
        }

        for (int k = 1; k <= n; k++) {
            char cur = (char) (k + '0');
            if (!Constrained(cur, i, j)) {
                board[i][j] = cur;
                putCs(i, j, board);
                int newInd = ind + 1;
                backtracking(newInd, board);
                if (!solved) {
                    removeCs(i, j, board);
                    board[i][j] = '.';
                }
            }
        }
    }

    private boolean Constrained(Character c, int i, int j) {
        int position = i / 3 * 3 + j / 3;
        return rowc.get(i).contains(c) || colc.get(j).contains(c) || boxc.get(position).contains(c);
    }

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        char[][] board = new char[][]
                {{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                        {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                        {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                        {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                        {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                        {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                        {'.', '.', '.', '2', '7', '5', '9', '.', '.'}};
        solver.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}
