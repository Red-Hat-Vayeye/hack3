name := """play-hack-03"""
organization := "com.redhatvayeye"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "com.twitter" % "hbc-core" % "2.2.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.8.11"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.11"
