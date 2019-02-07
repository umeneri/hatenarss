name := """play-scala-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.8"

crossScalaVersions := Seq("2.11.12", "2.12.7")

val json4sNative = "org.json4s" %% "json4s-native" % "3.6.3"
val json4sXml = "org.json4s" %% "json4s-xml" % "3.6.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies += ws
libraryDependencies += ehcache
libraryDependencies += json4sNative
libraryDependencies += json4sXml

