package ru.sidomik.csc.algo.basic

import org.junit.Assert.assertEquals
import org.junit.Test

internal class FibTest {
    @Test
    fun fib() {
        assertEquals(1, fib(0))
        assertEquals(1, fib(1))
        assertEquals(2, fib(2))
        assertEquals(3, fib(3))
        assertEquals(5, fib(4))
        assertEquals(8, fib(5))
        assertEquals(13, fib(6))
        assertEquals(21, fib(7))
    }

}