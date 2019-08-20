package com.scalagrpcakka.questionaire.storage.mysql

import com.scalagrpcakka.questionaire.storage.Storage
import explori.Tables._
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.concurrent.duration.Duration

class MysqlImpl extends Storage with Connectable {

  def getQuestion(id: Int): Future[Option[SurveydatastrRow]]= {
    //    try {
    //      // val resultFuture: Future[_] = { ... }
    //      Await.result(resultFuture, Duration.Inf)
    //      lines.foreach(Predef.println _)
    //    } finally db.close

    //    val q = Surveydatastr.join(Survey).on(_.id === _.id)
    //      .map { case (co, cp) => (co.name, cp.name) }

    val db = Database.forConfig("mysql")

    val duration = Duration(100, "seconds")

    val q = Surveydatastr
      .map(_.value)
      .filter(_.id === id)
      .result.headOption

//    db.run(q.map(s => println(s.get.str)))

//    Await.result(db.run(q), duration)

    db.run(q)


  }

}
