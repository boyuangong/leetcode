package com.matrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author boyuangong created on 1/23/21 at 17:45
 */
public class CanPermutePalindromSolution {
    public boolean canPermutePalindrome(String s) {
        int l = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int odds = 0;
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            //check how many odd count in the map
            if (entry.getValue() % 2 != 0) {
                odds ++;
            }
        }

        if (l % 2 == 0) {
            // even length string, shouldn't have any odd character
            return odds == 0;
        } else {
            // odd length string, we can only have one odd number character
            return odds <= 1;
        }
    }
}
