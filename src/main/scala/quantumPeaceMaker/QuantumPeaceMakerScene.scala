package quantumPeaceMaker

import com.uqbar.vainilla.GameScene
import scala.collection.JavaConversions._


class QuantumPeaceMakerScene extends GameScene{
  
  val player = new QuantumShip(this)
  
  this.addComponent(EnemySpawner)
  this.addComponent(player)
}