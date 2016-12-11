package quantumPeaceMaker

import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.appearances.Rectangle
import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.DeltaState
import java.awt.Color
import com.uqbar.vainilla.events.constants.Key
import java.awt.Graphics2D

class QuantumShip(scene : QuantumPeaceMakerScene) extends RichGameComponent[QuantumPeaceMakerScene]{
  
  val ancho = 50
  val alto = 50
  this.setAppearance(new Rectangle(Color.BLACK, ancho, alto))
  private var _showDebug = false
  override def showDebug = _showDebug
  def showDebug_=(value: Boolean) = _showDebug = value
  
  setScene(scene)
  
  this.setAppearance(Resources.quantumShip)
  
  var laserShotWeapon = new LaserShotWeapon(this.getScene, this)

  
  this.getScene.addComponent(laserShotWeapon)
  
  var speed: Vector2D = (0.0, 0.0)
  
  val speedMagnitude = 500.0
  
  var weapon: Weapon = laserShotWeapon
  
  this.position = ((QuantumPeaceMakerGame.width.toDouble - this.getWidth) / 2, QuantumPeaceMakerGame.height - this.getHeight.toDouble * 2)

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
     if(state.isKeyPressed(Key.CTRL)){
       weapon.coolDownAndFire(state.getDelta)
     }
    this.position = this.position + this.speed * state.getDelta
   
    if(state.isKeyPressed(Key.E)) {
      this.showDebug = !this.showDebug
    }
    if(state.isKeyPressed(Key.F)) {
      this.showDebug = !this.showDebug
    }
 
  }
  
//    override def render(graphics: Graphics2D) = {
//    
//      if(showDebug) {
//        super.render(graphics)
//        this.getScene.shopUp(graphics)
//      }
//      if(!showDebug){
//        
//      }
//    }
    
    def death: Unit = {
      super.destroy()
      this.setY(-10000)
      this.scene.gameOver
      
    }
  
    override def position_=(v:Vector2D) = super.position_=(QuantumPeaceMakerGame.bounds.limit(v, (this.getWidth.toDouble, this.getHeight.toDouble)))

    def center = {
      position + (Vector2D(this.getWidth, this.getHeight) * 0.5)
    }
   

}