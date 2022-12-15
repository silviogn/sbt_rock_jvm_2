package com.sbt

import org.scalatest.funsuite.AnyFunSuite

class SimpleTest extends AnyFunSuite {

  test("This is a simple test") {
    assert("Scala".toLowerCase == "scala")
  }
}
