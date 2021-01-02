package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author boyuangong created on 9/22/19 at 21:05
 */
public class deckRevealedIncreasingSolution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=deck.length-1; i>=0; i--) {
            if(list.size() <= 1) {
                list.add(0, deck[i]);
            } else {
                swap(list);
                list.add(0, deck[i]);
            }
//            System.out.println(list.toString());
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public void swap(ArrayList list) {
        int tmp = (int)list.get(list.size()-1);
        list.add(0, tmp);
        list.remove(list.size()-1);
    }

    public int[] deckRevealedIncreasing2(int[] deck) {
        Arrays.sort(deck);
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=deck.length-1; i>=0; i--) {
            if(list.size() <= 1) {
                list.addFirst(deck[i]);
            } else {
                list.addFirst(list.removeLast());
                list.addFirst(deck[i]);
//                for (Integer aList : list) {
//                    System.out.println(aList);
//                }
//                System.out.println("---------");
            }
        }
        int[] ans = new int[deck.length];
        for(int i=0; i<=deck.length-1; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        deckRevealedIncreasingSolution solution = new deckRevealedIncreasingSolution();
        System.out.println(Arrays.toString(solution.deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7})));
        System.out.println(Arrays.toString(solution.deckRevealedIncreasing2(new int[]{17,13,11,2,3,5,7})));
        System.out.println(4 >> 1);
    }
}
