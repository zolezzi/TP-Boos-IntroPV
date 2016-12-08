package quantumPeaceMaker

import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.commons.math.Vector2D
import ar.pablitar.vainilla.commons.math.TimedValue
import ar.pablitar.vainilla.commons.math.InterpolatorKind
import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import java.awt.Graphics2D
import scala.util.Random

class EnemyLateral extends SpeedyComponent[QuantumPeaceMakerScene]{
    

	    val initialSpeed: Vector2D = (200.0, 300.0)
      override var speed: Vector2D = initialSpeed
      var llegueAlaDerecha = false
      var randomFeed = new Random()

	    //var x = true
	    var x = true
      val enemy1 = Resources.enemy1
      this.setAppearance(enemy1)

     def getRand():Double ={
      var res = randomFeed.nextInt() % 250
      if(res <0)
      {
       res =  res * -1 + 140
       }
      return res + 140
    }
      
      
      
    override def update(state: DeltaState) = {
   
	      
	 if(this.position.x2 > 250){
      this.speed = Vector2D(this.getRand(),-this.getRand())
    }
    
    if(this.position.x2 < 0){
      this.speed = Vector2D(-this.getRand(), this.getRand())
    }
    if(x){
    if(this.position.x1 > 700){
      this.speed = Vector2D(-this.getRand(), - this.getRand())
      x = false
    }}else{
    if(this.position.x1 > 700){
      this.speed = Vector2D(-this.getRand(),  this.getRand())
      x = true
    }  
    }
    
    if(this.position.x1 < -10){
      this.speed = Vector2D(this.getRand(), this.getRand())
    }
    
	   this.position += this.speed * state.getDelta   
    
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
  
  
  
  
}