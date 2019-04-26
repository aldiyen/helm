val PaytronixNexus = "https://nexus.corp.paytronix.com/nexus/"

val PaytronixSnapshots = "paytronix-snapshots" at PaytronixNexus + "content/repositories/snapshots"
val PaytronixReleases = "paytronix-releases" at PaytronixNexus + "content/repositories/releases"
val LocalMaven = "Local Maven" at Path.userHome.asFile.toURI.toURL + ".m2/repository"

resolvers in ThisBuild ++= Seq(LocalMaven, PaytronixSnapshots, PaytronixReleases)

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishConfiguration := publishConfiguration.value.withOverwrite(true)

publishTo := {
  if (isSnapshot.value) Some(PaytronixSnapshots)
  else                  Some(PaytronixReleases)
}

organization in Global := "io.getnelson.helm"

crossScalaVersions in Global := Seq("2.12.4", "2.11.12")

scalaVersion in Global := crossScalaVersions.value.head

lazy val helm = project.in(file(".")).aggregate(core, http4s)

lazy val core = project

lazy val http4s = project.dependsOn(core)

//enablePlugins(DisablePublishingPlugin)
