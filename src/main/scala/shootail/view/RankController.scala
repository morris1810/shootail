package shootail.view

import scalafx.scene.control.{TableColumn, TableView}
import scalafxml.core.macros.sfxml
import shootail.MainApp
import shootail.model.Record
import shootail.util.CommonUtil
import scalafx.Includes._
import scalafx.beans.property.StringProperty


@sfxml
class RankController(
                    val rankTable: TableView[Record],
                    val nameTableColumn: TableColumn[Record, String],
                    val scoreTableColumn: TableColumn[Record, String],
                    val cocktailTableColumn: TableColumn[Record, String],
                    val levelTableColumn: TableColumn[Record, String]
                    ) {

  val records: List[Record] = Record.getRecords(10)

  rankTable.items = CommonUtil.listToObservableBuffer(records)

  nameTableColumn.cellValueFactory = {
    _.value.name
  }
  scoreTableColumn.cellValueFactory = (x) => {
    new StringProperty(x.value.score.value.toString)
  }
  cocktailTableColumn.cellValueFactory = {
    _.value.cocktail
  }
  levelTableColumn.cellValueFactory = {
    _.value.level
  }

  def handleBack(): Unit = MainApp.showHome()
}