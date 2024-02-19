package shootail.util

import scalikejdbc._
import shootail.model.Record

trait Database {
  var derbyDriverClassName: String = "org.apache.derby.jdbc.EmbeddedDriver"

  val dbURL: String = "jdbc:derby:shootailDB;create=true;"

  Class.forName(derbyDriverClassName)

  ConnectionPool.singleton(dbURL, "shootail", "shootail")

  implicit val session: DBSession = AutoSession
}

object Database extends Database {
  def setUpDB(): Unit = {
    if(!hasDBInitialize()) {
      Record.initializeTable()
    }

  }

  def hasDBInitialize(): Boolean = {
    DB getTable "Record" match {
      case Some(x) => true
      case None => false
    }
  }
}