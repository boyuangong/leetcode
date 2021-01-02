package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 8/22/20 at 19:09
 */
public class WorldSearchSolution {
    private boolean match = false;
    private String word;
    private char[][] board;
    public boolean exist(char[][] board, String word) {
        this.word = word;
        this.board = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    List<List<Integer>> visited = new ArrayList<>();
                    visited.add(Arrays.asList(i, j));
                    backtracking(1, visited);
                    if (match) {
                        return true;
                    }
                }
            }
        }
        return match;
    }

    private void backtracking(int nextid, List<List<Integer>> visited) {
        List<Integer> lastVisited = visited.get(visited.size() - 1);
        int i = lastVisited.get(0);
        int j = lastVisited.get(1);
        if (nextid == word.length()) {
            match = true;
            return;
        }
        char next = word.charAt(nextid);
//        System.out.println(nextid);
        System.out.println(i);
        System.out.println(j);
//        System.out.println(visited);
//        System.out.println("-----------------");

        if (i+1 < board.length && board[i+1][j] == next) {
            List<Integer> curVisited = Arrays.asList(i+1, j);
            if (!visited.contains(curVisited)) {
                visited.add(curVisited);
                backtracking(nextid + 1, visited);
                visited.remove(visited.size() - 1);
            }
        }

        if (match) {
            return;
        }

        if (j+1 < board[0].length && board[i][j+1] == next) {
            List<Integer> curVisited = Arrays.asList(i, j+1);
            if (!visited.contains(curVisited)) {
                visited.add(curVisited);
                backtracking(nextid + 1, visited);
                visited.remove(visited.size() - 1);
            }
        }

        if (match) {
            return;
        }

        if (i-1>=0 && board[i-1][j] == next) {
            List<Integer> curVisited = Arrays.asList(i-1, j);
            if (!visited.contains(curVisited)) {
                visited.add(curVisited);
                backtracking(nextid + 1, visited);
                visited.remove(visited.size() - 1);
            }
        }

        if (match) {
            return;
        }

        if (j-1>=0 && board[i][j-1] == next) {
            List<Integer> curVisited = Arrays.asList(i, j-1);
            if (!visited.contains(curVisited)) {
                visited.add(curVisited);
                backtracking(nextid + 1, visited);
                visited.remove(visited.size() - 1);
            }
        }
    }

    public boolean exist2(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (dfs(i, j, board, words, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x,
                        int y,
                        char[][] board,
                        char[] word,
                        int cur_len) {
        System.out.println(x);
        System.out.println(y);
        if (cur_len == word.length) {
            return true;
        }

        if (!canGo(x, y, board)) {
            return false;
        }

        if (word[cur_len] != board[x][y]) {
            return false;
        }

        char tmp = board[x][y];
        board[x][y] = '0';
        boolean flag = dfs(x + 1, y, board, word, cur_len + 1)
                || dfs(x, y + 1, board, word, cur_len + 1)
                || dfs(x - 1, y, board, word, cur_len + 1)
                || dfs(x, y - 1, board, word, cur_len + 1);
        board[x][y] = tmp;
        return flag;
    }


    private boolean canGo(int x, int y, char[][] board) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (board[x][y] == '0') {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        WorldSearchSolution solution = new WorldSearchSolution();
        char[][] board = new char[][]
                {{'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};

        char[][] board2 = new char[][]{
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}};

        boolean exist1 = solution.exist(board2, "AACD");
        System.out.println(exist1);
        System.out.println("-------------------------------------exit2");
        boolean exist2 = solution.exist2(board2, "AACD");
        System.out.println(exist2);
    }
}
