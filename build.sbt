val scala212 = "2.12.16"
val scala213 = "2.13.10"


ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := scala213
ThisBuild / name := "multi-module"
ThisBuild / organization := "silvio.com"

// Add an external resolver
//resolvers += Resolver.url("my-test-repo", url("https://silvio.com"))

// Add a mavel local repo
resolvers += Resolver.mavenLocal

// Custom Task
// Referring a custom task
lazy val printerTask = taskKey[Unit]("Custom Printer Task")

printerTask := {
  val uuid = uuidStringTask.value
  println(s"Generated UUID: $uuid")

  val uuidSetting = uuidStringSetting.value
  println(s"Configuration getting: $uuidSetting")

  // This line is located in the CustomTaskPrinter.scala file
  CustomTaskPrinter.print()
}

// This is a task that returns a certain value.
lazy val uuidStringTask = taskKey[String]("Random UUID generator")

uuidStringTask := {
  StringTask.stringTask()
}

// Custom Settings
lazy val uuidStringSetting = settingKey[String]("Random UUID setting")
uuidStringSetting := {
  "CONFIGURATION_EXAMPLE_TEST"
}

// This is a command ALIASES, can 'ci' invoked in the command line
addCommandAlias("ci", "compile;test;assembly")


lazy val core = (project in file("core")).settings(
  assembly / mainClass := Some("com.rockthejvm.CoreApp"),
  libraryDependencies += "com.typesafe" % "config" % "1.4.2",
  crossScalaVersions := List(scala212, scala213)
)
lazy val server = (project in file("server")).dependsOn(core)
lazy val root = (project in file(".")).aggregate(core, server)


libraryDependencies ++= Seq(
  "com.lihaoyi" %% "fansi" % "0.4.0",
  "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.4.1",
  "org.scalatest" %% "scalatest" % "3.2.12" % Test
)
