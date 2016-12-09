package quantumPeaceMaker

import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.math.TimedValue
import ar.pablitar.vainilla.commons.math.InterpolatorKind
import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import java.awt.Graphics2D
import scala.util.Random

class EnemyLateral(scene:QuantumPeaceMakerScene) extends SpeedyComponent[QuantumPeaceMakerScene]{
    
  
      val ancho = 50
      val alto = 50
	    val initialSpeed: Vector2D = (200.0, 300.0)
	    this.setScene(scene)
      var speed2: Vector2D = initialSpeed
      var llegueAlaDerecha = false
      var randomFeed = new Random()

      var score2 = this.getScene.score  
	    //var x = true
	    var x = true
      val enemy1 = Resources.enemy1
      this.setAppearance(enemy1)

     def getRand():Double ={
      var res = randomFeed.nextInt() % 30
      if(res <0)
      {
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
    if(x){
    if(this.position.x1 > 800){
      this.speed2 = Vector2D(-this.getRand(), - this.getRand())
      x = false
    }}else{
    if(this.position.x1 > 800){
      this.speed2 = Vector2D(-this.getRand(),  this.getRand())
      x = true
    }  
    }
    
    if(this.position.x1 < -10){
      this.speed2 = Vector2D(this.getRand(), this.getRand())
    }
    
	   this.position += this.speed2 * state.getDelta   
    
//	   this.bajarSobreEjeY
//     
//     if(this.position.x2 > 400){
//        this.correteSobreElEjeX
//        this.subirSobreEjeY
//     }
     
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
      score2.sumScore // MEJORA se suma un punto por nave eliminada
    
    //this.getScene.addComponent(new Score(this.getScene))  EL NUMERO QUE SALTA AL MATAR NAVES
      score2.sumCombo //MEJORA Aumenta la chance de critico por cada enemigo eliminado
      score2.sumMaxCombo //MEJORA aumenta el daño critico por enemigo eliminado
  }
  
  def hasbeenHitBy(arg: LaserShot) = {
   this.actualizarValores()
   ControllerTheCollision.removeEnemyLateral(this) 
   this.destroy()

   var feedBack = new AttackFeedback()
   feedBack.setX(this.getX)
   feedBack.setY(this.getY)
   //this.getScene.addComponent(feedBack)
    scene.addComponent(feedBack)
  // Resources.explosion.play(0.5f)
  }
  
  
}