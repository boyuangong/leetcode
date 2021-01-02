package com.matrix;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author boyuangong created on 8/28/19 at 00:41
 */
public class isStrobogrammaticSolution {
    public boolean isStrobogrammatic(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        char[] schar = s.toCharArray();

        HashSet<Character> set = new HashSet<>();
        set.add('0');
        set.add('1');
        set.add('8');
        if(s.toCharArray().length%2 != 0) {
            if(!set.contains(schar[schar.length/2])) {
                return false;
            }
        }
        for(int i=0; i<=schar.length/2-1; i++) {
            if(!map.containsKey(schar[i])) {
                return false;
            }
            if(map.get(schar[i]) != schar[schar.length-1-i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        isStrobogrammaticSolution stringSolution = new isStrobogrammaticSolution();
        System.out.println(stringSolution.isStrobogrammatic("10"));
    }
}
