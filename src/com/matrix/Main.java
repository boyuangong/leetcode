package com.matrix;

public class Main {

    public static void main(String[] args) {
	// write your code here
        numberOfIslands number = new numberOfIslands();
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(number.numIslands(null));
    }
}
