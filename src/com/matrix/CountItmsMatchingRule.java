package com.matrix;

import java.util.List;

/**
 * @author boyuangong created on 4/15/21 at 23:31
 */
public class CountItmsMatchingRule {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int index = 0;
        if (ruleKey.equals("color")) {
            index = 1;
        } else if (ruleKey.equals("name")) {
            index = 2;
        }

        int finalIndex = index;

        return (int) items.stream()
                .filter(i -> i.get(finalIndex).equals(ruleValue)).count();
    }
}
