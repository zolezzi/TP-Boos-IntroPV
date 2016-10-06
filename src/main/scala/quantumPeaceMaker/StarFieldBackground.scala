package quantumPeaceMaker


import com.uqbar.vainilla.GameComponent
import scala.collection.mutable.Set
import QuantumPeaceMakerGame._
import StarFieldBackground._
import com.uqbar.vainilla.DeltaState
import java.awt.Color
import java.awt.Graphics2D
import com.uqbar.vainilla.Game
import com.uqbar.vainilla.appearances.Rectangle

object StarFieldBackground {
  val maxStars = 100
}

class StarFieldBackground(scene: QuantumPeaceMakerScene, game: Game) extends GameComponent[QuantumPeaceMakerScene] {
  this.setZ(-2)
  this.setScene(scene)
  this.setAppearance(new Rectangle(Color.DARK_GRAY, this.getGame().getDisplayWidth(), this.getGame().getDisplayHeight()))

  this.initializeStars

  def initializeStars = {
    for (i <- 1 to maxStars) {
      scene.addComponent(new Star(randomizer.nextDouble * getGame.getDisplayWidth,
        randomizer.nextDouble * getGame.getDisplayHeight))
    }
  }
  
    def initialize = {
    for (i <- 1 to maxStars) {
      scene.addComponent(new Planet(randomizer.nextDouble * getGame.getDisplayWidth,
        randomizer.nextDouble * getGame.getDisplayHeight))
    }
  }
}