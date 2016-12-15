package quantumPeaceMaker

import ar.pablitar.vainilla.commons.components.SpeedyComponent
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.colissions.CollisionDetector
import ar.pablitar.vainilla.commons.math.Vector2D

class LaserEnemyWeapon (scene: QuantumPeaceMakerScene, x: Double, y: Double, xSpeed:Double) extends SpeedyComponent[QuantumPeaceMakerScene]{

    val ancho = 10
    val alto = 20
   
    
    val explosion = Resources.spriteExplosion
    val laserShotSprite = Resources.miniLaserShotSprite

    this.setAppearance(laserShotSprite)
    this.setScene(scene)
   
    this.setX(x)
    this.setY(y)
    
    var player = scene.player
    
    this.speed = (xSpeed, 300.0)
    
    override def update(state: DeltaState) = {

      applySpeed(state)
      
      if(hayColision(player)){
        player.takeDamage(1)
        this.destroy
      }
      
      if (isOutsideOfTheScreen){
        this.destroy
      }    
    }
  
  override def applySpeed(state: DeltaState, speed: Vector2D = this.speed) = {
    this.position = positionAfterSpeed(state, speed)
  }
  
    
    def hayColision(player : QuantumShip)={
     CollisionDetector.INSTANCE.collidesRectAgainstRect(this.position.x1, this.position.x2, this.ancho, this.alto,
     player.position.x1, player.position.x2, player.ancho, player.alto)
  }
    
    def isOutsideOfTheScreen: Boolean = {
      (this.position.x2 > 1000)
    }
    
    override def destroy() {
      super.destroy()
    }
}