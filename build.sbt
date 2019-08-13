name := "explori-akka-grpc"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.6.0-M5"
lazy val akkaGrpcVersion = "0.7.0"

enablePlugins(AkkaGrpcPlugin)

// ALPN agent
enablePlugins(JavaAgent)
javaAgents += "org.mortbay.jetty.alpn" % "jetty-alpn-agent" % "2.0.9" % "runtime"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http"   % "10.1.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.9",
  "com.google.api.grpc" % "googleapis-common-protos" % "0.0.3" % "protobuf",
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "mysql" % "mysql-connector-java" % "8.0.17",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.1"
)

/*
  Schema code generation for aws mysql rds db. For info go to http://slick.lightbend.com/doc/3.3.1/code-generation.html
 */
sourceGenerators in Compile += slick.taskValue // Automatic code generation on build

lazy val slick = taskKey[Seq[File]]("Generate Tables.scala")
slick := {
  val dir = (sourceManaged in Compile) value
  val outputFolder = dir / "slick"
  val url = "jdbc:mysql://exploridb.crq1ry8p3kvr.us-east-1.rds.amazonaws.com/explori" // connection info
  val profile = "slick.jdbc.MySQLProfile"
  val jdbcDriver = "com.mysql.cj.jdbc.Driver"
  val pkg = "explori"
  val user = "explori"
  val password = "goj08086502"

  val cp = (dependencyClasspath in Compile) value
  val s = streams value

  runner.value.run("slick.codegen.SourceCodeGenerator",
    cp.files,
    Array(profile, jdbcDriver, url, outputFolder.getPath, pkg, user, password),
    s.log).failed foreach (sys error _.getMessage)

  val file = outputFolder / pkg / "Tables.scala"

  Seq(file)
}
