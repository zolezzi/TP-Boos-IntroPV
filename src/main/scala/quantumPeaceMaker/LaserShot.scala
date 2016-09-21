package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.DeltaState

class LaserShot(scene: QuantumPeaceMakerScene, x: Double, y: Double, xSpeed:Double) extends Proyectile(ResourceManager.LASERSHOT_SPRITE, scene ,x y, 12, 1200, xSpeed)