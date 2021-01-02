package com.matrix;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author boyuangong created on 9/23/20 at 20:54
 */
public class LadderLengthSolution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        // Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            System.out.println(word);
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        boolean contains = false;
        for (String word: wordList) {
            if (word.equals(endWord)) {
                contains = true;
            }
        }
        if (!contains) {
            return 0;
        }

        Map<String, List<String>> graph = new HashMap<>();
        wordList.add(beginWord);
        for (String word: wordList) {
            for(int i=0; i<word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i+1, word.length());
                List<String> list = graph.getOrDefault(key, new ArrayList<>());
                list.add(word);
                graph.put(key, list);
            }
        }

        // now we have the graph
        // do the bfs to search for the endword
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        List<String> visited = new ArrayList<>();
        while(!q.isEmpty()) {
            Pair<String, Integer> curroot = q.poll();
            String rootWord = curroot.getKey();
            visited.add(rootWord);
            System.out.println(rootWord);
            for (int i=0; i<rootWord.length(); i++) {
                String curkey = rootWord.substring(0, i) + "*" + rootWord.substring(i+1);
                if (graph.containsKey(curkey)) {
                    int depth = curroot.getValue();
                    List<String> children = graph.get(curkey);
                    for (String child: children) {
                        if (child.equals(endWord)) {
                            return depth + 1;
                        }
                        if (!visited.contains(child)) {
                            q.add(new Pair(child, depth + 1));
                        }
                    }
                }
            }
        }
        return 0;
    }
}
