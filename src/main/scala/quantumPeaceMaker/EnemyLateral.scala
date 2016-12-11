package quantumPeaceMaker

import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.math.TimedValue
import ar.pablitar.vainilla.commons.math.InterpolatorKind
import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import java.awt.Graphics2D
import scala.util.Random
import com.uqbar.vainilla.events.constants.Key

class EnemyLateral(scene:QuantumPeaceMakerScene) extends SpeedyComponent[QuantumPeaceMakerScene]{


      var randomFeed = new Random()  
      val ancho = 50
      val alto = 50
	    val initialSpeed: Vector2D = (this.getRand() + this.getRand() * 1.4, this.getRand() + this.getRand() * 1.3)
	    this.setScene(scene)
      var player = getScene.player
	    var speed2: Vector2D = initialSpeed
      var llegueAlaDerecha = false
      val coolDownTime = 0.12
      var cooldown = 0.0 
      var score2 = this.getScene.score  
	    
      var choice = true
      val enemy1 = Resources.enemy1
      this.setAppearance(enemy1)

      def getRand():Double ={
        var res = randomFeed.nextInt() % 30
        if(res <0){
          res =  res * -1 + 60
        }
      return res + 60
    }
      
      
      
    override def update(state: DeltaState) = {
   
	      
	 if(this.position.x2 > 250){
      this.speed2 = Vector2D(this.getRand(),-this.getRand())
    }
    
    if(this.position.x2 < 0){
      this.speed2 = Vector2D(-this.getRand(), this.getRand())
    }
    if(choice){
    if(this.position.x1 > 800){
      this.speed2 = Vector2D(-this.getRand(), - this.getRand())
      choice = false
    }}else{
    if(this.position.x1 > 800){
      this.speed2 = Vector2D(-this.getRand(),  this.getRand())
      choice = true
    }  
    }
    
    if(this.position.x1 < -10){
      this.speed2 = Vector2D(this.getRand(), this.getRand())
    }
    
	   this.position += this.speed2 * state.getDelta   
     
	   if(this.position.x1 < (player.position.x1 + 60) && this.position.x1 > (player.position.x1 - 60)){
       this.coolDownAndFire(state.getDelta)
     }
     
	  this.cooldown = (this.cooldown - state.getDelta) max 0

    if (this.isBelowTheScreen) {
     // this.getScene.score.resetCombo
      this.destroy()
    }
   }  

     def bajarSobreEjeY = {
       this.position.x2 += 5
     }
     
     def correteSobreElEjeX = {
      llegueAlaDerecha = this.position.x1 > 500
       
      if(llegueAlaDerecha){
         this.position.x1 += 5
       }else{
         this.position.x1 += -5    
       }
     
     }
      def subirSobreEjeY ={ 
        this.position.x2 += - 5
     }
   
    
    def isBelowTheScreen = {
      this.position.x2 >= QuantumPeaceMakerGame.height
    }
  
    def actualizarValores()={
      score2.sumScore(this.position.x2.toInt) // MEJORA se suma un punto por nave eliminada
    
    //this.getScene.addComponent(new Score(this.getScene))  EL NUMERO QUE SALTA AL MATAR NAVES
      score2.sumCombo //MEJORA Aumenta la chance de critico por cada enemigo eliminado
      score2.sumMaxCombo //MEJORA aumenta el daño critico por enemigo eliminado
  }
  
  def hasbeenHitBy(arg: LaserShot) = {
   this.actualizarValores()
   ControllerTheCollision.removeEnemyLateral(this) 
   this.destroy()

   var feedBack = new AttackFeedback(this.getY.toInt)
   feedBack.setX(this.getX)
   feedBack.setY(this.getY)
   //this.getScene.addComponent(feedBack)
    scene.addComponent(feedBack)
  // Resources.explosion.play(0.5f)
  }  
  
  def doFire = {
    val xSpeed = (QuantumPeaceMakerGame.randomizer.nextDouble - 0.5) *  100
    var laser = new LaserEnemyWeapon(this.getScene,this.getX, this.getY, xSpeed)
    scene.addComponent(laser)
   // Resources.laserSound.play(0.1f)
  }
    
    def coolDownAndFire(delta: Double): Unit = {
    if ((this.cooldown - delta) < 0) {
      this.doFire
      this.cooldown = 1
    }
  }  
}