package quantumPeaceMaker


import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.Game

class QuantumPeaceMakerScene(game : Game) extends GameScene{
  
  this.setGame(game)
  this.addComponent(new StarFieldBackground(this, game))
  
  val player = new QuantumShip(this)
  val score = new ScoreDisplay(this)
  
  this.addComponent(EnemySpawner)
  this.addComponent(player)
  this.addComponent(score)
}