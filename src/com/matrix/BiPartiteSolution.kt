package com.matrix

class BiPartiteSolution {
    fun isBipartite(graph: Array<IntArray>): Boolean {
        // connected nodes must be separated into two sets
        // so we only need to find out whether the nodes connected
        // to the current node are connected to each other
        // so I would prefer to use dfs to solve this

        // first we need an 2d array to store whether two nodes are connected to each other
        // -1: value not set
        // 0: not connected
        // 1: connected
        var store = Array(100){0};
        return backtracking(graph, 0, store)
    }

    fun backtracking(graph: Array<IntArray>, curNode: Int, store: Array<Int>): Boolean {
        if (curNode >= graph.size) return true

        val childs = graph[curNode]

        var alreadyAssigned = false
        var assignedValue = 0
        for (node in childs) {
            if (!alreadyAssigned && store[node] != 0) {
                alreadyAssigned = true
                assignedValue = store[node]
            }
            if (alreadyAssigned && store[node] != 0) {
                if (assignedValue != store[node]) {
                    return false
                }
            }
        }

        if (alreadyAssigned) {
            store[curNode] = 0 - assignedValue
        }

        if (store[curNode] == 0) {


            store[curNode] = 1
            for (node in childs) {
                if (store[node] == 1) return false
                store[node] = -1
            }
            var isTrue = backtracking(graph, curNode+1, store)
            if (isTrue) return true

            // backtracking not true
            store[curNode] = -1
            for (node in childs) {
                if (store[node] == -1) return false
                store[node] = 1
            }
        } else {
            for (node in childs) {
                if (store[node] == store[curNode]) return false
                store[node] = 0 - store[curNode]
            }
        }
        return backtracking(graph, curNode+1, store)
    }


}