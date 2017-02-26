package ru.sidomik.csc.algo.problem

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class CheckBracesTest {

    @Test
    fun emptyStringIsValid() {
        assertThat(validateBraces("")).isTrue()
    }

    @Test
    fun stringWithOpenCloseBracesIsValid() {
        assertThat(validateBraces("()")).isTrue()
    }

    @Test
    fun stringWithCloseOpenBracesIsInvalid() {
        assertThat(validateBraces(")(")).isFalse()
    }

    @Test
    fun validInnerBraces() {
        assertThat(validateBraces("((()()(()())))")).isTrue()
    }

    @Test
    fun noClosingBrace() {
        assertThat(validateBraces("(()")).isFalse()
    }

    @Test
    fun noOpeningBrace() {
        assertThat(validateBraces("())")).isFalse()
    }
}