package quantumPeaceMaker


import vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.appearances.Rectangle
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.DeltaState
import java.awt.Color
import com.uqbar.vainilla.events.constants.Key

class QuantumShip(scene: QuantumPeaceMakerScene) extends RichGameComponent [QuantumPeaceMakerScene]{
  
  val ancho = 50
  val alto = 50
  this.setAppearance(new Rectangle(Color.BLACK, ancho, alto))

  var speed: Vector2D = (0.0, 0.0)
  
  val speedMagnitude = 500.0

  this.position = ((QuantumPeaceMakerGame.width.toDouble - width) / 2, QuantumPeaceMakerGame.height - height.toDouble * 2)

  override def update(state: DeltaState) = {
    this.speed = (0,0)
    if (state.isKeyBeingHold(Key.LEFT)) {
      this.speed += (-speedMagnitude, 0.0)
    }
     if (state.isKeyBeingHold(Key.RIGHT)) {
      this.speed += (speedMagnitude, 0.0)
    }   
     if (state.isKeyBeingHold(Key.DOWN)){
      this.speed += (0.0, speedMagnitude)
    }
     if (state.isKeyBeingHold(Key.UP)){
      this.speed += (0.0, -speedMagnitude)
    }
    this.position += this.speed * state.getDelta
  }
  
   override def position_=(v:Vector2D) = super.position_=(QuantumPeaceMakerGame.bounds.limit(v, (width.toDouble, height.toDouble)))

  def center = {
    position + (Vector2D(width, height) * 0.5)
  }
}