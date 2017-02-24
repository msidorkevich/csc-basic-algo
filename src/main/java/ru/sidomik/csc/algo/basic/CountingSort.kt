package ru.sidomik.csc.algo.basic

import java.lang.Math.abs

fun sort(a: IntArray): IntArray {
    val amax = a.max()
    val amin = a.min()

    if (amax == null || amin == null) {
        return intArrayOf()
    }

    val positive = IntArray(abs(amax))
    val negative = IntArray(abs(amin))
    var zero = 0

    // O(n)
    for (i in a) {
        if (i == 0) {
            zero++
        } else if (i > 0) {
            positive[i - 1]++
        } else {
            negative[-i - 1]++
        }
    }

    // O(n), because result has size n
    val result = IntArray(a.size)
    var k = 0
    for (i in negative.indices.reversed()) {
        for (j in 1..negative[i]) {
            result[k++] = -i - 1
        }
    }
    for (i in 1..zero) {
        result[k++] = 0
    }
    for (i in positive.indices) {
        for (j in 1..positive[i]) {
            result[k++] = i + 1
        }
    }

    return result
}
