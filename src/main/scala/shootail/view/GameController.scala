package shootail.view
import scalafx.Includes._
import scalafx.animation.{FadeTransition, Interpolator, KeyFrame, KeyValue, RotateTransition, Timeline}
import scalafx.scene.control.Label
import scalafx.scene.ImageCursor
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.{AnchorPane, HBox}
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.util.Duration
import scalafxml.core.macros.sfxml
import shootail.MainApp._
import shootail.model.{Bomb, Fruit, Ingredient, Level, Liquor, Record, Shootable, SoftDrink, Syrup}
import shootail.util.CommonUtil



@sfxml
class GameController(
                      private val shootingArea: AnchorPane,
                      private val bgLight: AnchorPane,
                      private val ingredientHBox: HBox,
                      private val scoreLabel: Label,
                      private val timeLabel: Label
                    ) {

  var score: Int = 0

  val aimImg: Image = new Image(getClass.getResourceAsStream("../images/aim_cursor.png"))
  val aimImgCursor: ImageCursor = new ImageCursor(aimImg, (aimImg.width / 2).toDouble, (aimImg.height / 2).toDouble)

  val shotEffectImg: Image = new Image(getClass.getResourceAsStream("../images/shot_effect.png"))
  val gunShotEffectAudio: Media = new Media(getClass.getResource("../audios/9mm_pistol_shoot.mp3").toString)

  var timeCount: Duration = gameLevel.time


  // Make the bg light keep flashing effect
  new FadeTransition(Duration(700), bgLight) {
    fromValue = 0.6
    toValue = 0.7
    cycleCount = FadeTransition.Indefinite
    interpolator = Interpolator.EaseBoth
    autoReverse = true
  }.play()

  // Show the ingredient list
  selectedCocktail.ingredients.foreach(ingredient => {
    val cocktailImageView: ImageView = new ImageView(ingredient.img) {
      fitWidth = 20
      preserveRatio = true
    }
    ingredientHBox.children.add(cocktailImageView)
  })

  shootingArea.onMouseClicked = (e) => gunShotEffect(e)


  // Generate the items included in the Ingredient for the cocktail
  val ingredientList: List[Ingredient] = selectedCocktail.ingredients
  val ingredientDelay: Double = calcDelay(gameLevel.ingredientCount)

  for (i <- 0 until gameLevel.ingredientCount) {
    var randomIndex: Int = (math.random() * ingredientList.length).toInt
    while (!ingredientList(randomIndex).isInstanceOf[Shootable]) {
      randomIndex = (math.random() * ingredientList.length).toInt
    }
    generateTarget(ingredientList(randomIndex).asInstanceOf[Shootable], gameLevel.speed, Duration(ingredientDelay * i))
  }

  // Generate the items not included in the Ingredient for the cocktail
  val nonIngredientLiquors: List[Ingredient] = Liquor.liquors.diff(selectedCocktail.ingredients)
  val nonIngredientSoftDrinks: List[Ingredient] = SoftDrink.softDrinks.diff(selectedCocktail.ingredients)
  val nonIngredientSyrups: List[Ingredient] = Syrup.syrups.diff(selectedCocktail.ingredients)
  val nonIngredientFruits: List[Ingredient] = Fruit.fruits.diff(selectedCocktail.ingredients)
  var nonIngredientList: List[Ingredient] = List.concat(
    nonIngredientLiquors,
    nonIngredientSoftDrinks,
    nonIngredientSyrups,
    nonIngredientFruits
  )
  val nonIngredientDelay: Double = calcDelay(gameLevel.nonIngredientCount)

  for (j <- 0 until gameLevel.nonIngredientCount) {
    var randomIndex: Int = (math.random() * nonIngredientList.length).toInt
    while (!nonIngredientList(randomIndex).isInstanceOf[Shootable]) {
      randomIndex = (math.random() * nonIngredientList.length).toInt
    }
    generateTarget(nonIngredientList(randomIndex).asInstanceOf[Shootable], gameLevel.speed, Duration(nonIngredientDelay * j))
  }

  // Generate Bomb
  val bombDelay: Double = calcDelay(gameLevel.bombCount)

  for (k <- 0 until gameLevel.bombCount) {
    generateTarget(Bomb.Bomb, gameLevel.speed, Duration(bombDelay * k))
  }


  // Timer
  new Timeline() {
    cycleCount = gameLevel.time.toSeconds.toInt
    keyFrames.add(KeyFrame(Duration(1000), null, (_) => countDown()))
    onFinished = (_) => endGame()
  }.play()


  def gunShotEffect(e: MouseEvent): Unit = {
    // To make sure the cursor always be the Aim cursor
    shootingArea.cursor = aimImgCursor

    //Add the ImageView for shot effect
    val shootEffect = new ImageView(shotEffectImg) {
      // Instead of set the position on x y, use translate can avoid the Image expand the size of Anchor Pane
      x = 0
      y = 0
      fitHeight = 50
      fitWidth = 50
      translateX = e.getX - (this.fitWidth / 2).toDouble
      translateY = e.getY - (this.fitHeight / 2).toDouble
      disable = true
    }
    new FadeTransition(Duration(500), shootEffect) {
      fromValue = 1
      toValue = 0
      delay = Duration(500)
      cycleCount = 1
      // Remove the ImageView Object after the Fade Animation done
      onFinished = (_) => shootingArea.children.remove(shootEffect)
    }.play()
    shootingArea.children.add(shootEffect)

    //play the shot effect sound
    new MediaPlayer(gunShotEffectAudio) {
      autoPlay = true
      cycleCount = 1
    }
  }

  def itemShotEffectSound(item: Shootable): Unit = {
    new MediaPlayer(item.shotAudio) {
      autoPlay = true
      cycleCount = 1
    }
  }

  def updateScoreLabel(): Unit = {
    scoreLabel.text = score.toString
  }

  def calcDelay(itemCount: Int): Double = {
    gameLevel.speed.toMillis + ((gameLevel.time.toMillis - (gameLevel.speed.toMillis * itemCount)) / (itemCount - 1))
  }

  def generateTarget(target: Shootable, speed: Duration = Duration(1000), targetDelay: Duration = Duration(1000)): Unit = {
    val randBool: Boolean = math.random() >= 0.5

    val targetImg = target match {
      case ingredient: Ingredient =>
        ingredient.img
      case bomb: Bomb =>
        bomb.img
    }

    val targetSize = target match {
      case ingredient: Ingredient =>
        ingredient.size * 0.75
      case bomb: Bomb =>
        bomb.size * 0.75
    }

    val targetContainer: AnchorPane = new AnchorPane() {
      prefHeight = targetSize
      //      prefWidth = shootingArea.width.value / 2
      rotate = if (randBool) 180 else 0
      translateY = targetSize * 1.2
    }

    val item: ImageView = new ImageView(targetImg) {
      fitHeight = targetSize
      //calculate ratio
      fitWidth = targetSize * (targetImg.width / targetImg.height).toDouble
      x = 0
      y = 0
      if (randBool) {
        translateX = math.random() * 200
      }
      else {
        translateX = - math.random() * 200
      }


      onMouseClicked = (_) => {
        itemShotEffectSound(target)
        this.opacity = 0
        this.disable = true
        if (ingredientList.contains(target)) {
          score += 2
        } else if (nonIngredientList.contains(target)) {
          score -= 1
        } else if (target.isInstanceOf[Bomb]) {
          endGame()
        }
        updateScoreLabel()
        shootingArea.children.remove(targetContainer)
      }
    }
    targetContainer.children.add(item)

    AnchorPane.setBottomAnchor(targetContainer, 0)
    AnchorPane.setLeftAnchor(targetContainer, 0)
    AnchorPane.setRightAnchor(targetContainer, 0)

    //Animation
    new RotateTransition(speed, targetContainer) {
      fromAngle = if (randBool) 180 else 0
      toAngle = if (randBool) 0 else 180
      autoReverse = true
      interpolator = Interpolator.Linear
      cycleCount = 1
      delay = targetDelay
      onFinished = (_) => {
        if (!gameLevel.equals(Level.Easy) && ingredientList.contains(target)) {
          score -= 1
        }
        updateScoreLabel()
        shootingArea.children.remove(targetContainer)
      }
    }.play()

    new RotateTransition(speed / 2, item) {
      fromAngle = if (randBool) 360 else 0
      toAngle = if (randBool) 0 else 360

      autoReverse = false
      interpolator = Interpolator.Linear
      cycleCount = RotateTransition.Indefinite
    }.play()

    shootingArea.children.add(targetContainer)
  }

  def countDown(): Unit = {
    timeCount -= Duration(1000)
    updateTimeLabel()
  }

  def updateTimeLabel(): Unit = {
    timeLabel.text = CommonUtil.durationToTimerString(timeCount)
  }

  def endGame(): Unit =  {
    shootingArea.children.clear()
    gameResult = new Record(playerName, score, selectedCocktail.name.value, gameLevel.label)
    gameResult.save()
    showResult()
  }

}