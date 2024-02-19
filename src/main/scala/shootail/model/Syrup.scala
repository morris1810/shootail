package shootail.model

import scalafx.scene.image.Image
import scalafx.scene.media.Media


class Syrup(
             val name: String,
             val size: Double,
             val img: Image,
             val flavour: String
           ) extends Ingredient with Shootable {
  val shotAudio: Media = new Media(getClass.getResource("../audios/bottle_smash.mp3").toString)
}

object Syrup {
  val Vanilla: Syrup = new Syrup(
    "Vanilla Syrup",
    200,
    new Image(getClass.getResourceAsStream("../images/vanilla_syrup.png")),
    "vanilla"
  )

  def syrups: List[Syrup] = List(Vanilla)
}