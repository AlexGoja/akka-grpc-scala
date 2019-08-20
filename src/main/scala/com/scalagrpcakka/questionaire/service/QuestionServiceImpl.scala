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
//    val questionFuture: Future[Option[(SurveydatastrRow, SurveydataoptRow)]] = storage.getQuestion(in.id)
//    questionFuture.map {
//      result =>
//        if (result.isEmpty) new QuestionResponse(question = "None")
//        else new QuestionResponse(question = result.get._1.str.get)
//    }

    val questionFuture: Future[Unit] = storage.getQuestion(in.id)

    Future.successful(new QuestionResponse(question = "None"))
  }

}
