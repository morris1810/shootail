package shootail.view

import scalafx.animation.RotateTransition
import scalafx.geometry.Point3D
import scalafx.scene.control.{Label, TextField, Button}
import scalafx.scene.transform.Rotate
import scalafx.util.Duration
import shootail.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class HomeController(
                      private val musicLabel: Label,
                      private val nameTextField: TextField
                    ) {

  var playButtonDisabled: Boolean = false

  nameTextField.text = MainApp.playerName

  nameTextField.text.onChange((_, _, c) => {
    playButtonDisabled = c.isEmpty
    MainApp.playerName = c
  })

  def handleBgm(): Unit = {
    MainApp.buttonClickedSound()
    if (MainApp.toggleBgm()) {
      musicLabel.text = "ON"
    } else {
      musicLabel.text = "OFF"
    }
  }

  def handlePlay(): Unit = {
    MainApp.buttonClickedSound()
    if(!playButtonDisabled) {
      MainApp.showLevelSelection()
    }
  }

  def handleRank(): Unit = {
    MainApp.buttonClickedSound()
    MainApp.showRank()
  }

  def handleRules(): Unit = {
    MainApp.buttonClickedSound()
    MainApp.showRules()
  }

}
