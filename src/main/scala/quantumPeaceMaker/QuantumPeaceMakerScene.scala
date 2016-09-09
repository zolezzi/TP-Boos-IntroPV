package quantumPeaceMaker

import com.uqbar.vainilla.GameScene
import scala.collection.JavaConversions._


class QuantumPeaceMakerScene extends GameScene{
  
  val quantumShip = new QuantumShip(this)
  
  this.addComponent(EnemySpawner)
  this.addComponent(quantumShip)
  
  def collisionableGameComponents: List[CollisionDrivenGameComponent] =
    this.getComponents.filter(_.isInstanceOf[CollisionDrivenGameComponent]).map(_.asInstanceOf[CollisionDrivenGameComponent]).toList
}