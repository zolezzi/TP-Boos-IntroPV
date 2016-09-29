package quantumPeaceMaker

import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.appearances.Circle
import java.awt.Color
import com.uqbar.vainilla.DeltaState


class Enemy extends QuantumPeaceMakerComponent {
    

  val diameter = 40
  val radius = diameter.toDouble / 2
  val initialSpeed: Vector2D = (0.0, 300.0)

  this.setAppearance(new Circle(Color.BLUE, diameter))

  val accelerationMagnitude = 800
  var speed: Vector2D = initialSpeed
  
  reset()

  override def update(state: DeltaState) = {
    this.position += this.speed * state.getDelta

  }

    if (this.isBelowTheScreen) {
      this.destroy()
    }
  

  def randomPosition() = {
    //CatchTheBallGame.width.toDouble / 2
    QuantumPeaceMakerGame.randomizer.nextDouble * QuantumPeaceMakerGame.width
  }

  def isBelowTheScreen = {
    this.position.x2 >= QuantumPeaceMakerGame.height
  }

  def center = {
    this.position + (radius, radius)
  }

  override def position_=(v: Vector2D) = super.position_=(boundWithWalls(v))

  def boundWithWalls(v: Vector2D) = {
    v
  }

  override def destroy() {
//    super.destroy()
//    Enemy.despawn(this);
  }

  def reset() = {
    this.position = (randomPosition(), -600.0)
    speed = initialSpeed
    this.setDestroyPending(false)
    this
  }

  
    def spawn() = {
     new Enemy()
    }
}