package shootail.view

import scalafx.collections.ObservableBuffer
import scalafx.scene.control.{Button, Label, ListCell, ListView, TableColumn, TableView}
import scalafx.scene.layout.{HBox, VBox}
import scalafxml.core.macros.sfxml
import shootail.MainApp
import shootail.MainApp.{gameLevel, selectedCocktail}
import shootail.model.{Cocktail, Level}
import scalafx.Includes._
import scalafx.scene.image.ImageView
import shootail.util.CommonUtil

@sfxml
class LevelSelectionController(
                                private val cocktailListView: ListView[Cocktail],
                                private val selectedCocktailLabel: Label,
                                private val ingredientHBox: HBox,
                                private val recipeLabel: Label,
                                private val selectedLevelLabel: Label,
                                private val timeLabel: Label,
                                private val easyButton: Button,
                                private val normalButton: Button,
                                private val hardButton: Button,
                                private val hellButton: Button
                              ) {

  cocktailListView.items = CommonUtil.listToObservableBuffer(Cocktail.cocktails)

  cocktailListView.selectionModel.value.selectedItem.onChange((_, _, c) => selectCocktail(c))

  cocktailListView.selectionModel.value.select(0)

  easyButton.onAction = (_) => selectLevel(Level.Easy)
  normalButton.onAction = (_) => selectLevel(Level.Normal)
  hardButton.onAction = (_) => selectLevel(Level.Hard)
  hellButton.onAction = (_) => selectLevel(Level.Hell)

  def selectLevel(level: Level): Unit = {
    MainApp.buttonClickedSound()
    gameLevel = level
    updateLabel()
  }

  def handleBack(): Unit = MainApp.showHome()

  def handleStart(): Unit = {
    MainApp.buttonClickedSound()
    MainApp.showGame()
  }

  def updateLabel(): Unit = {
    selectedLevelLabel.text = gameLevel.label
    timeLabel.text = gameLevel.getTimerString()
  }

  def selectCocktail(cocktail: Cocktail): Unit = {
    selectedCocktail = cocktail
    selectedCocktailLabel.text = selectedCocktail.name.value

    ingredientHBox.children.clear()
    selectedCocktail.ingredients.foreach(ingredient => {
      val cocktailImageView: ImageView = new ImageView(ingredient.img) {
        fitWidth = 50
        preserveRatio = true
      }
      ingredientHBox.children.add(cocktailImageView)
    })
    recipeLabel.text = ""

    for(i <- 0 until selectedCocktail.recipe.length) {
      val step: String = selectedCocktail.recipe(i)
      recipeLabel.text = s"${recipeLabel.text.value}${i+1}. $step\n\n"
    }

  }
  updateLabel()
}