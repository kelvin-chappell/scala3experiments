lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3experiments",
    scalaVersion := "3.3.5",
    scalacOptions ++= Seq(
      "-deprecation"
    ),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.6.0",
      "org.scalameta" %% "munit" % "1.1.0" % Test,
      "org.typelevel" %% "munit-cats-effect" % "2.0.0" % Test,
      "org.typelevel" %% "scalacheck-effect" % "1.0.4" % Test
    )
  )
