package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boyuangong created on 9/10/20 at 21:06
 */
public class EvaluationDivisionSolution {
    private double v;
    private Map<String, Map<String, Double>> graph;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        graph = new HashMap<>();
        for (int i=0; i<equations.size(); i++) {
            List<String> equation = equations.get(i);
            double val = values[i];
            String s1 = equation.get(0);
            String s2 = equation.get(1);
            Map<String, Double> map1 = graph.containsKey(s1)? graph.get(s1) : new HashMap<>();
            Map<String, Double> map2 = graph.containsKey(s2)? graph.get(s2) : new HashMap<>();
            map1.put(s2, val);
            map2.put(s1, 1/val);
            graph.put(s1, map1);
            graph.put(s2, map2);
        }

        System.out.println(graph.toString());

        double[] ans = new double[queries.size()];

        for (int i=0; i<queries.size(); i++) {
            ans[i] = getValue(queries.get(i));
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    private double getValue(List<String> query) {
        String s1 = query.get(0);
        String s2 = query.get(1);
        if (!graph.containsKey(s1) || !graph.containsKey(s2)) {
            return -1.0;
        }

        v = 1.0;
        List<String> visited = new ArrayList<>();
        boolean finded = dfs(visited, s1, s2, false);
        return finded? v : -1.0;
    }

    private boolean dfs(List<String> visited, String start, String end, boolean finded) {
        if (start.equals(end)) {
            return true;
        }
        visited.add(start);

        if (!graph.containsKey(start)) {
            return false;
        }

        Map<String, Double> childs = graph.get(start);

        for (Map.Entry<String, Double> child: childs.entrySet()) {
            System.out.println(child.toString());

            String node = child.getKey();
            double curval = child.getValue();
            if (!visited.contains(node)) {
                v *= curval;
                finded = dfs(visited, node, end, finded);
                if (finded) {
                    return true;
                }
                v /= curval;
            }
        }
        visited.remove(start);
        return false;
    }

    public static void main(String[] args) {
        EvaluationDivisionSolution solution = new EvaluationDivisionSolution();
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a","b"));
        equations.add(Arrays.asList("b","c"));
        double[] values = new double[]{2.0,3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a","c"));
        queries.add(Arrays.asList("b","a"));
        queries.add(Arrays.asList("a","e"));
        queries.add(Arrays.asList("a","a"));
        queries.add(Arrays.asList("x","x"));
        solution.calcEquation(equations, values, queries);
    }


}
