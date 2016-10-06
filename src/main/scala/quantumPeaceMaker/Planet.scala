package quantumPeaceMaker


import QuantumPeaceMakerGame._
import com.uqbar.vainilla.appearances.Rectangle
import java.awt.Color
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.components.SpeedyComponent

object Planet{
  val maxSpeed = 100
  val minSpeed = 50
}

class Planet(x: Double, y: Double) extends SpeedyComponent[QuantumPeaceMakerScene]  {
this.setX(x)
  this.setY(y)
  this.setZ(-1)

  initSpeed
  
  val size = randomizer.nextInt(4) + 1

  this.setAppearance(Resources.planetSprite)

   def getMaxY = Double.PositiveInfinity
   def getMinY = Double.NegativeInfinity

  override def update(state: DeltaState) = {
    super.update(state)
    this.applySpeed(state)
    if (this.position.x2 > 600) {
      this.setY(-3);
      this.setX(randomizer.nextDouble )
      initSpeed
    }
  }
  
  def initSpeed = {
    speed = (0.0, randomizer.nextDouble * (Planet.maxSpeed-Planet.minSpeed) + Planet.minSpeed)
  }

}