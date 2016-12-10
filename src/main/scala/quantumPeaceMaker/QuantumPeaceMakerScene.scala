package quantumPeaceMaker


import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.Game
import quantumPeaceMakerShop.ViewShop
import java.awt.Graphics2D
import quantumPeaceMakerShop.Shop

class QuantumPeaceMakerScene(game : Game) extends GameScene{
  
    this.setGame(game)
    this.addComponent(new StarFieldBackground(this, game))
 
  
//  val enemy2 = new EnemyLateral
//  val enemy3 = new EnemyLateral
//  val my2 = new EnemyLateral
//  val eny2 = new EnemyLateral
//  val a = new EnemyLateral
//  
    val player = new QuantumShip(this)
    val score = new ScoreDisplay(this)
    val spawner = new EnemySpawner(this)
 
    this.addComponent(BOSSSpawner)
    this.addComponent(spawner)
    this.addComponent(player)
    this.addComponent(score)
 
//  this.addComponent(enemy2)
//  this.addComponent(enemy3)
//  this.addComponent(my2)
//  this.addComponent(eny2)
//  this.addComponent(a)
//  
  
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
  
    def scoreValue()={
      score.sumScore
    }
}