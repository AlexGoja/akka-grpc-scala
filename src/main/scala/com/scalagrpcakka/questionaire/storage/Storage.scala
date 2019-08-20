package com.scalagrpcakka.questionaire.storage

import scala.concurrent.Future

trait Storage {
  def getQuestion(id: String) : Future[Unit]
}
