val scala210Version = "2.10.6"
val scala211Version = "2.11.8"

val scalaTestArtifact      = "org.scalatest"    %% "scalatest"    % "2.2.4"       % "test"
val slf4jApiArtifact       = "org.slf4j"        %  "slf4j-api"    % "1.7.12"
val slf4jSimpleArtifact    = "org.slf4j"        %  "slf4j-simple" % "1.7.12"

lazy val publishSettings = Seq(
  sonatypeProfileName := "com.krux",
  publishMavenStyle := true,
  pomIncludeRepository := { _ => false },
  pgpSecretRing := file("secring.asc"),
  pgpPublicRing := file("pubring.asc"),
  publishTo := {
    if (isSnapshot.value)
      Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    else
      Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  },
  pomExtra := (
    <url>https://github.com/krux/stubborn</url>
    <scm>
       <url>git@github.com:krux/stubborn.git</url>
       <connection>scm:git:git@github.com:krux/stubborn.git</connection>
    </scm>
    <licenses>
      <license>
        <name>Apache-2.0</name>
        <url>http://opensource.org/licenses/Apache-2.0</url>
      </license>
    </licenses>
    <developers>
       <developer>
         <id>realstraw</id>
         <name>Kexin Xie</name>
         <url>http://github.com/realstraw</url>
       </developer>
       <developer>
         <id>sethyates</id>
         <name>Seth Yates</name>
         <url>http://github.com/sethyates</url>
       </developer>
    </developers>
  )
)

lazy val noPublishSettings = Seq(
  publishArtifact := false,
  publish := (),
  publishLocal := ()
)

lazy val commonSettings = Seq(
  scalacOptions ++= Seq("-deprecation", "-feature", "-Xlint", "-Xfatal-warnings"),
  version := "1.0.0",
  scalaVersion := scala210Version,
  crossScalaVersions := Seq(
    scala210Version,
    scala211Version
  ),
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
