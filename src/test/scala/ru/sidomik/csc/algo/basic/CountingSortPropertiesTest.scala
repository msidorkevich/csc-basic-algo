package ru.sidomik.csc.algo.basic

import org.junit.runner.RunWith
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import ru.sidomik.csc.algo.util.ScalaCheckJUnitPropertiesRunner

@RunWith(classOf[ScalaCheckJUnitPropertiesRunner])
class CountingSortPropertiesTest extends Properties("Fib") {
  private val arrayGen = for {
    size <- Gen.choose(0, 100)
    l <- Gen.listOfN(size, Gen.chooseNum(-1000, 1000))
  } yield l.toArray

  property("ordering") = forAll(arrayGen) { a =>
    val sorted = CountingSortKt.sort(a)
    isOrdered(sorted)
  }

  property("size doesn't change") = forAll(arrayGen) { a =>
    val sorted = CountingSortKt.sort(a)
    a.length == sorted.length
  }

  property("all elements exit") = forAll(arrayGen) { a =>
    val sorted = CountingSortKt.sort(a)
    a.map(el => sorted.contains(el)).reduceOption((a,b) => a && b).getOrElse(true)
  }

  def isOrdered(ar: Array[Int]): Boolean = {
    @annotation.tailrec
    def isOrdered0(i: Int): Boolean = {
      if (i >= ar.length - 1) true
      else ar(i) <= ar(i + 1) && isOrdered0(i + 1)
    }
    isOrdered0(0)
  }
}
