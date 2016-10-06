package quantumPeaceMaker

import ar.pablitar.vainilla.commons.math.Vector2D
import com.uqbar.vainilla.appearances.Circle
import java.awt.Color
import com.uqbar.vainilla.DeltaState
//import com.uqbar.vainilla.appearances.Rectangle
import ar.pablitar.vainilla.commons.math.Semiplane
import com.uqbar.vainilla.events.constants.Key
import java.awt.Rectangle
import java.awt.Graphics2D
import ar.pablitar.vainilla.commons.inspectors.MathInspector

class Enemy extends QuantumPeaceMakerComponent {
    
  val ancho = 50
  val alto = 50
  private var _showDebug = false
  override def showDebug = _showDebug
  def showDebug_=(value: Boolean) = _showDebug = value
 
  def sideWalls = List(
    Semiplane(topLeft() + (8, 8), Vector2D(3.1, -1)),
    Semiplane(topRight() + (-8, 8), Vector2D(-3.1, -1))
  )
  
  def bottomWall = Semiplane(this.position, Vector2D(ancho, alto))
  
//  def walls = bottomWall +: sideWalls
  
  
  //val diameter = 40
  //val radius = diameter.toDouble / 2
  val initialSpeed: Vector2D = (0.0, 300.0)

  val x1 = this.position.x1.toInt
  val y1 = this.position.x2.toInt
  var rec = new Rectangle(x1,y1,ancho,alto)

    val enemySprite = Resources.enemySprite
    this.setAppearance(enemySprite)
 // this.setAppearance(new Rectangle(Color.BLUE, 50, 50))
  
  val accelerationMagnitude = 800
  override var speed: Vector2D = initialSpeed
  
  reset()

  override def update(state: DeltaState) = {
    this.position += this.speed * state.getDelta
    var xp =(this.position.x1.toInt)
    var yp =(this.position.x2.toInt)
    rec.setBounds(xp, yp, ancho, alto)
    this.showDebug = !this.showDebug
    if(state.isKeyPressed(Key.D)) {
      this.showDebug = !this.showDebug
    }

    if (this.isBelowTheScreen) {
      this.destroy()
    }
  }
    
  def randomPosition() = {
    //CatchTheBallGame.width.toDouble / 2
    QuantumPeaceMakerGame.randomizer.nextDouble * QuantumPeaceMakerGame.width
  }

  def isBelowTheScreen = {
    this.position.x2 >= QuantumPeaceMakerGame.height
  }

  def center = {
    this.position + (alto, ancho)
  }

  override def position_=(v: Vector2D) = super.position_=(boundWithWalls(v))

  def boundWithWalls(v: Vector2D) = {
    v
  }

  override def destroy() {
      ControllerTheCollision.removeEnemy(this)
      super.destroy()
      //Enemy.despawn(this);
  }

  def reset() = {
    this.position = (randomPosition(), -600.0)
    speed = initialSpeed
    this.setDestroyPending(false)
    this
  }

    override def render(graphics: Graphics2D) = {
    super.render(graphics)
    if(showDebug) {
      graphics.drawRect(this.topLeft().x1.toInt + 30, this.topLeft().x2.toInt + 10, this.width.toInt -30 * 2, 20)
   //   walls.foreach { w => MathInspector.renderSemiplane(graphics, w) }
    }
  }
  
  
  def spawn() = {
    new Enemy()
  }

  def hasbeenHitBy(arg: LaserShot) = {
   this.destroy()
  }
}