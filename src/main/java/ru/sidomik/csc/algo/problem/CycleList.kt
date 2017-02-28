package ru.sidomik.csc.algo.problem

sealed class List {
    class Node(var next: List): List()
    object Nil: List()
}

fun hasCycle(l: List): Boolean {
    var a = l
    var b = l
    do {
        a = next(a)
        b = next(next(b))
    } while (a != b && a != l)

    if (a == List.Nil || b == List.Nil) return false

    return true
}

private fun next(l: List): List =
    when (l) {
        is List.Node -> l.next
        is List.Nil -> List.Nil
    }