name := "scalding-template"

version := "1.0"

scalaVersion := "2.10.5"

resolvers ++= Seq("maven.org" at "http://repo2.maven.org/maven2",
  "conjars.org" at "http://conjars.org/repo"
)

//mainClass in assembly := Some("com.twitter.scalding.Tool")
assemblyJarName in assembly := "scalding-template.jar"

val hadoopVersion = "2.7.1"
val scaldingVersion = "0.15.0"
val cascadingVersion = "3.0.3"

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.3.0",
  "cascading" % "cascading-core" % cascadingVersion,
  "cascading" % "cascading-local" % cascadingVersion,
  "cascading" % "cascading-hadoop" % cascadingVersion,
  //"cascading" % "cascading-hadoop2-mr1" % cascadingVersion,
  "com.twitter" %% "scalding-core" % scaldingVersion excludeAll ExclusionRule(organization = "cascading"),
  // include Hadoop runtime to run locally
  //"org.apache.hadoop" % "hadoop-common" % hadoopVersion,
  //"org.apache.hadoop" % "hadoop-mapreduce-client-core" % hadoopVersion
  //replace above with the following to exclude Hadoop from assembly jar
  "org.apache.hadoop" % "hadoop-common" % hadoopVersion % "provided",
  "org.apache.hadoop" % "hadoop-mapreduce-client-core" % hadoopVersion % "provided"
)


assemblyMergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
  case m if m.startsWith("META-INF") => MergeStrategy.discard
  case m if m.contains("LICENSE") => MergeStrategy.discard
  //case x if x.contains("com/twitter/bijection/GeneratedTupleCollectionInjections") => MergeStrategy.discard
  case _ => MergeStrategy.first
}