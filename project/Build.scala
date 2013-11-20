import sbt._
import Keys._
import com.typesafe.sbt.SbtScalariform._
import com.typesafe.sbt.SbtNativePackager._
import spray.revolver.RevolverPlugin._



object Build extends Build {

  val buildSettings = Seq(
    organization := "yourorg",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.10.2",
    crossScalaVersions := Seq("2.10.2"),
    resolvers ++= Seq(
      "TypesafeMaven" at "http://repo.typesafe.com/typesafe/maven-releases",
      "whydoineedthis" at "http://repo.typesafe.com/typesafe/releases",
      "spray nightlies" at "http://nightlies.spray.io/",
      "spray repo" at "http://repo.spray.io", 
      "oss" at "http://oss.sonatype.org/content/repositories/snapshots")
  ) ++ Defaults.defaultSettings  ++ scalariformSettings ++ packageArchetype.java_application ++ Revolver.settings

  val sprayer = Project(
    id = "sprayer",
    base = file("."),
    settings = buildSettings ++ Seq(libraryDependencies ++= deps)
  )

  def deps = Seq(spray_routing, spray_client, spray_json, spray_dynamo, akka,
    slf4j, logback_classic, logback_core, spray_testkit, akka_testkit, scalaTest)

  val spray_routing = "io.spray" % "spray-routing" % "1.2-RC3" % "compile"
  val spray_client = "io.spray" % "spray-client" % "1.2-RC3" % "compile"
  val spray_json =  "io.spray" %%  "spray-json" % "1.2.5" % "compile"
  val akka = "com.typesafe.akka" %% "akka-actor" % "2.2.3" % "compile"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.7.5"
  val logback_classic = "ch.qos.logback" % "logback-classic" % "1.0.3"
  val logback_core = "ch.qos.logback" % "logback-core" % "1.0.3"
  val akka_testkit = "com.typesafe.akka" %% "akka-testkit" % "2.2.3" % "test"
  val spray_dynamo = "com.sclasen" %% "spray-dynamodb" % "0.2.1-SNAPSHOT"
  val spray_testkit   = "io.spray" % "spray-testkit" % "1.2-RC3" % "test"
  val scalaTest   = "org.scalatest" %% "scalatest" % "2.0" % "test"



}

