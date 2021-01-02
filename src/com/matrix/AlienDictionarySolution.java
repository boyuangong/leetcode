package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author boyuangong created on 9/6/20 at 14:44
 */

public class AlienDictionarySolution {
    List<List<Character>> relations;
    List<Character> chars;
    public String alienOrder(String[] words) {
        // 1. build 1v1 relationship
        // 2. build graph
        // 3. sort graph

        for (int i=0; i<words.length-1; i++) {
            System.out.println(words[i+1].startsWith(words[i]));
            if (words[i+1].length() < words[i].length() && words[i].startsWith(words[i+1])) {
                return "";
            }
        }
        chars = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // build relationship
        List<String> wordslist = Arrays.asList(words);
        relations = new ArrayList<>();
        buildRelationship(wordslist);
        System.out.println(relations.toString());
        // build graph
        Map<Character, Integer> dimensions = new HashMap<>();
        Map<Character, List<Character>> childs = new HashMap<>();
        for (List<Character> list: relations) {
            char c1 = list.get(0);
            char c2 = list.get(1);
            dimensions.put(c2, dimensions.getOrDefault(c2, 0) + 1);

            List<Character> curlist = childs.containsKey(c1)? childs.get(c1) : new ArrayList<>();
            curlist.add(c2);
            childs.put(c1, curlist);
        }

        for (char c: chars) {
            if (!dimensions.containsKey(c)) {
                dimensions.put(c, 0);
            }
        }


        // sort graph

        Queue<Character> zerod = new LinkedList<>();

        dimensions.entrySet().stream().forEach(pair -> {
            if (pair.getValue() == 0) {
                zerod.add(pair.getKey());
            }
        });
        System.out.println(dimensions.toString());
        System.out.println(zerod.toString());

        while(!zerod.isEmpty()) {
            char cur = zerod.poll();
            sb.append(cur);
            dimensions.put(cur, dimensions.get(cur) - 1);
            if (childs.containsKey(cur) && !childs.get(cur).isEmpty()) {
                List<Character> child = childs.get(cur);

                for (char c: child) {
                    dimensions.put(c, dimensions.get(c) - 1);
                    if(dimensions.get(c) == 0) {
                        zerod.add(c);
                    }
                }
            }

        }

        if (sb.toString().length() < chars.size()) {
            return "";
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    private void buildRelationship(List<String> wordslist) {
        if (wordslist.size() == 0) {
            // don't have any relation information if wordslist only contains one word or fewer
            return;
        }

        if (wordslist.size() == 1) {
            for (char c: wordslist.get(0).toCharArray()) {
                if(!chars.contains(c)) {
                    chars.add(c);
                }
            }
        }

        Map<Character, List<String>> subwordsMap = new HashMap<>();

        List<Character> order = new LinkedList<>();
        for (String word: wordslist) {
            char c = word.charAt(0);
            order.add(c);
            if (!chars.contains(c)) {
                chars.add(c);
            }

            if (word.length() > 1) {
                String subwords = word.substring(1);
                List<String> list = new ArrayList<>();
                if (subwordsMap.containsKey(c)) {
                    list = subwordsMap.get(c);
                }
                list.add(subwords);
                subwordsMap.put(c, list);
            }
        }

        // remove the repeated character

        List<Character> neworder = new ArrayList<>();
        for (int i=0; i<order.size()-1; i++) {
            if (order.get(i) != order.get(i+1)) {
                neworder.add(order.get(i));
            }
        }
        neworder.add(order.get(order.size() - 1));
        // now we build 1v1 relationship from the order list
        // the 1v1 relationship should be first -> second
        for (int i=1; i<neworder.size(); i++) {
            List<Character> curlist = new ArrayList<>();
            curlist.add(neworder.get(i-1));
            curlist.add(neworder.get(i));
            relations.add(curlist);
        }


        // then we recursive in the subwordsMap to build further relations
        for(List<String> list: subwordsMap.values()) {
            buildRelationship(list);
        }
    }

    public static void main(String[] args) {
        AlienDictionarySolution solution = new AlienDictionarySolution();
//        solution.alienOrder(new String[]{
//                "wrt",
//                "wrf",
//                "er",
//                "ett",
//                "rftt"
//        });
//
//        solution.alienOrder(new String[]{
//                "ab",
//                "adc"
//        });

        String s = solution.alienOrder(new String[]{"ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby"});
        System.out.println("result");
        System.out.println(s);
    }
}
