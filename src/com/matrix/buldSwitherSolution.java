package com.matrix;

/**
 * @author boyuangong created on 9/3/19 at 23:32
 */
public class buldSwitherSolution {
    public int bulbSwithcher(int n) {
        int k = 0;
        for(int i=1; i<=n; i++) {
            if(Math.sqrt(i) == Math.floor(Math.sqrt(i))) {
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        buldSwitherSolution solution = new buldSwitherSolution();
        System.out.println(solution.bulbSwithcher(9));
    }
}
