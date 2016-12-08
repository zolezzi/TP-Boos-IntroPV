package quantumPeaceMaker


import com.uqbar.vainilla.GameScene
import com.uqbar.vainilla.Game
import quantumPeaceMakerShop.ViewShop
import java.awt.Graphics2D
import quantumPeaceMakerShop.Shop

class QuantumPeaceMakerScene(game : Game) extends GameScene{
  
  this.setGame(game)
  this.addComponent(new StarFieldBackground(this, game))
 
  //val shop = new SceneShop(this)
  
  val player = new QuantumShip(this)
  val score = new ScoreDisplay(this)
  
  this.addComponent(EnemySpawner)
  this.addComponent(player)
  this.addComponent(score)
  
  Resources.backgroundSound.play()
  
  def shopUp(graphics: Graphics2D)={
   val shop = new SceneShop(this)
//   val shop2 = new Shop()
//   shop2.render(graphics)
   //this.addComponent(shop)
   shop.render(graphics)
   shop.destroyShop()
  }
 
  
  def scoreValue()={
    score.sumScore
  }
}