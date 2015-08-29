name := "spark-avro"

organization := "com.databricks"

scalaVersion := "2.10.5"

crossScalaVersions := Seq("2.10.5", "2.11.7")

spName := "databricks/spark-avro"

sparkVersion := "1.4.1"

val testSparkVersion = settingKey[String]("The version of Spark to test against.")

testSparkVersion := sys.props.getOrElse("spark.testVersion", sparkVersion.value)

resolvers += "Spark 1.5.0 RC2 Staging" at "https://repository.apache.org/content/repositories/orgapachespark-1141"

spAppendScalaVersion := true

spIncludeMaven := true

spIgnoreProvided := true

sparkComponents := Seq("sql")

libraryDependencies ++= Seq(
  "org.apache.avro" % "avro" % "1.7.6" exclude("org.mortbay.jetty", "servlet-api"),
  "org.apache.avro" % "avro-mapred" % "1.7.6"  classifier "hadoop2"  exclude("org.mortbay.jetty", "servlet-api"),
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "commons-io" % "commons-io" % "2.4" % "test"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % testSparkVersion.value % "test",
  "org.apache.spark" %% "spark-sql" % testSparkVersion.value % "test"
)

publishMavenStyle := true

pomExtra :=
  <url>https://github.com/databricks/spark-avro</url>
  <licenses>
    <license>
      <name>Apache License, Verision 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:databricks/spark-avro.git</url>
    <connection>scm:git:git@github.com:databricks/spark-avro.git</connection>
  </scm>
  <developers>
    <developer>
      <id>marmbrus</id>
      <name>Michael Armbrust</name>
      <url>https://github.com/marmbrus</url>
    </developer>
    <developer>
      <id>vlyubin</id>
      <name>Volodymyr Lyubinets</name>
      <url>https://github.com/vlyubin</url>
    </developer>
  </developers>

// Display full-length stacktraces from ScalaTest:
testOptions in Test += Tests.Argument("-oF")

bintrayReleaseOnPublish in ThisBuild := false
