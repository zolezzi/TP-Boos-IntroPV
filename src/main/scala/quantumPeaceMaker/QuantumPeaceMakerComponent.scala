package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.components.SpeedyComponent

class QuantumPeaceMakerComponent extends SpeedyComponent[QuantumPeaceMakerScene] {

 override def position_=(position: Vector2D) = {
    this.setX(position.x1)
    this.setY(position.x2)
  }

 override  def position: Vector2D = (getX, getY)
}