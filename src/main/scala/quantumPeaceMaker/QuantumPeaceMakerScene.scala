package quantumPeaceMaker

import com.uqbar.vainilla.GameScene

class QuantumPeaceMakerScene extends GameScene{
  
  val quantumShip = new QuantumShip(this)
  
  this.addComponent(EnemySpawner)
  this.addComponent(quantumShip)
}