package com.sbt


object Main {
  val fansiString: fansi.Str = fansi.Color.Red("This is a fansi string...")
  //val fansiString = "vamos a la playa"
  def main(args: Array[String]): Unit = println(fansiString)
}