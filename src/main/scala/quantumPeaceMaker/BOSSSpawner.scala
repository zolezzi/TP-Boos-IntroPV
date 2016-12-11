package quantumPeaceMaker

import com.uqbar.vainilla.DeltaState
import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.events.constants.Key

class BOSSSpawner(scene : QuantumPeaceMakerScene ) extends GameComponent[QuantumPeaceMakerScene] {
  
  var scoreActual = 0
  var llego = true  
  this.setScene(scene)
  val boss = new BOSS(this.getScene)
 
  
  override def update(state: DeltaState) = {
       
    scoreActual = this.getScene.score.score
    this.getScene.score.score
    
    if(llego && scoreActual >= 7500){
      this.spawnBoss()
      llego = false
      this.getScene.spawner.destroy()    
    }
    
    if(state.isKeyPressed(Key.B)) {
      this.spawnBoss()
      this.getScene.spawner.destroy() 
    }
  }

  def spawnBoss() = {
    //val boss = new BOSS(this.getScene)
    //ControllerTheCollision.addEnemy(boss)
    this.getScene.addComponent(boss)
  }
  
}