package com.scalagrpcakka.questionaire

import akka.actor.ActorSystem
import akka.grpc.GrpcClientSettings
import akka.stream.ActorMaterializer

import scala.util.{Failure, Success}

object QuestionClient {

  def main(args: Array[String]): Unit = {
    implicit val sys = ActorSystem("HelloWorldClient")
    implicit val mat = ActorMaterializer()
    implicit val ec = sys.dispatcher

    val client = QuestionServiceClient(GrpcClientSettings.fromConfig("question.QuestionService"))

    val names =
      if (args.isEmpty) List("Alice", "Bob")
      else args.toList

    names.foreach(singleRequestReply)


    def singleRequestReply(id: String): Unit = {
      println(s"Performing request: $id")
      val reply = client.getQuestion(QuestionRequest(id))
      reply.onComplete {
        case Success(msg) =>
          println(msg)
        case Failure(e) =>
          println(s"Error: $e")
      }
    }

  }

}
