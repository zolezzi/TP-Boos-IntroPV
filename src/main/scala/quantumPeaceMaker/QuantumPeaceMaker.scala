package quantumPeaceMaker

import com.uqbar.vainilla.Game
import java.awt.Dimension

class QuantumPeaceMaker extends Game{
    def getDisplaySize(): Dimension = {
    new Dimension(800, 600)
  }
  
  def getTitle(): String = {
    "Quantum Peace Maker"
  }
  
  def setUpScenes(): Unit = {
   this.setCurrentScene(new QuantumPeaceMakerScene())
  }
  
  def initializeResources(): Unit = {
    //Nada por ahora
  }
}