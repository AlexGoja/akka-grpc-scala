package com.scalagrpcakka.questionaire.storage.mysql

import com.typesafe.config.ConfigFactory
import slick.jdbc.MySQLProfile.api._


trait Connectable {

  object connection {
    def connect(path: String): Database = {
      val db = Database.forConfig(path, config = ConfigFactory.load("src/main/resources/application.conf"))
      sys.addShutdownHook(db.close())
      db
    }
  }

}
