package com.scalagrpcakka.questionaire.storage

import explori.Tables._

import scala.concurrent.Future

trait Storage {
  def getQuestion(id: Int) : Future[Option[SurveydatastrRow]]
}
