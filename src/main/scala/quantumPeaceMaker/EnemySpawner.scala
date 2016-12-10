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
  
  override def update(state: DeltaState) = {
    if(state.isKeyPressed(Key.SPACE)) {
      spawning = !spawning
    }
    
    if(spawning) {
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
    val enemy2 = new EnemyLateral(this.getScene)
    
    if(ninjasEveryFour  == 0){
     // var ninjaShips = 1
      //while(ninjaShips > 0){
        this.getScene.addComponent(enemy2)
        ControllerTheCollision.addEnemyLateral(enemy2)
       // ninjaShips = ninjaShips - 1
     // }
    ninjasEveryFour = 3
    }
    ninjasEveryFour = ninjasEveryFour - 1
    this.getScene.addComponent(enemy)
  }

}


object EnemySpawner extends GameComponent[QuantumPeaceMakerScene] {
//  var spawning = true
//  
//  val cooldown = Cooldown(0.6, () => spawnEnemy())
//  
//  override def update(state: DeltaState) = {
//    if(state.isKeyPressed(Key.SPACE)) {
//      spawning = !spawning
//    }
//    
//    if(spawning) {
//      cooldown.update(state)
//    }
//  }
//
//  var randomFeed = new Random()
//  
//  def getRand():Int ={
//      var res = randomFeed.nextInt()
//      if(res <0)
//      {
//       res =  res * -1
//       }
//      return res
//    }
//
//  var ninjasEveryFour = 4
  
//  def spawnEnemy() = {
//    val enemy = new Enemy
//    ControllerTheCollision.addEnemy(enemy)
//   // val enemy2 = new EnemyLateral
//    ControllerTheCollision.addEnemyLateral(enemy2)
//    if(ninjasEveryFour  == 0){
//      var ninjaShips = 4
//      while(ninjaShips > 0){
//        this.getScene.addComponent(enemy2)
//        ninjaShips = ninjaShips - 1
//      }
//    ninjasEveryFour = 4
//    }
//    ninjasEveryFour = ninjasEveryFour - 1
//    this.getScene.addComponent(enemy)
//  }
//  
}