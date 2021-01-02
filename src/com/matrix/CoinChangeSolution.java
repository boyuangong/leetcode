package com.matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boyuangong created on 9/28/20 at 21:11
 */
public class CoinChangeSolution {
    Map<Integer, Integer> map;
    public int coinChange(int[] coins, int amount) {
        // solve use recurssion
        map =  new HashMap<>();
        Arrays.sort(coins);
        int x = coinChangeRecur(coins, amount);
        System.out.println(x);
        return x;
    }

    private int coinChangeRecur(int[] coins, int amount) {
        System.out.println(amount);
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        int min = Integer.MAX_VALUE;
        for (int i = coins.length - 1; i >= 0; i--) {
            int remain = amount - coins[i];
            int remaincount = coinChangeRecur(coins, remain);
            if (remaincount >= 0 && remaincount < min) {
                min = remaincount;
            }
        }

        if (min != Integer.MAX_VALUE) {
            map.put(amount, min + 1);
            return min + 1;
        }
        map.put(amount, -1);
        return -1;
    }

    public static void main(String[] args) {
        CoinChangeSolution solution = new CoinChangeSolution();
        solution.coinChange(new int[]{186,419,83,408}, 6249);
    }
}
