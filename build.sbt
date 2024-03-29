import com.typesafe.sbt.packager.docker.ExecCmd

name := """hatenaRss"""
version := "1.0-SNAPSHOT"
scalaVersion := "2.12.8"
crossScalaVersions := Seq("2.11.12", "2.12.7")

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, JavaAppPackaging, AshScriptPlugin, DockerPlugin)
  .settings(
    dockerBaseImage := "adoptopenjdk/openjdk11:alpine-slim",

    // it should create CMD command instead of ENTRYPOINT for heroku
    dockerCommands := dockerCommands.value.filter {
      case ExecCmd("CMD", _*) => false
      case _ => true
    }.map {
      case ExecCmd("ENTRYPOINT", args@_*) => ExecCmd("CMD", args: _*)
      case other => other
    }
  )

resolvers += Resolver.sonatypeRepo("snapshots")

val json4sNative = "org.json4s" %% "json4s-native" % "3.6.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += ws
libraryDependencies += ehcache
libraryDependencies += json4sNative
libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test"
libraryDependencies += "software.reinvent" % "headless-chrome" % "0.3.1"
libraryDependencies += cacheApi
