package quantumPeaceMaker


import com.uqbar.vainilla.GameComponent
import CollisionGroup._

object CollisionGroups {
  val ENEMY: CollisionGroup = (aComponent:GameComponent[_]) => aComponent.isInstanceOf[Enemy]
  val PLAYER: CollisionGroup = (aComponent:GameComponent[_]) => aComponent.isInstanceOf[QuantumShip]
}