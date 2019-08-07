package com.scalagrpcakka.questionaire
//
//import akka.actor.ActorSystem
//import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
//import akka.http.scaladsl.{Http, HttpConnectionContext}
//import akka.stream.{ActorMaterializer, Materializer}
//import com.typesafe.config.ConfigFactory
//
//import scala.concurrent.{ExecutionContext, Future}
//
//object QuestionServer {
//
//  def main(args: Array[String]): Unit = {
//    // Enable HTTP/2 in ActorSystem's config
//    val conf = ConfigFactory
//      .parseString("akka.http.server.preview.enable-http2 = on")
//      .withFallback(ConfigFactory.defaultApplication())
//    val system = ActorSystem("QuestionSystem", conf)
//    new QuestionServer(system).run()
//    // ActorSystem threads will keep the app alive until `system.terminate()` is called
//  }
//}
//
//class QuestionServer(system: ActorSystem) {
//
//  def run(): Future[Http.ServerBinding] = {
//    // Akka boot up code
//    implicit val sys: ActorSystem = system
//    implicit val mat: Materializer = ActorMaterializer()
//    implicit val ec: ExecutionContext = sys.dispatcher
//
//    // Create service handlers
//    val service: HttpRequest => Future[HttpResponse] =
//      QuestionServiceHandler(new QuestionServiceImpl())
//
//    // Bind service handler servers to localhost:8080
//    val binding = Http().bindAndHandleAsync(
//      service,
//      interface = "127.0.0.1",
//      port = 8080,
//      connectionContext = HttpConnectionContext())
//
//    // report successful binding
//    binding.foreach { binding =>
//      println(s"gRPC server bound to: ${binding.localAddress}")
//    }
//
//    binding
//  }
//}

import akka.actor.ActorSystem
import akka.http.scaladsl.UseHttp2.Always
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.{Http, HttpConnectionContext}
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.ConfigFactory

import scala.concurrent.{ExecutionContext, Future}

object QuestionServer {

  def main(args: Array[String]): Unit = {
    // important to enable HTTP/2 in ActorSystem's config
    val conf = ConfigFactory.parseString("akka.http.server.preview.enable-http2 = on")
      .withFallback(ConfigFactory.defaultApplication())
    val system: ActorSystem = ActorSystem("HelloWorld", conf)
    new QuestionServer(system).run()
  }
}

class QuestionServer(system: ActorSystem) {

  def run(): Future[Http.ServerBinding] = {
    implicit val sys = system
    implicit val mat: Materializer = ActorMaterializer()
    implicit val ec: ExecutionContext = sys.dispatcher

    val service: HttpRequest => Future[HttpResponse] = {
      QuestionServiceHandler(new QuestionServiceImpl(mat))
    }


    val bound = Http().bindAndHandleAsync(
      service,
      interface = "127.0.0.1",
      port = 8080,
      // Needed until akka-http 10.1.5 see  https://github.com/akka/akka-http/issues/2168
      parallelism = 256,
      connectionContext = HttpConnectionContext(http2 = Always)
    )

    bound.foreach { binding =>
      println(s"gRPC server bound to: ${binding.localAddress}")
    }

    bound
  }
}