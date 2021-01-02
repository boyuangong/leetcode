package com.matrix;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 11/21/20 at 17:45
 */
public class CouplesHoldingHandsSolution {

    int numSwaps;
    Map<Integer, Integer> numPairIndexMap;
    List<Pair<Integer, Integer>> list;
    int[] visited;

    public int minSwapsCouples(int[] row) {
        numSwaps = 0;
        list = new ArrayList<>();
        numPairIndexMap = new HashMap<>();
        int n = row.length;
        visited = new int[n / 2];
        // preprocessing date: 1. Create each two numbers as Pair
        // 2. Each num in pair has the same corresponding Pair index
        for (int i = 0; i <= n / 2 - 1; i++) {
            int a1 = row[2 * i];
            int a2 = row[2 * i + 1];
            list.add(new Pair<>(a1, a2));

            // put the num -> pair index in the map for tracking
            numPairIndexMap.put(a1, i);
            numPairIndexMap.put(a2, i);
        }

        // enumberate the Pair list
        for (int i = 0; i < list.size(); i++) {
            if (visited[i] == 1) continue;
            Pair<Integer, Integer> curPair = list.get(i);
            if (validPair(curPair)) continue;
            searchPair(curPair, i);
        }

        System.out.println(numSwaps);
        return numSwaps;
    }

    private boolean validPair(Pair<Integer, Integer> curPair) {
        int a1 = curPair.getKey();
        int a2 = curPair.getValue();
        if (a1 % 2 == 0) {
            return a2 == a1 + 1;
        } else {
            // a1 is the odd number
            // require a2 is even number and a2+1 = a1
            return a2 + 1 == a1;
        }
    }

    private void searchPair(Pair<Integer, Integer> p, int curIndex) {
        // p is not valid pair
        numSwaps++;
        int a1 = p.getKey();
        int a2 = p.getValue();
        int pairsearch = a1 % 2 == 0 ? a1 + 1 : a1 - 1;
        int searchIndex = numPairIndexMap.get(pairsearch);

        Pair<Integer, Integer> sp = list.get(searchIndex);
        int sa1 = sp.getKey();
        int sa2 = sp.getValue();
        if (sa1 == pairsearch) {
            // we swap the a2 and sa1
            sp = new Pair<>(a2, sa2);
            list.set(curIndex, new Pair<>(a1, sa1));
            list.set(searchIndex, sp);
        } else if (sa2 == pairsearch) {
            sp = new Pair<>(sa1, a2);
            list.set(curIndex, new Pair<>(a1, sa2));
            list.set(searchIndex, sp);
        }

        if (!validPair(sp)) {
            searchPair(sp, searchIndex);
        }
    }

    public static void main(String[] args) {
        CouplesHoldingHandsSolution solution = new CouplesHoldingHandsSolution();
        solution.minSwapsCouples(new int[]{0, 2, 1, 3});
    }
}
