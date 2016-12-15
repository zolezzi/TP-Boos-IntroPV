package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import java.awt.Color

class HealthBar(ship:QuantumShip, x:Double, y:Double) extends Bar("Health", Color.RED, x, y) {
  
  def getCurrentValue = ship.health
  def getMaxValue = ship.maxHealth
  
  
}