lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3experiments",
    scalaVersion := "3.2.2",
    scalacOptions ++= Seq(
      "-deprecation",
    ),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.4",
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.typelevel" %% "munit-cats-effect-3" % "1.0.6" % Test,
      "org.typelevel" %% "scalacheck-effect" % "1.0.3" % Test
    )
  )
