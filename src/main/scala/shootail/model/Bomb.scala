package shootail.model

import scalafx.scene.image.Image
import scalafx.scene.media.Media


class Bomb(val name: String, val size: Double, val img: Image) extends Shootable {
  val shotAudio: Media = new Media(getClass.getResource("../audios/hq_explosion.mp3").toString)
}

object Bomb {
  val Bomb = new Bomb("Bomb", 150, new Image(getClass.getResourceAsStream("../images/bomb.png")))
}