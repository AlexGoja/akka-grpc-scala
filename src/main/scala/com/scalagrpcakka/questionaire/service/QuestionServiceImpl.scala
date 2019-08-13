package com.scalagrpcakka.questionaire.service

import akka.stream.Materializer
import com.scalagrpcakka.questionaire.storage.Storage
import com.scalagrpcakka.questionaire.{QuestionRequest, QuestionResponse, QuestionService}

import scala.concurrent.Future

class QuestionServiceImpl(materializer: Materializer, storage: Storage) extends QuestionService {

  private implicit val mat: Materializer = materializer

  /**
   * Obtains the question with a given id
   *
   */
  override def getQuestion(in: QuestionRequest): Future[QuestionResponse] = {
    val question: Unit = storage.getQuestion(in.id)
    Future.successful(QuestionResponse(s"Question:, ${in.id}"))
  }

}
