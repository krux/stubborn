val scala212Version = "2.12.12"
val scala213Version = "2.13.3"

val slf4jVersion = "1.7.+"

val scalaTestArtifact      = "org.scalatest"    %% "scalatest"    % "3.2.2"       % Test
val slf4jApiArtifact       = "org.slf4j"        %  "slf4j-api"    % slf4jVersion
val slf4jSimpleArtifact    = "org.slf4j"        %  "slf4j-simple" % slf4jVersion

lazy val publishSettings = Seq(
  sonatypeProfileName := "com.krux",
  publishMavenStyle := true,
  pomIncludeRepository := { _ => false },
  pgpSecretRing := file("secring.gpg"),
  pgpPublicRing := file("pubring.gpg"),
  publishTo := {
    if (isSnapshot.value)
      Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    else
      Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  },
  licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")),
  homepage := Some(url("https://github.com/krux/stubborn")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/krux/stubborn"),
      "scm:git:git@github.com:krux/stubborn.git"
    )
  ),
  developers := List(
    Developer(id = "realstraw", name = "Kexin Xie", email = "kexin.xie@salesforce.com", url = url("http://github.com/realstraw")),
    Developer(id = "sethyates", name = "Seth Yates", email = "syates@salesforce.com", url = url("http://github.com/sethyates"))
  )
)

lazy val noPublishSettings = Seq(
  publishArtifact := false,
  publish := {},
  publishLocal := {},
  publishTo := None
)

lazy val commonSettings = Seq(
  scalacOptions ++= Seq("-deprecation", "-feature", "-Xlint", "-Xfatal-warnings"),
  scalaVersion := scala212Version,
  crossScalaVersions := Seq(scala212Version, scala213Version),
  libraryDependencies += scalaTestArtifact,
  organization := "com.krux"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(publishSettings: _*).
  settings(
    name := "stubborn",
    libraryDependencies += slf4jApiArtifact
  )

lazy val examples = (project in file("examples")).
  settings(commonSettings: _*).
  settings(noPublishSettings: _*).
  settings(
    name := "stubborn-examples",
    libraryDependencies += slf4jSimpleArtifact
  ).
  dependsOn(root)
