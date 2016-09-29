package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import com.uqbar.vainilla.appearances.Circle
import java.awt.Color

class LaserShot(scene: QuantumPeaceMakerScene, x: Double, y: Double, xSpeed:Double) extends SpeedyComponent[QuantumPeaceMakerScene]{

  val pelotitas = new Circle(Color.YELLOW, 20)
  
  this.setAppearance(pelotitas)
  this.setScene(scene)
  this.setX(x)
  this.setY(y)
  
  var impacts = 0
  def maxImpacts = 1
  
  //asigna la velocidad de el disparo
  this.speed = (xSpeed, -400.0)
  
  override def update(state: DeltaState) = {
    super.update(state)
    //checkCollisions
    applySpeed(state)
    if (isOutsideOfTheScreen){
      this.destroy

    }
  }  
  
   def isOutsideOfTheScreen: Boolean = {
    (this.position.x2 < -650)
  }
  
}