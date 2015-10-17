android.Plugin.androidBuild

lazy val commonSettings = Seq(
    organization := "mx.udlap",
    scalaVersion := "2.11.7",
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    fork in run := true
)

lazy val sub-project = (project in file("sub-project-dir")).
    settings(commonSettings: _*).
    settings(
        name := "sub-project-name",
        version := "0.1",
        mainClass in (Compile, run) := Some("sub-project-package.Main")
    )
lazy val root = (project in file(".")).
    settings(commonSettings: _*).
    settings(
        name := """project-name""",
        version := "1.0",
        platformTarget in Android := "android-22",
        proguardScala in Android := true,
        useProguard in Android := true,
        proguardOptions in Android ++= Seq(
          "-ignorewarnings",
          "-keep class scala.Dynamic")
    ).
    aggregate(sub-project).
    dependsOn(sub-project)
