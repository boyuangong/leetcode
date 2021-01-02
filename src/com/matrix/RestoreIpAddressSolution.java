package com.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boyuangong created on 8/29/20 at 12:51
 */
public class RestoreIpAddressSolution {
    List<String> ips;
    public List<String> restoreIpAddresses(String s) {
        ips = new ArrayList<>();
        backtrackingForIp(s, new StringBuilder(), 0, 0);
        System.out.println(ips.toString());
        return ips;
    }

    public void backtrackingForIp(String s, StringBuilder sb, int numOfDots, int id) {
        System.out.println(sb.toString());
        String sbs = sb.toString();

        System.out.println(sbs);
        if (id >= s.length()) {
            return;
        }

        if (numOfDots == 3) {
            String cur = s.substring(id);
            if (validSubString(cur)) {
                sb.append(cur);
                ips.add(sb.toString());
            }
            return;
        }

        for (int i=1; i<=3; i++) {
            if (id + i > s.length()) {
                break;
            }
            String cur = s.substring(id, id + i);
            if (!validSubString(cur)) {
                break;
            }
            sb.append(cur);
            if (numOfDots < 3) {
                sb.append(".");
            }
            backtrackingForIp(s, sb, numOfDots+1, id + i);
            sb = new StringBuilder(sbs);

        }
    }


    private boolean validSubString(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.length() > 1 && s.startsWith("0")) {
            return false;
        }

        if (s.length() >= 4) {
            return false;
        }
        return Integer.valueOf(s) <= 255;
    }

    public static void main(String[] args) {
        RestoreIpAddressSolution solution =  new RestoreIpAddressSolution();
//        solution.restoreIpAddresses("25525511135");
        solution.restoreIpAddresses("0279245587303");
    }
}
