package com.scalagrpcakka.questionaire.storage.mysql

import com.scalagrpcakka.questionaire.storage.Storage
import explori.Tables._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class MysqlImpl extends Storage with Connectable {

  def getQuestion(id: String): Unit = {
    //    try {
    //      // val resultFuture: Future[_] = { ... }
    //      Await.result(resultFuture, Duration.Inf)
    //      lines.foreach(Predef.println _)
    //    } finally db.close

    //    val q = Surveydatastr.join(Survey).on(_.id === _.id)
    //      .map { case (co, cp) => (co.name, cp.name) }

    val db = Database.forConfig("mysql")

    val duration = Duration(100, "seconds")
    val q = Surveydatastr.filter(_.str === id)
    println(q)
    Await.result(db.run(q.result).map { result => println(result) }, duration)

  }

}
