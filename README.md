# NOTE

Note that stubborn in this repo is no longer receiving updates. All new pull
requests should be made to https://github.com/salesforce/stubborn

# Stubborn

> Stubborn: having or showing dogged determination not to change one's attitude
> or position on something.

A scala library for performing retries on actions that might fail.

## Configuration

Add the Sonatype.org Releases repo as a resolver in your `build.sbt` or `Build.scala` as appropriate.

```scala
resolvers += "Sonatype.org Releases" at "https://oss.sonatype.org/content/repositories/releases/"
```

Add stubborn as a dependency in your `build.sbt` or `Build.scala` as appropriate.

```scala
libraryDependencies ++= Seq(
  // Other dependencies ...
  "com.krux" %% "stubborn" % "2.0.0"
)
```

## Scala Versions

This project is compiled, tested, and published for the following Scala versions:

1. 2.13.x
2. 2.12.x

## Usage

See examples in `examples` [directory](https://github.com/krux/stubborn/tree/master/examples).

## License

Stubborn is licensed under [APL 2.0](LICENSE).
