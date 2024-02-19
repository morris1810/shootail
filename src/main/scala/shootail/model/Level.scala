package shootail.model

import scalafx.util.Duration
import shootail.util.CommonUtil

class Level(val label: String, val time: Duration, val speed: Duration, val ingredientCount: Int, val nonIngredientCount: Int, val bombCount: Int) {
  def getTimerString(): String = {
    CommonUtil.durationToTimerString(time)
  }
}

object Level {
  val Easy = new Level("EASY", Duration(60000), Duration(10000), 50, 0, 0)
  val Normal = new Level("NORMAL", Duration(120000), Duration(5000), 55, 20, 0)
  val Hard = new Level("HARD", Duration(150000), Duration(4500), 60, 50, 30)
  val Hell = new Level("HELL", Duration(180000), Duration(2000), 65, 70, 100)
}