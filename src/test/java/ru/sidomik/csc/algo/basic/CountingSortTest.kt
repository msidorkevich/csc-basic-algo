package ru.sidomik.csc.algo.basic

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class CountingSortTest {

    @Test
    fun alreadySorted() {
        val a = intArrayOf(1, 2, 3)
        checkSort(a)
    }

    @Test
    fun unsorted() {
        val a = intArrayOf(3, 5, 0, 1, 2, 10)
        checkSort(a)
    }

    @Test
    fun empty() {
        val a = intArrayOf()
        checkSort(a)
    }

    @Test
    fun negativeNumbers() {
        val a = intArrayOf(3, -5, 0, -1, 2, -10)
        checkSort(a)
    }

    @Test
    fun onlyNegativeNumbers() {
        val a = intArrayOf(-5, -1, -10)
        checkSort(a)
    }

    private fun checkSort(a: IntArray) {
        val sorted = sort(a)

        a.sort()
        assertThat(sorted).isEqualTo(a)
    }
}

