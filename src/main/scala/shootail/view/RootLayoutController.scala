package shootail.view

import shootail.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController() {
  def handleClose(): Unit = {
    System.exit(0);
  }

  def toggleBgm(): Unit = {
    MainApp.toggleBgm()
  }
}
