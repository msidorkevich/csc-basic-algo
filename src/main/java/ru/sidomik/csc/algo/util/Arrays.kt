package ru.sidomik.csc.algo.util


fun IntArray.zipAll(other: IntArray, emptyValue: Int, otherEmptyValue: Int): List<Pair<Int, Int>> {
    val i1 = this.iterator()
    val i2 = other.iterator()
    return generateSequence {
        if (i1.hasNext() || i2.hasNext()) {
            Pair(if (i1.hasNext()) i1.next() else emptyValue,
                 if (i2.hasNext()) i2.next() else otherEmptyValue)
        } else {
            null
        }
    }.toList()
}