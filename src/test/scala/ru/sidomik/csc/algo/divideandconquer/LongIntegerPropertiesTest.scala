package ru.sidomik.csc.algo.divideandconquer

import org.junit.runner.RunWith
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}
import ru.sidomik.csc.algo.util.ScalaCheckJUnitPropertiesRunner

@RunWith(classOf[ScalaCheckJUnitPropertiesRunner])
class LongIntegerPropertiesTest extends Properties("LongInteger") {
  val longInt: Gen[LongInteger] =
    for {
      x <- Gen.choose(-100000, 100000)
    } yield new LongInteger(x.toString)

  property("sum") = forAll (longInt, longInt) { (a: LongInteger, b: LongInteger) =>
    a.add(b).toString == (BigInt(a.toString) + BigInt(b.toString)).toString()
  }

  property("mul") = forAll (longInt, longInt) { (a: LongInteger, b: LongInteger) =>
    a.mul(b).toString == (BigInt(a.toString) * BigInt(b.toString)).toString()
  }
}
