package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color
import com.uqbar.vainilla.events.constants.Key
import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.DeltaState

class GameOverComponent(scene: QuantumPeaceMakerScene) extends EventDrivenGameComponent[QuantumPeaceMakerScene] {
  this.setScene(scene)

  
  val gameOverLabel = new Label(new Font(Font.SERIF, Font.BOLD, 48), Color.WHITE.darker(), "Game Over!", "Press enter to restart")
  
  this.setAppearance(gameOverLabel)
  
  this.setX(this.getGame.getDisplayWidth / 2 - gameOverLabel.getWidth / 2)

  this.setY(300)
  this.setZ(4)

  withEvents(List(
    (Key.ENTER, () => this.getScene.restartOnEnd)))
        
}