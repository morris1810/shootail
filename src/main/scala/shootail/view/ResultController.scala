package shootail.view

import scalafx.scene.control.Label
import scalafxml.core.macros.sfxml
import shootail.MainApp
import shootail.MainApp.gameResult

@sfxml
class ResultController(
                      private val nameLabel: Label,
                      private val scoreLabel: Label,
                      private val cocktailLabel: Label,
                      private val levelLabel: Label
                      ) {
  nameLabel.text = gameResult.name.value
  scoreLabel.text = gameResult.score.value.toString
  cocktailLabel.text = gameResult.cocktail.value
  levelLabel.text = gameResult.level.value

  def handleDone(): Unit = {
    MainApp.buttonClickedSound()
    MainApp.showHome()
  }

}