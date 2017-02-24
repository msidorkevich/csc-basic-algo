package ru.sidomik.csc.algo.basic

import org.junit.runner.RunWith
import org.scalacheck.Prop.{forAll, BooleanOperators}

import org.scalacheck.Properties
import ru.sidomik.csc.algo.util.ScalaCheckJUnitPropertiesRunner

@RunWith(classOf[ScalaCheckJUnitPropertiesRunner])
class FibPropertiesTest extends Properties("Fib") {
  property("main") = forAll { i: Int =>
    (i >= 0 && i <= Int.MaxValue - 2) ==>
      (FibKt.fib(i) + FibKt.fib(i + 1) == FibKt.fib(i + 2))
  }
}
