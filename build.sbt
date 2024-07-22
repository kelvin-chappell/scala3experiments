val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3experiments",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
