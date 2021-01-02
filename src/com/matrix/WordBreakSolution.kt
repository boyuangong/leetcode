package com.matrix

import java.util.*
import kotlin.collections.ArrayList

class WordBreakSolution {
    val letterToWordMap = mutableMapOf<Char, ArrayList<String>>()
    var visited = mutableMapOf<Int, Boolean>()
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        wordDict.listIterator().forEach {
            val c = it.first()
            var clist = arrayListOf<String>()
            if (letterToWordMap.containsKey(c)) {
                clist = letterToWordMap[c]!!
            }

            clist.add(it)
            letterToWordMap.put(c, clist)
        }
        return backTracking(s)
    }

    fun backTracking(s: String): Boolean {
        if (s.isEmpty()) {
            return true
        }
        if (visited.containsKey(s.length)) {
            return visited[s.length]!!
        }
        val c = s.first()
        if (!letterToWordMap.containsKey(c)) {
            return false
        }
        val clist = letterToWordMap[c]!!
        var nexts: String
        clist.iterator().forEach {
            if (s.startsWith(it)) {
                nexts = s.removePrefix(it)
                if (backTracking(nexts)) {
                    visited.put(s.length, true)
                    return true
                }
            }
        }
        visited.put(s.length, false)
        return false
    }
}


fun main(args: Array<String>) {
//    println(WordBreakSolution().wordBreak("test", listOf("test", "set")))
    var s = "applepenappleapple"
    var wordDict = listOf<String>("apple", "pen", "sand", "and", "cat")
    println(WordBreakSolution().wordBreak(s, wordDict))

    var s2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
    var wordDict2 = listOf<String>("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")
    println(WordBreakSolution().wordBreak(s2, wordDict2))

}
