package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent

class LaserShotWeapon(scene: QuantumPeaceMakerScene, ship: QuantumShip) extends Weapon{
   
  val coolDownTime = 0.12
  
  def doFire = {
    val xSpeed = (QuantumPeaceMakerGame.randomizer.nextDouble - 0.5) *  100
    var laser = new LaserShot(this.getScene,ship.getX, ship.getY, xSpeed)
    scene.addComponent(laser)
 //   Resources.laserSound.play(0.1f)
  }
}