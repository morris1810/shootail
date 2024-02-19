package shootail

import javafx.scene.layout.BorderPane
import scalafx.scene.text.Font
import scalafx.application.JFXApp
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import javafx.{scene => jfxs}
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafx.scene.media.MediaPlayer.Status
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.util.Duration
import shootail.model.{Bomb, Cocktail, Level, Record}
import shootail.util.Database

import java.net.URL

object MainApp extends JFXApp {

  Database.setUpDB()

  var playerName: String = "Anonymous"
  var gameLevel: Level = Level.Normal
  var selectedCocktail: Cocktail = Cocktail.cocktails.head  // By default set to first cocktail
  var gameResult: Record = Record.empty

  // Read .ttf font file, to used in css file
  Font.loadFont(getClass.getResourceAsStream("fonts/PixelifySans-Regular.ttf"), 12)
  Font.loadFont(getClass.getResourceAsStream("fonts/PixelifySans-Bold.ttf"), 12)


  // GAME Background Music(BGM)
  val bgm: Media = new Media(getClass.getResource("audios/bgm.mp3").toString) {}
  val bgmPlayer: MediaPlayer = new MediaPlayer(bgm) {
    autoPlay = true
    cycleCount = MediaPlayer.Indefinite
    volume = 0.5
  }

  val buttonClickSound: Media = new Media(getClass.getResource("audios/spring-click-sound.mp3").toString) {}
  val buttonClickPlayer: MediaPlayer = new MediaPlayer(buttonClickSound) {
    autoPlay = false
    cycleCount = 1
  }

  //==========================
  // Root
  //==========================
  val rootResource: URL = getClass.getResource("view/RootLayout.fxml")
  val loader: FXMLLoader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots: BorderPane = loader.getRoot[jfxs.layout.BorderPane]()


  stage = new PrimaryStage {
    title = "Shootail"
    icons += new Image(getClass.getResourceAsStream("images/shootail_logo.png"))
    minWidth = 800
    minHeight = 550

    scene = new Scene {
      root = roots
      stylesheets += getClass.getResource("view/MainTheme.css").toString
    }
  }


  //==========================
  // Function
  //==========================
  def showHome(): Unit = {
    val resource = getClass.getResource("view/Home.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showRank(): Unit = {
    val resource = getClass.getResource("view/Rank.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showRules(): Unit = {
    val resource = getClass.getResource("view/Rules.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showGame(): Unit = {
    val resource = getClass.getResource("view/Game.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showLevelSelection(): Unit = {
    val resource = getClass.getResource("view/LevelSelection.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showResult(): Unit = {
    val resource = getClass.getResource("view/Result.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def toggleBgm(): Boolean = {
    val playerStatus: String = bgmPlayer.status.value.toString;
    val isPlaying: Boolean = Status.Playing.toString.equals(playerStatus);
    if (isPlaying) bgmPlayer.pause() else bgmPlayer.play()
    !isPlaying
  }

  def buttonClickedSound(): Unit = {
    buttonClickPlayer.stop()
    buttonClickPlayer.play()
  }

  // By default show the Home page
  showHome()
}


