package shootail.model

import scalafx.scene.image.Image
import scalafx.scene.media.Media

class Fruit(
              val name: String,
              val size: Double,
              val img: Image,
              val taste: String
            ) extends Ingredient with Shootable {
  val shotAudio: Media = new Media(getClass.getResource("../audios/breeze_of_blood.mp3").toString)
}

object Fruit {
  val Lemon = new Fruit(
    "Lemon",
    100,
    new Image(getClass.getResourceAsStream("../images/lemon.png")),
    "sour"
  )

  val Lime = new Fruit(
    "Lime",
    100,
    new Image(getClass.getResourceAsStream("../images/lime.png")),
    "sour"
  )

  def fruits: List[Fruit] = List(Lemon, Lime)
}