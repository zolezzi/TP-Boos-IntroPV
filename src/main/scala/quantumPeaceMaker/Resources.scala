package quantumPeaceMaker


import com.uqbar.vainilla.appearances.Sprite
import com.uqbar.vainilla.appearances.Animation
import com.uqbar.vainilla.sound.SoundBuilder

object Resources {

    val spriteSize = 200
    val scale = 0.8

    
    def spriteHeight = (200 * scale).toInt
  
    def spriteWidth = (200 * scale).toInt
  
    val enemySprite = Sprite.fromImage("transparentUno.png").center()
  
    val laserShotSprite = Sprite.fromImage("laserRed-difuso.png").center()
    
    val miniLaserShotSprite = Sprite.fromImage("miniDisparo.png").center()
 
    val spriteExplosion = Sprite.fromImage("explosion.png").center()
    
    val spriteExplosionNave = Sprite.fromImage("explosionNave.png").center()
  
    val quantumShip = Sprite.fromImage("QuantumShip.png").center()
  
    val enemy1 = Sprite.fromImage("transparentDos.png").center() 
  
    val planetSprite = Sprite.fromImage("planet.png").center()
  
    val laserSound = new SoundBuilder().buildSound(this.getClass.getClassLoader.getResourceAsStream("laser.wav"))  

    val backgroundSound = new SoundBuilder().buildSound(this.getClass.getClassLoader.getResourceAsStream("backgroundSound.wav"))
   
    val explosion = new SoundBuilder().buildSound(this.getClass.getClassLoader.getResourceAsStream("explosion.wav"))
}