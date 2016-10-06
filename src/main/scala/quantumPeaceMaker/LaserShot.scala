package quantumPeaceMaker
import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import com.uqbar.vainilla.appearances.Circle
import java.awt.Color
import java.awt.Rectangle
import com.uqbar.vainilla.colissions.CollisionDetector
import ar.pablitar.vainilla.commons.math.Semiplane
import ar.pablitar.vainilla.commons.math.PhysicsUtils

class LaserShot(scene: QuantumPeaceMakerScene, x: Double, y: Double, xSpeed:Double) extends SpeedyComponent[QuantumPeaceMakerScene]{

  val ancho = 10
  val alto = 20

  val diameter = 40
  val radius = diameter.toDouble / 2
  val collisionMargin = 30
  
  //val pelotitas = new Circle(Color.YELLOW, 20)
  //val laserShot = new Rectangle(Color.BLACK, 10, 20)
  val laserShotSprite = Resources.laserShotSprite
  
  this.setAppearance(laserShotSprite)
  this.setScene(scene)
  this.setX(x)
  this.setY(y)
  
  var impacts = 0
  def maxImpacts = 1
 
  val x1 = this.position.x1.toInt
  val y1 = this.position.x2.toInt
  var rec = new Rectangle(x1,y1,ancho,alto)

  var ene = ControllerTheCollision.enemigos
  //asigna la velocidad de el disparo
  this.speed = (xSpeed, -400.0)
  
  override def update(state: DeltaState) = {
    super.update(state)
    
    for(enemy <- ControllerTheCollision.enemigos){
      //if(Collision.hayColision(this, enemy)){
      if(this.isCollidedBy(enemy)){
       // if(enemy != null && this != null){
          enemy.hasbeenHitBy(this)
          this.destroy()
      } 
    }
    applySpeed(state)
    if (isOutsideOfTheScreen){
      this.destroy
      //ControllerTheCollision.removeLaser(this)

    }
  }  
  
  def isOutsideOfTheScreen: Boolean = {
    (this.position.x2 < -650)
  }
  
    def checkCollisionWithCatcherWalls() = {
     for(enemy <- ControllerTheCollision.enemigos){ 
        enemy.sideWalls.foreach {
          this.checkCollisionWithWallAndRebound(_)
    }

    if (enemy.bottomWall.circuloPasoDetras(this.position, this.radius)) {
      this.destroy()
    }
     }
  }

  def checkCollisionWithCatcher = {
    for(enemy <- ControllerTheCollision.enemigos){
      if (enemy.bottomWall.circuloPasoDetras(this.position, this.radius)) {
        this.destroy()
      }
    }
  }  
    
    
  def checkCollisionWithWallAndRebound(wall: Semiplane) = {
    if (wall.circuloPasoDetras(this.position, this.radius)) {
      this.speed = PhysicsUtils.rebound(this.speed, wall.normal, 0.3)
    }
  }
 
    
    def isCollidedBy(enemy : Enemy) ={
      CollisionDetector.INSTANCE.collidesCircleAgainstRect(this.position.x1 - radius, this.position.x2 - radius, this.radius,
        enemy.topLeft().x1, enemy.topLeft().x2, enemy.ancho, this.radius)
    }
    
    
    override def destroy() {
    // ControllerTheCollision.removeEnemy(this)
     super.destroy()
     //laserShot.despawn(this);
  }
 /*   
    def eleminarSi(enemy : Enemy){
      if(enemy != null && this != null){
        getScene.removeComponent(enemy)
        getScene.removeComponent(this)       
      }
    }*/
}