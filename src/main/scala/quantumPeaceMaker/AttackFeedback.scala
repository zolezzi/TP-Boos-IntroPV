
package quantumPeaceMaker

import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color
import ar.pablitar.vainilla.commons.math.TimedValue
import com.uqbar.vainilla.DeltaState
import java.awt.Graphics2D
import com.uqbar.vainilla.GameComponent
import ar.pablitar.vainilla.commons.math.InterpolatorKind
import ar.pablitar.vainilla.commons.math.Vector2D
import java.awt.AlphaComposite
import java.lang.StrictMath.RandomNumberGeneratorHolder
import java.util.Random


class AttackFeedback(scoreInput:Int) extends RichGameComponent[QuantumPeaceMakerScene] {
  
  val duration = 0.9
  val animationProgress = new TimedValue(duration, InterpolatorKind.EaseOutQuad)
  
  def t = animationProgress.currentValue
  
  def scale = 1 + 5 * t - t * t * 5
  //def distanceOffset = Vector2D(t * 2, -5 * t + 8 * t * t * t) * 100
  
  def distanceOffset = Vector2D(t * 2, -5 * t + 8 * t * t * t) * 100
  def alpha = (1 - t).toFloat
  
  var randomFeed = new Random()
  
  var score = 0.0
  
  def setScore(score: Double) {
    this.score = score
  }
  
  def getRand():Integer ={
    var res = randomFeed.nextInt() % 250
    if(res <0)
    {
     res =  res* -1
     }
    return res
  }
  
  this.setAppearance(LabelUtils.damageText(scoreInput.toString, Color.YELLOW))
  this.position = (400,300)
  
  
  override def update(state: DeltaState) = {
    animationProgress.update(state.getDelta)
    if(animationProgress.isOverDuration) {
      this.destroy()
    }
  }
  
  override def render(g: Graphics2D) = {
    val beforeTransform = g.getTransform
    val beforeComposite = g.getComposite
    g.translate(getX + distanceOffset.x1, getY + distanceOffset.x2)
    g.scale(scale, scale)
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha))
    this.getAppearance.render(new GameComponent(), g)
    g.setComposite(beforeComposite)
    g.setTransform(beforeTransform)
  }
}