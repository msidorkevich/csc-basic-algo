package ru.sidomik.csc.algo.basic

fun fib(n: Int): Int {
    if (n <= 1) return 1

    var a1 = 1
    var a2 = 1
    for (i in 2..n) {
        val a = a1 + a2
        a1 = a2
        a2 = a
    }

    return a2
}