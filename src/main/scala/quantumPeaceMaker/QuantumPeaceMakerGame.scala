package quantumPeaceMaker

import com.uqbar.vainilla.DesktopGameLauncher
import ar.pablitar.vainilla.commons.math.RectangularBounds
import scala.util.Random

object QuantumPeaceMakerGame extends App{
  val width = 860
  val height = 650
  
  val randomizer = new Random
  
  new DesktopGameLauncher(new QuantumPeaceMaker()).launch()
  
  def bounds = RectangularBounds((0.0, 0.0), (width.toDouble, height.toDouble))
  
}