package ru.sidomik.csc.algo.basic

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.sidomik.csc.algo.divideandconquer.LongInteger

internal class LongIntegerTest {

    @Test
    fun addPositiveNumbers() {
        assertThat(LongInteger("1234").add(LongInteger("2345"))).isEqualTo(LongInteger("3579"))
    }

    @Test
    fun addPositiveNumbersWithCarry() {
        assertThat(LongInteger("1274").add(LongInteger("2345"))).isEqualTo(LongInteger("3619"))
    }

    @Test
    fun addPositiveNumbersWithCarryInHighDigit() {
        assertThat(LongInteger("8274").add(LongInteger("9345"))).isEqualTo(LongInteger("17619"))
    }

    @Test
    fun addNegativeNumbers() {
        assertThat(LongInteger("-1234").add(LongInteger("-2345"))).isEqualTo(LongInteger("-3579"))
    }

    @Test
    fun addNegativeNumbersWithCarry() {
        assertThat(LongInteger("-1274").add(LongInteger("-2345"))).isEqualTo(LongInteger("-3619"))
    }

    @Test
    fun addNegativeNumbersWithCarryInHighDigit() {
        assertThat(LongInteger("-8274").add(LongInteger("-9345"))).isEqualTo(LongInteger("-17619"))
    }

    @Test
    fun addPositiveAndNegativeNumberWhenResultIsGreaterThanZero() {
        assertThat(LongInteger("2345").add(LongInteger("-1234"))).isEqualTo(LongInteger("1111"))
    }

    @Test
    fun addPositiveAndNegativeNumberWhenResultIsGreaterThanZeroWithCarry() {
        assertThat(LongInteger("2345").add(LongInteger("-1454"))).isEqualTo(LongInteger("891"))
    }

    @Test
    fun addPositiveAndNegativeNumbersWhenResultIsLessThanZero() {
        assertThat(LongInteger("1234").add(LongInteger("-2345"))).isEqualTo(LongInteger("-1111"))
    }

    @Test
    fun addNegativeAndPositiveNumbersWhenResultIsGreaterThanZero() {
        assertThat(LongInteger("-1234").add(LongInteger("2345"))).isEqualTo(LongInteger("1111"))
    }

    @Test
    fun addNegativeAndPositiveNumbersWhenResultIsLessThanZero() {
        assertThat(LongInteger("-2345").add(LongInteger("1234"))).isEqualTo(LongInteger("-1111"))
    }

    @Test
    fun addZeroToPositiveNumberDoesntChangeIt() {
        assertThat(LongInteger("1234").add(LongInteger("0"))).isEqualTo(LongInteger("1234"))
        assertThat(LongInteger("0").add(LongInteger("1234"))).isEqualTo(LongInteger("1234"))
    }

    @Test
    fun addZeroToNegativeNumberDoesntChangeIt() {
        assertThat(LongInteger("-1234").add(LongInteger("0"))).isEqualTo(LongInteger("-1234"))
        assertThat(LongInteger("0").add(LongInteger("-1234"))).isEqualTo(LongInteger("-1234"))
    }

    @Test
    fun addOppositeNumbersReturnsZero() {
        assertThat(LongInteger("-1234").add(LongInteger("1234"))).isEqualTo(LongInteger("0"))
        assertThat(LongInteger("1234").add(LongInteger("-1234"))).isEqualTo(LongInteger("0"))
    }

    @Test
    fun addZerosReturnsZero() {
        assertThat(LongInteger("0").add(LongInteger("0"))).isEqualTo(LongInteger("0"))
    }

    @Test
    fun subPositiveNumbersWhenResultIsGreaterThanZero() {
        assertThat(LongInteger("2345").sub(LongInteger("1234"))).isEqualTo(LongInteger("1111"))
    }

    @Test
    fun mulPowerTen() {
        assertThat(LongInteger("23").mulTenPower(1)).isEqualTo(LongInteger("230"))
        assertThat(LongInteger("23").mulTenPower(2)).isEqualTo(LongInteger("2300"))
        assertThat(LongInteger("23").mulTenPower(3)).isEqualTo(LongInteger("23000"))
    }

    @Test
    fun mulSingleDigitNumbers() {
        assertThat(LongInteger("2").mul(LongInteger("3"))).isEqualTo(LongInteger("6"))
    }

    @Test
    fun mulSingleDigitNumbersWithCarry() {
        assertThat(LongInteger("4").mul(LongInteger("5"))).isEqualTo(LongInteger("20"))
    }

    @Test
    fun mulPositiveNumbersWithEvenAndSameNumberOfDigits() {
        assertThat(LongInteger("23").mul(LongInteger("12"))).isEqualTo(LongInteger("276"))
    }

    @Test
    fun mulPositiveNumbersWithOddAndSameNumberOfDigits() {
        assertThat(LongInteger("123").mul(LongInteger("234"))).isEqualTo(LongInteger("28782"))
    }

    @Test
    fun mulPositiveNumbersWithNotSameNumberOfDigits() {
        assertThat(LongInteger("12").mul(LongInteger("234"))).isEqualTo(LongInteger("2808"))
        assertThat(LongInteger("234").mul(LongInteger("12"))).isEqualTo(LongInteger("2808"))
    }

    @Test
    fun mulPositiveNumbersWithNotSameNumberOfDigitsWithCarry() {
        assertThat(LongInteger("4567").mul(LongInteger("234"))).isEqualTo(LongInteger("1068678"))
    }

    @Test
    fun mulNegativeNumbersWithNotSameNumberOfDigitsWithCarry() {
        assertThat(LongInteger("-4567").mul(LongInteger("-234"))).isEqualTo(LongInteger("1068678"))
    }

    @Test
    fun mulNegativeAndPositiveNumbersWithNotSameNumberOfDigitsWithCarry() {
        assertThat(LongInteger("4567").mul(LongInteger("-234"))).isEqualTo(LongInteger("-1068678"))
        assertThat(LongInteger("-4567").mul(LongInteger("234"))).isEqualTo(LongInteger("-1068678"))
    }

    @Test
    fun mulNumberOfDigitsDifferBy2() {
        assertThat(LongInteger("8").mul(LongInteger("416"))).isEqualTo(LongInteger("3328"))
    }
}