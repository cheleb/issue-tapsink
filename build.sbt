val scala3Version = "3.2.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "issue-tapsink",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "dev.zio" %% "zio-streams" % "2.0.5",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
