package quantumPeaceMaker

import scala.collection._

object ControllerTheCollision {
   
  
  //var enemigos: List[(Enemy)] = List()
  var enemigos = mutable.MutableList[Enemy]()
  //var lasers: List[(LaserShot)] = List()
  var lasers = mutable.MutableList[LaserShot]()
  
  var enemigosMosquitos = mutable.MutableList[EnemyLateral]()
  
  
  def addEnemy (enemy: Enemy){
    this.enemigos.+=(enemy) 
  }

  def addEnemyLateral (enemy: EnemyLateral){
    this.enemigosMosquitos.+=(enemy) 
  }  
  
  def addLaser(laser: LaserShot){
    this.lasers.+=(laser)
  }
 
  def removeLaser(laser : LaserShot){
    var res = mutable.MutableList[LaserShot]()
    for(l <- this.lasers){
      if(l != laser){
        res.+=(l)
      }
    }
    this.lasers = res
  }
  
  def removeEnemy(enemy : Enemy){
    var res = mutable.MutableList[Enemy]()
    for(e <- this.enemigos){
      if(e != enemy){
        res.+=(e)
      }
    }
    this.enemigos = res
  }

  def removeEnemyLateral(enemy : EnemyLateral){
    var res = mutable.MutableList[EnemyLateral]()
    for(e <- this.enemigosMosquitos){
      if(e != enemy){
        res.+=(e)
      }
    }
    this.enemigosMosquitos = res
  }
  
  def restart()={
    this.enemigos = mutable.MutableList[Enemy]()
    this.enemigosMosquitos = mutable.MutableList[EnemyLateral]()
    this.lasers = mutable.MutableList[LaserShot]()
  }
  
  def getSize(){
    enemigos.size
  }
  
}