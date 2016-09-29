package quantumPeaceMaker

import com.uqbar.vainilla.appearances.Appearance
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import com.uqbar.vainilla.appearances.Rectangle
import com.uqbar.vainilla.DeltaState

class Proyectile(appearance: Appearance, scene: QuantumPeaceMakerScene, x: Double, y: Double, val damage: Int, ySpeed: Double, xSpeed: Double = 0.0) extends SpeedyComponent[QuantumPeaceMakerScene]{
  
  this.setAppearance(appearance)
  this.setScene(scene)
  this.setX(x)
  this.setY(y)
  
  var impacts = 0
  def maxImpacts = 1
  
  this.speed = (xSpeed, -ySpeed)
  
  override def update(state: DeltaState) = {
    super.update(state)
    //checkCollisions
    applySpeed(state)
  //  if (this.isOutsideOfScreen){
      this.destroy
   // }
  }
  
  
}