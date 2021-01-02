package com.matrix;

import java.util.Arrays;

import static java.lang.Boolean.FALSE;

/**
 * @author boyuangong created on 8/14/19 at 23:46
 */
public class numberOfIslands {
    private char[][] grid;
    private Boolean[][] searched;
    public int numIslands(char[][] grid) {
        if(grid == null){
            return 0;
        }
        this.grid = grid;
        this.searched = new Boolean[grid.length][grid[0].length];
        int num = 0;
        this.searched = new Boolean[grid.length][grid[0].length];
        for(Boolean[] booleans: this.searched) {
            Arrays.fill(booleans, FALSE);
        }
        System.out.println(Arrays.toString(this.searched[0]));
        for(int i=0; i<grid.length;  i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == '1' && !this.searched[i][j]) {
                    // we discover a new island
                    num++;
                    // search every corner of the island
                    bfs(i, j);
                }
            }
        }
        return num;
    }

    private void bfs(int i, int j) {
        if(this.searched[i][j]) {
            // this is necessary because the bfs will go back and forth.
            return;
        }
        if(this.grid[i][j] == '0'){
            return;
        }
        // first mark the current point as searched
        this.searched[i][j] = true;
        if(i>0) {
            bfs(i-1, j);
        }
        if(i<this.grid.length - 1) {
            bfs(i+1, j);
        }
        if(j>0) {
            bfs(i, j-1);
        }
        if(j<this.grid[0].length - 1) {
            bfs(i, j+1);
        }
    }
}
