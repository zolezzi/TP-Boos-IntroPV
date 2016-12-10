package quantumPeaceMaker


import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.events.constants.Key
import scala.util.Random

case class Cooldown(cooldown: Double, action: (() => Unit)) {
  var elapsed = 0.0
  def update(state: DeltaState) = {
    this.elapsed += state.getDelta
    if(elapsed >= cooldown){
      action()
      this.elapsed = 0.0
    }
  }
}

class EnemySpawner(scene: QuantumPeaceMakerScene) extends GameComponent[QuantumPeaceMakerScene]{
  
  
  this.setScene(scene)
  
  var spawning = true
  
  val cooldown = Cooldown(0.6, () => spawnEnemy())
  val cooldownMosquito = Cooldown(0.2,() => spawnEnemyMosquito())
  
  override def update(state: DeltaState) = {
    if(state.isKeyPressed(Key.SPACE)) {
      spawning = !spawning
    }
    cooldownMosquito.update(state)
    if(spawning &&this.getScene.score.score >= 100) {
      cooldown.update(state)
    }
  }

  var randomFeed = new Random()
  
  def getRand():Int ={
      var res = randomFeed.nextInt()
      if(res <0)
      {
       res =  res * -1
       }
      return res
    }

  var ninjasEveryFour = 4
  
  def spawnEnemy() = {
   
    val enemy = new Enemy(this.getScene)
    ControllerTheCollision.addEnemy(enemy)
    this.getScene.addComponent(enemy)     
 
  }
  
  def spawnEnemyMosquito()={
     val enemy2 = new EnemyLateral(this.getScene)
    
      if(ninjasEveryFour  == 0){
        this.getScene.addComponent(enemy2)
        ControllerTheCollision.addEnemyLateral(enemy2)
        ninjasEveryFour = 3
      }
      ninjasEveryFour = ninjasEveryFour - 1
  }

}
  
