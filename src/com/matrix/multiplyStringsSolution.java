package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 7/6/20 at 00:24
 */
public class multiplyStringsSolution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        //ensure chars1 length > chars2's
        if (chars1.length < chars2.length) {
            char[] temp = chars1;
            chars1 = chars2;
            chars2 = temp;
        }
        List<String> prods = new ArrayList<>();
        for (int i=0; i<chars2.length; i++) {
            prods.add(getProd(chars1, chars2[chars2.length-1-i], i));
        }
        return sumAllProds(prods);
    }

    private String getProd(char[] chars, Character cur, int k) {
        int curInt = Integer.valueOf(String.valueOf(cur));
        String prod = "";
        int pre = 0;
        for (int i=0; i<chars.length;i++) {
            int id = chars.length - 1 - i;
            int j = Integer.valueOf(String.valueOf(chars[id]));
            int curProd = curInt * j + pre;
            pre = curProd/10;
            prod = String.valueOf(curProd%10) + prod;
        }
        if (pre > 0) {
            prod = String.valueOf(pre) + prod;
        }

        while (k > 0) {
            prod = prod + "0";
            k--;
        }
        return prod;
    }

    private String sumAllProds(List<String> prods) {
        int max = 0;
        for (String prod: prods) {
            if (prod.length() > max) {
                max = prod.length();
            }
        }
        int pre = 0;
        String ans = "";

        for (int i = 0; i < max; i++) {
            int cur = pre;
            for (String prod: prods) {
                if (i <= prod.length() - 1) {
                    int id = prod.length() - 1 - i;
                    int j = Integer.valueOf(String.valueOf(prod.charAt(id)));
                    cur += j;
                }
            }
            pre = cur / 10;
            ans = String.valueOf(cur % 10) + ans;
        }
        if (pre > 0) {
            ans = String.valueOf(pre) + ans;
        }
        return ans;
    }

    public String multiply2(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();

        if(len2 > len1) return multiply(num2, num1);

        char[] result = new char[len1 + len2];
        Arrays.fill(result, '0');

        for(int i = len2 - 1; i >= 0; i--) {
            char a = num2.charAt(i);
            for(int j = len1 - 1; j >= 0; j--) {
                char b = num1.charAt(j);

                int prod = (a - '0') * (b - '0');

                int temp = (result[i + j + 1] - '0') + (prod);
                result[i + j + 1] = (char)((temp % 10) + '0');

                temp = (result[i + j] - '0') + (temp / 10);
                result[i + j] = (char)((temp) + '0');
            }

        }

        StringBuilder sb = new StringBuilder();
        boolean numSeen = false;
        System.out.println(result);

        for(char c : result) {
            System.out.println(c);
            if(c - '0' > 0) numSeen = true;
            if(numSeen) {
                sb.append(c);
                System.out.println(sb);
                System.out.println(c);
            }

        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println('0' - '0');
//        multiplyStringsSolution solution = new multiplyStringsSolution();
//        String nums1 = "12004";
//        String nums2 = "10";
//        System.out.println(Integer.valueOf(nums1) * Integer.valueOf(nums2));
//        System.out.println(solution.multiply(nums1, nums2));
//        System.out.println(solution.multiply2(nums1, nums2));
        System.out.println(4/3);
        String a = "/a//b////c/d//././/..";
        for (String s: a.split("/")) {
            System.out.println(s.isEmpty());
        }
        System.out.println(Arrays.toString(a.split("/")));
    }
}
