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

libraryDependencies ++= Seq(
  "io.argonaut"                %% "argonaut"          % "6.2.1",
  "org.typelevel"              %% "cats-free"         % "1.6.0",
  "org.typelevel"              %% "cats-effect"       % "1.2.0"
)

addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.5" cross CrossVersion.binary)

enablePlugins(ScalaTestPlugin, ScalaCheckPlugin)

scalaTestVersion := "3.0.5"

scalaCheckVersion := "1.13.5"
