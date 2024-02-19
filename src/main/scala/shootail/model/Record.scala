package shootail.model

import scalafx.beans.property.{IntegerProperty, StringProperty}
import scalikejdbc._
import shootail.util.Database

import scala.util.Try


class Record(_name: String, _score: Int, _cocktail: String, _level: String) {
  val name: StringProperty = new StringProperty(_name)
  val score: IntegerProperty = IntegerProperty(_score)
  val cocktail: StringProperty = new StringProperty(_cocktail)
  val level: StringProperty = new StringProperty(_level)

  def save(): Try[Int] = {
    Try(DB autoCommit { implicit session =>
      sql"""
           insert into record (
                               name,
                               score,
                               cocktail,
                               level
                               ) values
                                        (
                                         ${name.value},
                                         ${score.value},
                                         ${cocktail.value},
                                         ${level.value}
                                         )
         """.update.apply()
    })
  }

}


object Record extends Database{
  val empty = new Record("", 0, null, null)

  def initializeTable(): Boolean = {
    DB autoCommit { implicit session =>
      sql"""
           create table record (
               id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
               name varchar(255),
               score int,
               cocktail varchar(255),
               level varchar(255)
           )

         """.execute.apply()
    }
  }

  def getRecords(count: Int): List[Record] = {
    DB readOnly{implicit session =>
      sql"select * from record order by score desc fetch first ${count} rows only".map(rs => {
        new Record(
          rs.string("name"),
          rs.int("score"),
          rs.string("cocktail"),
          rs.string("level")
        )
      }).list.apply()
    }
  }

}


