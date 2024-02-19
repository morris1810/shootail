package shootail.util

import scalafx.collections.ObservableBuffer
import scalafx.util.Duration

object CommonUtil {
  def durationToTimerString(duration: Duration): String = {
      val minute: Int = duration.toMinutes.toInt
      val second: Int = (duration.toSeconds % 60).toInt
      val formattedSecond: String = "%02d".format(second)
      s"$minute:$formattedSecond"
  }

  def listToObservableBuffer[A](list: List[A]): ObservableBuffer[A] = {
    val observableBuffer = new ObservableBuffer[A]()
    list.foreach(item => observableBuffer.add(item))
    observableBuffer
  }
}
