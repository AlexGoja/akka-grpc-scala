name := "explori-akka-grpc"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.6.0-M5"
lazy val akkaGrpcVersion = "0.7.0"

enablePlugins(AkkaGrpcPlugin)

// ALPN agent
enablePlugins(JavaAgent)
javaAgents += "org.mortbay.jetty.alpn" % "jetty-alpn-agent" % "2.0.9" % "runtime"

//PB.targets in Compile := Seq(
//  scalapb.gen() -> (sourceManaged in Compile).value
//)

//PB.generate in Compile := (PB.generate in Compile).dependsOn(extractProtos).value
//
//PB.protoSources in Compile += resourceManaged.value / "googleapis-master"
//
//lazy val extractProtos = Def.task {
//  if (!(resourceManaged.value / "googleapis-master").exists) {
//    val zipUrl = "https://github.com/googleapis/googleapis/archive/master.zip"
//
//    println(s"Unzipping $zipUrl.")
//    IO.unzipURL(
//      from=url(zipUrl),
//      filter=(
//          "googleapis-master/google/api/*" |
//          "googleapis-master/google/logging/*" |
//          "googleapis-master/google/longrunning/*" |
//          "googleapis-master/google/rpc/*" |
//          "googleapis-master/google/type/*"
//        ),
//      toDirectory=resourceManaged.value)
//  }

//  if (!(resourceManaged.value / "protobuf-master").exists) {
//    val protoUrl = "https://github.com/protocolbuffers/protobuf/archive/master.zip"
//    println(s"Unzipping $protoUrl.")
//    IO.unzipURL(
//      from=url(protoUrl),
//      filter=(
//          "protobuf-master/src/google/protobuf/*"
//        ),
//      toDirectory=resourceManaged.value)
//  }
//}

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http"   % "10.1.5",
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.9",
//  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
//  "com.thesamet.scalapb" %% "scalapb-json4s" % "0.9.3",
//  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
//  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.google.api.grpc" % "googleapis-common-protos" % "0.0.3" % "protobuf",
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "test",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
