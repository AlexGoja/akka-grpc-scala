package com.scalagrpcakka.questionaire.storage

trait Storage {
  def getQuestion(id: String)
}
