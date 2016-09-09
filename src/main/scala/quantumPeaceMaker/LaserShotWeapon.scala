package quantumPeaceMaker

class LaserShotWeapon(scene: QuantumPeaceMakerScene, ship: QuantumShip) extends Weapon{
   
  val coolDownTime = 0.12
  
  def doFire = {
    val xSpeed = (QuantumPeaceMakerGame.randomizer.nextDouble - 0.5) *  300
    this.getScene.addComponent(new LaserShot(this.getScene,ship.getX, ship.getY, xSpeed)
  }
}