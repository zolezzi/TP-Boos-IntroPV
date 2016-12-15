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
import com.uqbar.vainilla.colissions.CollisionDetector

class Enemy(scene : QuantumPeaceMakerScene) extends QuantumPeaceMakerComponent {
    
  val ancho = 50
  val alto = 50
  this.setScene(scene)
  val explosionNave = Resources.spriteExplosionNave
  private var _showDebug = false
  override def showDebug = _showDebug
  def showDebug_=(value: Boolean) = _showDebug = value
 
  this.setZ(5)
  
  var player = this.getScene.player
  
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
    
    if(hayColision(player)){
      player.death
      crearExplosionPorCollision()
    }
    
    if (this.isBelowTheScreen) {
      this.getScene.score.resetCombo
      this.destroy()
    }
  }
  
  
  def hayColision(player : QuantumShip)={
     CollisionDetector.INSTANCE.collidesRectAgainstRect(this.position.x1, this.position.x2, this.ancho, this.alto,
     player.position.x1, player.position.x2, player.ancho, player.alto)
  }
  
  def randomPosition() = {
    //CatchTheBallGame.width.toDouble / 2
    var position = QuantumPeaceMakerGame.randomizer.nextDouble * QuantumPeaceMakerGame.width
    if(position < 5)
      { position = position + 30}
    if(position > 795)
      { position = position - 95}
    position
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
  
  
//  def spawn() = {
//    new Enemy()
//  }
  
  def actualizarValores(){
    this.getScene.score.sumScore(this.position.x2.toInt) // MEJORA se suma un punto por nave eliminada
    
    //this.getScene.addComponent(new Score(this.getScene))  EL NUMERO QUE SALTA AL MATAR NAVES
    this.getScene.score.sumCombo //MEJORA Aumenta la chance de critico por cada enemigo eliminado
    this.getScene.score.sumMaxCombo //MEJORA aumenta el daño critico por enemigo eliminado
  }
  
  def hasbeenHitBy(arg: LaserShot) = {
   this.actualizarValores()
   this.destroy()
   var score = this.getY.toInt
   if(score < 0){score * -1}
   var feedBack = new AttackFeedback(score)
   feedBack.setX(this.getX)
   feedBack.setY(this.getY)
   this.getScene.addComponent(feedBack)
  // Resources.explosion.play(0.5f)
  }
  
  def hasbeenHitByLaser(arg: EspansiveWaveLaser) = {
   this.actualizarValores()
   this.destroy()

   var score = this.getY.toInt
   if(score < 0){score * -1}
   var feedBack = new AttackFeedback(score)
   feedBack.setX(this.getX)
   feedBack.setY(this.getY)
   this.getScene.addComponent(feedBack)
  // Resources.explosion.play(0.5f)
  }
  
  
    def crearExplosionPorCollision() ={
      val e = new Explosion(this.explosionNave)
      e.position = this.position
      this.getScene.addComponent(e) 
      this.destroy()
    }
}