package shootail.view

import scalafxml.core.macros.sfxml
import shootail.MainApp

@sfxml
class RulesController() {
  def handleBack(): Unit = MainApp.showHome()
}