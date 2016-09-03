package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.commons.math.Vector2D

class QuantumPeaceMakerComponent extends GameComponent[QuantumPeaceMakerScene] {

  def position_=(position: Vector2D) = {
    this.setX(position.x1)
    this.setY(position.x2)
  }

  def position: Vector2D = (getX, getY)
}