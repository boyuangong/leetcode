package com.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author boyuangong created on 9/15/20 at 22:30
 */
public class NumberOfAtomsSolution {
    int i;

    public String countOfAtoms(String formula) {
        Map<String, Integer> countmap = countAtomsWithMul(formula, 1);
        List<String> keys = new ArrayList<>(countmap.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String s : keys) {
            sb.append(s);
            if (countmap.get(s) > 1) {
                sb.append(String.valueOf(countmap.get(s)));
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private Map<String, Integer> countAtomsWithMul(String formula, int mul) {
        System.out.println("------------");
        System.out.println(formula);
        System.out.println(mul);
        Map<String, Integer> currentCountMap = new HashMap<>();
        // Get all element in the string
        int left = formula.indexOf("(");
        int right = formula.lastIndexOf(")");
        right += 1;
        // trim the number
        if (right < formula.length() && formula.charAt(right) - '0' < 10) {
            while (right < formula.length() && formula.charAt(right) - '0' < 10) {
                right++;
            }
        }

        for (int i = 0; i < formula.length(); i++) {
            if (i >= left && i < right) {
                continue;
            }
            StringBuilder ele = new StringBuilder();

            if (formula.charAt(i) >= 'A' && formula.charAt(i) <= 'Z') {
                ele.append(formula.charAt(i));
                while (i + 1 < formula.length() && formula.charAt(i + 1) >= 'a' && formula.charAt(i + 1) <= 'z') {
                    i = i + 1;
                    ele.append(formula.charAt(i));
                }
            }
            StringBuilder count = new StringBuilder();
            while (i + 1 < formula.length() && formula.charAt(i + 1) >= '0' && formula.charAt(i + 1) <= '9') {
                i++;
                count.append(formula.charAt(i));
            }

            int curcount = count.length() == 0 ? 1 : Integer.valueOf(count.toString());

            String element = ele.toString();
            if (currentCountMap.containsKey(element)) {
                currentCountMap.put(element, currentCountMap.get(element) + curcount);
            } else {
                currentCountMap.put(ele.toString(), curcount);
            }
        }

        // Then deal with the one in the bracket
        if (left >= 0 && right > 0 && left < right) {
            // get the mul of the next formula
            right = right - 1;
            int nextmul = 1;
            if (!(formula.charAt(right) == ')')) {
                StringBuilder nextmulsb = new StringBuilder();
                while (formula.charAt(right) != ')') {
                    nextmulsb.insert(0, formula.charAt(right));
                    right--;
                }

                nextmul = Integer.valueOf(nextmulsb.toString());
            }
            String nextFormula = formula.substring(left + 1, right);

            Map<String, Integer> nextCountMap = countAtomsWithMul(nextFormula, nextmul);
            // Then we handle the result comming from the count next;
            for (Map.Entry<String, Integer> set : nextCountMap.entrySet()) {
                String key = set.getKey();
                int count = set.getValue();
                if (currentCountMap.containsKey(key)) {
                    currentCountMap.put(key, currentCountMap.get(key) + count);
                } else {
                    currentCountMap.put(key, count);
                }
            }
        }

        // Then we multiply all the keys in the map.
        for (Map.Entry<String, Integer> set : currentCountMap.entrySet()) {
            currentCountMap.put(set.getKey(), set.getValue() * mul);
        }
        return currentCountMap;
    }

    public String countOfAtoms2(String formula) {
        StringBuilder ans = new StringBuilder();
        i = 0;
        Map<String, Integer> count = parse(formula);
        for (String name : count.keySet()) {
            ans.append(name);
            int multiplicity = count.get(name);
            if (multiplicity > 1) ans.append("" + multiplicity);
        }
        System.out.println(ans.toString());
        return new String(ans);
    }

    public Map<String, Integer> parse(String formula) {
        System.out.println("-------------");
        System.out.println(formula);
        int N = formula.length();
        Map<String, Integer> count = new TreeMap();
        while (i < N && formula.charAt(i) != ')') {
            if (formula.charAt(i) == '(') {
                System.out.println(i);
                i++;
                for (Map.Entry<String, Integer> entry : parse(formula).entrySet()) {
                    count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } else {
                int iStart = i++;
                System.out.println(iStart);
                while (i < N && Character.isLowerCase(formula.charAt(i))) i++;
                String name = formula.substring(iStart, i);
                System.out.println(name);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) i++;
                int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                System.out.println(multiplicity);
                count.put(name, count.getOrDefault(name, 0) + multiplicity);
                System.out.println(count.toString());
            }
        }
        System.out.println("break");
        int iStart = ++i;
        System.out.println(iStart);
        while (i < N && Character.isDigit(formula.charAt(i))) i++;
        if (iStart < i) {
            int multiplicity = Integer.parseInt(formula.substring(iStart, i));
            for (String key : count.keySet()) {
                count.put(key, count.get(key) * multiplicity);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfAtomsSolution solution = new NumberOfAtomsSolution();
        solution.countOfAtoms2("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14");
//        solution.countOfAtoms("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14");
//        solution.countOfAtoms("H2O2He3Mg4");
//        solution.countOfAtoms(" (H2O2)");
//        solution.countOfAtoms("Mg(OH)2");
//        solution.countOfAtoms("K4(ON(SO3)2)2");
    }
}
