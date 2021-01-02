package com.matrix;

/**
 * @author boyuangong created on 8/19/20 at 09:52
 */
public class WildCardMatching {
    private boolean match;
    private String s;
    private String p;

    public boolean isMatch(String s, String p) {
        //backtracking when we only meet star
        if (s == null || s.length() == 0) {
            if (p == null || p.length() == 0) {
                return true;
            } else {
                return p.equals("*");
            }
        } else {
            if (p == null || p.length() == 0) {
                return false;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (!(i > 0 && p.charAt(i) == '*' && p.charAt(i - 1) == '*')) {
                sb.append(p.charAt(i));
            }
        }
        p = sb.toString();

        System.out.println(p);

        this.s = s;
        this.p = p;
        this.match = false;
        backtracking(0, 0);
        System.out.println(match);
        return match;
    }

    private void backtracking(int si, int pi) {
        // need judge whether the match is true here
        int sn = s.length();
        int pn = p.length();
        System.out.println("----");
        System.out.println(si);
        System.out.println(pi);
        char star = '*';
        char q = '?';

        if (si == sn && pi == pn) {
            match = true;
            return;
        }

        if (si == sn && pi <= pn - 1) {
            while (pi <= pn - 1 && p.charAt(pi) == star) {
                pi++;
            }
            if (pi == pn) {
                match = true;
                return;
            } else {
                return;
            }
        }

        if (pi < pn && p.charAt(pi) != star) {
            // if the current si and pi is a match, continue. Otherwise
            // we drop this path
            if (p.charAt(pi) == q || (p.charAt(pi) == s.charAt(si))) {
                // that's a match
                backtracking(si + 1, pi + 1);
            }
        } else if (pi < pn && p.charAt(pi) == star) {
            backtracking(si, pi + 1);
            if (!match) {
                if (si + 1 <= sn) {
                    backtracking(si + 1, pi+1);
                }
            }
        }
    }

    public boolean isMatch2(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            System.out.println(sIdx);
            System.out.println(pIdx);
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))){
                ++sIdx;
                ++pIdx;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            else if (starIdx == -1) {
                return false;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for(int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;
        return true;
    }

    public static void main(String[] args) {
        WildCardMatching matching = new WildCardMatching();
//        String s = "acdcb";
//        String p = "a*c?b";
//        matching.isMatch(s, p);
////
//        String s1 = "ho";
//        String p1 = "ho**";
//        System.out.println(matching.isMatch(s1, p1));
//
        String s2 = "aa";
        String p2 = "*";
        System.out.println(matching.isMatch(s2, p2));

//        String s3 = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
//        String p3 = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb" +
//                "**a*b*bb";
//        System.out.println(matching.isMatch(s3, p3));
//        System.out.println(matching.isMatch2(s3, p3));
    }
}
