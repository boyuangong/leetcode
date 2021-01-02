package com.matrix

class RedundantConnectionSolution {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        // first we initialize the DSU
        val dsu = DSU()
        for (edge in edges) {
            println(edge)
            if (!dsu.union(edge)) {
                return edge
            }
        }
        throw AssertionError();
    }

    class DSU {
        var rank = IntArray(1001)
        var parent = IntArray(1001) {i -> i}
        fun find(i: Int): Int {
            var node = i;
            while (parent[node] != node) {
                node = parent[node]
            }
            return node;
        }

        fun union(edge: IntArray): Boolean{
            var p1 = find(edge[0]);
            var p2 = find(edge[1]);
            if (p1 == p2) {
                // this means the two node already be union, so the
                // current edge is redundant
                return false;
            } else if (rank[p1] > rank[p2]) {
                // we always assign the low rank to the high rank(higher rank will be parent)
                parent[p2] = p1;
            } else if (rank[p2] > rank[p1]) {
                parent[p1] = p2;
            } else {
                // two parent is the same rank;
                parent[p2] = p1;
                rank[p1] ++;
            }
            return true;
        }
    }

    fun main() {
        val test = arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(1, 4));
        val solution = findRedundantConnection(test);
    }
}