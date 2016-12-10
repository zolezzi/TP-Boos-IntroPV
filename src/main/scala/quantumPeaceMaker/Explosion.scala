package quantumPeaceMaker

import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.appearances.Sprite

class Explosion(sprite : Sprite) extends SpeedyComponent[QuantumPeaceMakerScene] {
  //this.speed = component.speed * 0.1

  this.setAppearance(sprite)

 
  val duration = 0.3
  var current = 0.0

  override def update(state: DeltaState): Unit = {
    if (current >= duration) {
      this.destroy
    } else {
      applySpeed(state)
      current += state.getDelta
    }
  }
}