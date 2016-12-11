package quantumPeaceMaker


import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.Game
import quantumPeaceMakerShop.ViewShop
import java.awt.Graphics2D
import quantumPeaceMakerShop.Shop

class QuantumPeaceMakerScene(game : Game) extends GameScene{
  
    this.setGame(game)
    this.addComponent(new StarFieldBackground(this, game))
    
    val player = new QuantumShip(this)
    val score = new ScoreDisplay(this)
    val spawner = new EnemySpawner(this)
   val spawnerBoss = new BOSSSpawner(this)
    
    this.addComponent(spawnerBoss)
    this.addComponent(spawner)
    this.addComponent(player)
    this.addComponent(score)
  
    
  //  Resources.backgroundSound.play()
 
    var isRestartOnEnd = false
  
    def restartOnEnd = {
      isRestartOnEnd = true
    }
    
    override def takeStep(graphics: Graphics2D) = {
      super.takeStep(graphics)
      if (isRestartOnEnd) restart
    }
  
    def gameOver = {
      this.addComponent(new GameOverComponent(this));
      spawner.destroy()
    }

    def restart = {
      ControllerTheCollision.restart
      this.getGame.setCurrentScene(new QuantumPeaceMakerScene(game))
    }
}