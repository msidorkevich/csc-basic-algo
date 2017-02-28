package ru.sidomik.csc.algo.problem

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CycleListTest {

    @Test
    fun singleNodeCycle() {
        val singleNodeCycle = List.Node(List.Nil)
        singleNodeCycle.next = singleNodeCycle

        assertThat(hasCycle(singleNodeCycle)).isTrue()
    }

    @Test
    fun singleNodeNoCycle() {
        assertThat(hasCycle(List.Node(List.Nil))).isFalse()
    }

    @Test
    fun emptyList() {
        assertThat(hasCycle(List.Nil)).isFalse()
    }

    @Test
    fun twoNodeCycle() {
        val node1 = List.Node(List.Nil)
        val node2 = List.Node(List.Nil)

        node1.next = node2
        node2.next = node1

        assertThat(hasCycle(node1)).isTrue()
    }

    @Test
    fun twoNodeNoCycle() {
        val node1 = List.Node(List.Nil)
        val node2 = List.Node(List.Nil)

        node1.next = node2

        assertThat(hasCycle(node1)).isFalse()
    }

    @Test
    fun cycleOf2ElementsNotFromTheBeginning() {
        val node1 = List.Node(List.Nil)
        val node2 = List.Node(List.Nil)
        val node3 = List.Node(List.Nil)
        val node4 = List.Node(List.Nil)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node2

        assertThat(hasCycle(node1)).isTrue()
    }

    @Test
    fun cycleOf3ElementsNotFromTheBeginning() {
        val node1 = List.Node(List.Nil)
        val node2 = List.Node(List.Nil)
        val node3 = List.Node(List.Nil)
        val node4 = List.Node(List.Nil)
        val node5 = List.Node(List.Nil)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node2

        assertThat(hasCycle(node1)).isTrue()
    }
}