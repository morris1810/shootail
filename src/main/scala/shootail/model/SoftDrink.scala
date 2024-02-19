package shootail.model

import scalafx.scene.image.Image
import scalafx.scene.media.Media

class SoftDrink(
                 val name: String,
                 val size: Double,
                 val img: Image,
                 val flavor: String
               ) extends Ingredient with Shootable {
  val shotAudio: Media = new Media(getClass.getResource("../audios/hit_to_the_can.mp3").toString)
}

object SoftDrink {
  val CocaCola = new SoftDrink(
    "Coca-Cola",
    200,
    new Image(getClass.getResourceAsStream("../images/coca_cola.png")),
    "sweet"
  )

  val TonicWater = new SoftDrink(
    "Tonic Water",
    200,
    new Image(getClass.getResourceAsStream("../images/tonic_water.png")),
    "quinine"
  )

  val GingerAle = new SoftDrink(
    "Ginger Ale",
    200,
    new Image(getClass.getResourceAsStream("../images/ginger_ale.png")),
    "sweet"
  )

  def softDrinks: List[SoftDrink] = List(CocaCola, TonicWater, GingerAle)
}