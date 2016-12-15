package quantumPeaceMaker


import com.uqbar.vainilla.appearances.Sprite
import com.uqbar.vainilla.appearances.Animation
import com.uqbar.vainilla.sound.SoundBuilder

object Resources {

    val spriteSize = 200
    val scale = 0.8

    
    def spriteHeight = (700 * scale).toInt
    def spriteWidth = (100 * scale).toInt
  
    val enemySprite = Sprite.fromImage("transparentUno.png").center()
  
    val laserShotSprite = Sprite.fromImage("laserRed-difuso.png").center()
    
    val miniLaserShotSprite = Sprite.fromImage("miniDisparo.png").center()
 
    val spriteExplosion = Sprite.fromImage("explosion.png").center()
    
    val spriteExplosionNave = Sprite.fromImage("explosionNave.png").center()
  
    val quantumShip = Sprite.fromImage("QuantumShip.png").center()
  
    val enemy1 = Sprite.fromImage("transparentDos.png").center() 
  
    val planetSprite = Sprite.fromImage("planet.png").center()
 
    val bossSprite = Sprite.fromImage("Boss.png").center()
    
    val laserSound = new SoundBuilder().buildSound(this.getClass.getClassLoader.getResourceAsStream("laser.wav"))  

    val backgroundSound = new SoundBuilder().buildSound(this.getClass.getClassLoader.getResourceAsStream("backgroundSound.wav"))
   
    val explosion = new SoundBuilder().buildSound(this.getClass.getClassLoader.getResourceAsStream("explosion.wav"))

    val spriteSheet =  Sprite.fromImage("explosionNave.png").center()
    
    val spritesheet = Sprite.fromImage("thundeTransparentAnimation.png").scale(scale)
    
    val laserAnimation = animationFromSpritesheet(spritesheet, spriteWidth, spriteHeight, 0.40)
    
    val laserIdle = spritesheet.crop(0, 0, spriteWidth, spriteHeight).center()
    
 
  def animationFromSpritesheet(spritesheet: Sprite, width: Int, height: Int, frameTime: Double) = {
    val frameCountH = (spritesheet.getWidth / width).toInt
    val frameCountV = (spritesheet.getHeight / height).toInt

    val sprites = for (
      i <- 0 to (frameCountH - 1);
      j <- 0 to (frameCountV - 1)
    ) yield {
      spritesheet.crop(width * i, height * j, width, height).center()
    }

    new Animation(frameTime, sprites: _*)
  }
    
}