package com.scalagrpcakka.questionaire

import akka.stream.Materializer

import scala.concurrent.Future

class QuestionServiceImpl(materializer: Materializer) extends QuestionService {
  private implicit val mat: Materializer = materializer
  /**
   * Obtains the question with a given id
   *
   */
  override def getQuestion(in: QuestionRequest): Future[QuestionResponse] = {
    println(s"Executed mf ${in.id}")
    val s = new QuestionResponse("someId")
    System.out.println(s.question)
    Future.successful(QuestionResponse(s"Hello, ${in.id}"))
  }

}
