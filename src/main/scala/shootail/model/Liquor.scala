package shootail.model

import scalafx.scene.image.Image
import scalafx.scene.media.Media

class Liquor(
              val name: String,
              val size: Double,
              val img: Image,
              val abv: Double   //abv = Alcohol by volume
            ) extends Ingredient with Shootable {
  val shotAudio: Media = new Media(getClass.getResource("../audios/bottle_smash.mp3").toString)
}

object Liquor {

  val Rum = new Liquor(
    "Bacardi Raspberry Rum",
    200,
    new Image(getClass.getResourceAsStream("../images/rum.png")),
    0.32
  )

  val Tequila = new Liquor(
    "Jose Cuervo Tequila ",
    200,
    new Image(getClass.getResourceAsStream("../images/tequila.png")),
    0.39
  )

  val Gin = new Liquor(
    "Bombay Gin ",
    200,
    new Image(getClass.getResourceAsStream("../images/gin.png")),
    0.4
  )

  val Whiskey = new Liquor(
    "Jim Beam Whiskey",
    200,
    new Image(getClass.getResourceAsStream("../images/whiskey.png")),
    0.4
  )

  val Vodka = new Liquor(
    "Absolut Vodka",
    200,
    new Image(getClass.getResourceAsStream("../images/vodka.png")),
    0.4
  )

  val Brandy = new Liquor(
    "Remy Martin XO Brandy",
    200,
    new Image(getClass.getResourceAsStream("../images/brandy.png")),
    0.4
  )

  val Cointreau = new Liquor(
    "Remy Martin XO Brandy",
    200,
    new Image(getClass.getResourceAsStream("../images/cointreau.png")),
    0.4
  )

  def liquors: List[Liquor] = List(Rum, Tequila, Gin, Whiskey, Vodka, Brandy, Cointreau)

}




