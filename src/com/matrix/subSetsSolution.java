package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author boyuangong created on 9/29/19 at 15:48
 */
public class subSetsSolution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int i=0; i<nums.length; i++) {
            List<List<Integer>> tmplist = new ArrayList<>();
            for (List<Integer> list: lists) {
                List<Integer> newlist = new ArrayList<>(list);
                newlist.add(nums[i]);
                tmplist.add(newlist);
            }
            lists.addAll(tmplist);
        }
        return lists;
    }

    public static void main(String[] args) {
        subSetsSolution subSetsSolution = new subSetsSolution();
        List<List<Integer>> lists = subSetsSolution.subsets(new int[]{1,2,3});
        System.out.println(lists.toString());
        for (List<Integer> list: lists) {
            System.out.println(list.toString());
        }
        Collections.reverseOrder();
    }
}
