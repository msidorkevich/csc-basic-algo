package ru.sidomik.csc.algo.problem

import java.util.*

fun validateBraces(s: String): Boolean {
    val stack = Stack<Char>()

    s.forEach {
        if (it == '(') stack.push(it)
        else if (it == ')') {
            if (stack.isEmpty()) return false
            val c = stack.pop()
            if (c != '(') return false
        }
    }

    return stack.isEmpty()
}