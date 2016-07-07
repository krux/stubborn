val scala210Version = "2.10.6"
val scala211Version = "2.11.8"

val scalaTestArtifact      = "org.scalatest"    %% "scalatest"    % "2.2.4"       % "test"
val slf4jApiArtifact       = "org.slf4j"        %  "slf4j-api"    % "1.7.12"
val slf4jSimpleArtifact    = "org.slf4j"        %  "slf4j-simple" % "1.7.12"

lazy val commonSettings = Seq(
  scalacOptions ++= Seq("-deprecation", "-feature", "-Xlint", "-Xfatal-warnings"),
  version := "0.0.1",
  scalaVersion := scala210Version,
  crossScalaVersions := Seq(
    scala210Version,
    scala211Version
  ),
  libraryDependencies += scalaTestArtifact,
  organization := "com.krux",
  publishMavenStyle := true
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "stubborn",
    libraryDependencies += slf4jApiArtifact
  )

lazy val examples = (project in file("examples")).
  settings(commonSettings: _*).
  settings(
    name := "stubborn-examples",
    libraryDependencies += slf4jSimpleArtifact
  ).
  dependsOn(root)
