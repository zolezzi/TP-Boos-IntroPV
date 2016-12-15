package quantumPeaceMaker

import ar.pablitar.vainilla.commons.components.SpeedyComponent
import com.uqbar.vainilla.colissions.CollisionDetector
import com.uqbar.vainilla.DeltaState
import ar.pablitar.vainilla.appearances.TimedAppearance

class EspansiveWaveLaser (scene: QuantumPeaceMakerScene, x: Double, y: Double, xSpeed:Double) extends SpeedyComponent[QuantumPeaceMakerScene]{
  
    val ancho = 10
    val alto = 20
    this.setAppearance(
      Resources.laserIdle
    )
  
    val explosion = Resources.spriteExplosion

    val laserShotSprite = Resources.laserShotSprite

   
    this.setScene(scene)
    this.setX(x)
    this.setY(y)
    var boss = this.getScene.spawnerBoss.boss
    var impacts = 0
    def maxImpacts = 1


    var ene = ControllerTheCollision.enemigos
    //asigna la velocidad de el disparo
   // this.speed = (xSpeed, -400.0)

    override def update(state: DeltaState) = {
    	super.update(state)

    	for(enemy <- ControllerTheCollision.enemigos){
    		//if(Collision.hayColision(this, enemy)){
    		if(this.isCollidedBy(enemy)){
    			// if(enemy != null && this != null){
    			crearExplosionPorCollision()  
    			enemy.hasbeenHitByLaser(this)
    			this.destroy()
    		} 
    	}
    	this.resetAnimation()
    	for(enemy2 <- ControllerTheCollision.enemigosMosquitos){
    		//if(Collision.hayColision(this, enemy)){
    		if(this.isCollidedByEnemyLateral(enemy2)){
    			// if(enemy != null && this != null){
    			crearExplosionPorCollision()  
    			enemy2.hasbeenHitByLaser(this)
    			this.destroy()
    		} 
    	}

    	if(isCollidedByBOSS(boss)){
    		this.destroy()
    		boss.descontarVida()
    		boss.chequearVida()
    	}
    	//applySpeed(state)
    	if (isOutsideOfTheScreen){
    		this.destroy
    		//ControllerTheCollision.removeLaser(this)

    	}
    }  

    def crearExplosionPorCollision() ={
    	val e = new Explosion(this.explosion)
    	e.position = this.position
    	this.getScene.addComponent(e) 
    	this.destroy()
    }

      def isOutsideOfTheScreen: Boolean = {
    		(this.position.x2 <= -650)
    	}


    	def isCollidedBy(enemy : Enemy) ={
    		    		CollisionDetector.INSTANCE.collidesRectAgainstRect(this.position.x1, this.position.x2, this.ancho, this.alto, enemy.position.x1, enemy.position.x2, enemy.ancho, enemy.alto)
    	}
    	def isCollidedByEnemyLateral(enemy : EnemyLateral) ={
    		CollisionDetector.INSTANCE.collidesRectAgainstRect(this.position.x1, this.position.x2, this.ancho, this.alto, enemy.position.x1, enemy.position.x2, enemy.ancho, enemy.alto)
    	}


    	def isCollidedByBOSS(boss : BOSS) ={
    		    		CollisionDetector.INSTANCE.collidesRectAgainstRect(this.position.x1, this.position.x2, this.ancho, this.alto, boss.position.x1, boss.position.x2, boss.ancho, boss.alto)
    	}	    
    	
    	def resetAnimation ()={
    	    Resources.laserAnimation.reset()
          this.setAppearance(TimedAppearance.fromAnimationTo(this, Resources.laserAnimation, Resources.laserIdle))
 
    	  
    	}
    	
    	override def destroy() {
    		super.destroy()
    	}

}