package ru.sidomik.csc.algo.divideandconquer

import ru.sidomik.csc.algo.util.zipAll
import java.util.*

class LongInteger {

    private val digits: IntArray
    private val signum: Int

    constructor(number: String) {
        if (number.isBlank()) {
            throw IllegalArgumentException("Incorrect string format")
        }

        if (number.length == 1 && number[0] == '0') {
            signum = 0
        } else if (number[0] == '-') {
            signum = -1
        } else {
            signum = 1
        }

        val size = if (signum == -1) number.length - 1 else number.length

        digits = IntArray(size)

        var i = digits.size - 1
        for (char in number) {
            if (char.isDigit()) {
                digits[i--] = char.toString().toInt() // todo: find proper way to convert char to int
            }
        }
    }

    private constructor(digits: IntArray, signum: Int) {
        this.digits = digits
        if (digits.size == 1 && digits[0] == 0) this.signum = 0
        else this.signum = signum
    }

    fun add(other: LongInteger): LongInteger {
        if (signum > 0 && other.signum > 0) return LongInteger(addDigits(digits, other.digits), 1)
        else if (signum < 0 && other.signum < 0) return LongInteger(addDigits(digits, other.digits), -1)
        else if (signum > 0 && other.signum < 0) { // todo: below 2 if should be uniformed
            if (this.compareAbs(other) > 0) {
                return LongInteger(subDigits(digits, other.digits), 1)
            } else {
                return LongInteger(subDigits(other.digits, digits), -1)
            }
        } else if (signum < 0 && other.signum > 0) {
            if (this.compareAbs(other) > 0) {
                return LongInteger(subDigits(digits, other.digits), -1)
            } else {
                return LongInteger(subDigits(other.digits, digits), 1)
            }
        } else if (signum == 0) return other
        else return this
    }

    private fun addDigits(a: IntArray, b: IntArray): IntArray {
        var res = IntArray(Math.max(a.size, b.size) + 1)
        var carry = 0

        var i = 0
        a.zipAll(b, 0, 0).forEach {
            val sum = it.first + it.second + carry
            carry = sum / 10
            res[i++] = sum.mod(10)
        }

        res[i] = carry

        res = trimLeadingZeros(res)

        return res
    }

    private fun trimLeadingZeros(a: IntArray): IntArray {
        if (a.size == 1) return a

        var k = 0
        for (i in a.indices.reversed()) {
            if (a[i] != 0) {
                break
            }
            k++
        }

        if (k == a.size) k--

        if (k > 0) {
            val copy = IntArray(a.size - k)
            System.arraycopy(a, 0, copy, 0, a.size - k)
            return copy
        }
        return a
    }

    private fun subDigits(a: IntArray, b: IntArray): IntArray {
        var res = IntArray(Math.max(a.size, b.size))
        var i = 0
        var remainder = 0
        a.zipAll(b, 0, 0).forEach {
            var sum = it.first - it.second - remainder
            if (remainder > 0) remainder = 0
            if (sum < 0) {
                remainder = 1
                sum += 10
            }
            res[i++] = sum
        }

        res = trimLeadingZeros(res)

        return res
    }

    fun sub(other: LongInteger): LongInteger {
        return this.add(ru.sidomik.csc.algo.divideandconquer.LongInteger(other.digits, -other.signum))
    }

    private fun compareAbs(other: LongInteger): Int {
        if (digits.size > other.digits.size) return 1
        else if (digits.size < other.digits.size) return -1
        else {
            digits.zipAll(other.digits, 0, 0).reversed().forEach {
                if (it.first != it.second) {
                    return it.first - it.second
                }
            }
            return 0
        }
    }

    fun mulTenPower(n: Int): LongInteger {
        val res = IntArray(digits.size + n)

        System.arraycopy(digits, 0, res, n, digits.size)

        return LongInteger(res, signum)
    }

    fun mul(other: LongInteger): LongInteger {
        if (digits.size == 1 && other.digits.size == 1) {
            return LongInteger(mulSingleDigit(digits[0], other.digits[0]), signum * other.signum)
        }

        var x = findMinLength(this, other)
        val y = findMaxLength(this, other)

        val minSize = Math.min(digits.size, other.digits.size)
        val maxSize = Math.max(digits.size, other.digits.size)
        if (minSize != maxSize) {
            val xCopy = IntArray(maxSize)
            System.arraycopy(x.digits, 0, xCopy, 0, minSize)
            x = LongInteger(xCopy, x.signum)
        }

        val xLeft = x.left()
        val xRight = x.right()
        val yLeft = y.left()
        val yRight = y.right()

        // p1 = x_l * y_l
        // p2 = x_r * y_r
        // p3 = (x_l + x_r) * (y_l + y_r)
        val p1 = xLeft.mul(yLeft)
        val p2 = xRight.mul(yRight)
        val p3 = (xLeft.add(xRight)).mul(yLeft.add(yRight))
        val n = x.digits.size

        return p1.mulTenPower(2 * (n / 2)).add((p3.sub(p1).sub(p2)).mulTenPower(n / 2)).add(p2)
    }

    private fun findMinLength(a: LongInteger, b: LongInteger): LongInteger {
        if (a.digits.size < b.digits.size) return a
        else return b
    }

    private fun findMaxLength(a: LongInteger, b: LongInteger): LongInteger {
        if (a.digits.size >= b.digits.size) return a
        else return b
    }

    private fun mulSingleDigit(a: Int, b: Int): IntArray {
        val mul = a * b
        if (mul < 10) return intArrayOf(mul)

        val arraySize = Math.floor(Math.log(mul.toDouble()) / Math.log(10.0)).toInt() + 1
        val ar = IntArray(arraySize)
        var i = 0
        var temp = mul
        while (temp >= 10) {
            ar[i++] = temp.mod(10)
            temp /= 10
        }
        ar[i] = temp
        return ar
    }

    private fun left(): LongInteger = LongInteger(digits.copyOfRange(digits.size / 2, digits.size), signum)
    private fun right(): LongInteger = LongInteger(digits.copyOfRange(0, digits.size / 2), signum)

    override fun toString(): String {
        var res = ""
        if (signum == -1) res += "-"
        for (i in digits.reversed()) {
            res += i
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as LongInteger

        if (!Arrays.equals(digits, other.digits)) return false
        if (signum != other.signum) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(digits)
        result = 31 * result + signum
        return result
    }
}