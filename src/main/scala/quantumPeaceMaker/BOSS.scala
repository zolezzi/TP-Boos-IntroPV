package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.events.constants.Key
import ar.pablitar.vainilla.commons.components.SpeedyComponent
import ar.pablitar.vainilla.commons.math.Vector2D
import scala.util.Random



class BOSS(scene : QuantumPeaceMakerScene) extends QuantumPeaceMakerComponent{
  
  this.setY(-500)
  this.setX(400)
  var x = true
  var y = true
  var w = true
  this.speed = Vector2D(0.0, 10.0)
  val initialSpeed: Vector2D = (0.0, 200.0)
  var speed2: Vector2D = initialSpeed
  this.setScene(scene)
  val coolDownTime = 0.12
  var cooldown = 0.0 
  var randomFeed = new Random()
  var player = getScene.player
  val explosionNave = Resources.spriteExplosionNave
  val ancho = 150
  val alto =  150
  var life = 50
  val bossSprite = Resources.bossSprite
  this.setAppearance(bossSprite)
  
 def spawn() = {
    new BOSS(this.getScene())
  }
  
 def getRand():Double ={
   var res = randomFeed.nextInt() % 30
      if(res <0)
      {
       res =  res * -1 + 60
       }
      return (res + 60) * 2
    }
  
  override def update(state: DeltaState) = {
    if(w){
	   if(this.position.x2 > 250){
        this.speed2 = Vector2D(300,-this.getRand())
        w = true 
	   }
	  }
   else{
     if(this.position.x2 > 250){
        this.speed2 = Vector2D(-300,-this.getRand())
        w = false 
     }     
   }
    if(y){
      if(this.position.x2 < 40){
        this.speed2 = Vector2D(-300, this.getRand())
        y = false
      }
      }else{
         if(this.position.x2 < 40){
            this.speed2 = Vector2D(300, this.getRand())
            y = true
         } 
      }
    if(x){
      if(this.position.x1 > 750){
        this.speed2 = Vector2D(-300, - this.getRand())
        x = false
      }
    }else{
        if(this.position.x1 > 750){
        this.speed2 = Vector2D(-300,  this.getRand())
        x = true
      }  
    }
    
    if(this.position.x1 < 30){
      this.speed2 = Vector2D(300, this.getRand())
    }
    
	   this.position += this.speed2 * state.getDelta   
     
	   if(this.position.x1 < (player.position.x1 + 60) && this.position.x1 > (player.position.x1 - 60)){
       this.coolDownAndFire(state.getDelta)
     }   
	   
	  this.cooldown = (this.cooldown - state.getDelta) max 0

    if (this.isBelowTheScreen) {
     // this.getScene.score.resetCombo
      this.destroy
    }
	   
    if(state.isKeyPressed(Key.B)) {
     this.spawn()
    }
    
  }
  
    def isBelowTheScreen = {
      this.position.x2 >= QuantumPeaceMakerGame.height
    }
    
    def doFire = {
      val xSpeed = (QuantumPeaceMakerGame.randomizer.nextDouble - 0.5) *  200
      var laser = new LaserEnemyWeapon(this.getScene,this.getX, this.getY, xSpeed)
      scene.addComponent(laser)
    }
    
    def coolDownAndFire(delta: Double): Unit = {
      if ((this.cooldown - delta) < 0) {
        this.doFire
        this.doFire
        this.doFire
        this.doFire
        this.doFire
        this.cooldown = 1
      }
    }
    
    
    def crearExplosionPorCollision() ={
      val e = new Explosion(this.explosionNave)
      e.position = this.position
      super.destroy
      scene.addComponent(e)
      this.setY(10000)
      scene.removeComponent(scene.spawnerBoss.boss)   
      
    }
    
    def descontarVida(){
      this.life = this.life - 1
    }
    
    def chequearVida(){
      if(this.life <= 0){
        crearExplosionPorCollision()
      }
    }
    
}