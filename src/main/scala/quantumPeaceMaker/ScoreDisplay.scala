package quantumPeaceMaker

import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color


class ScoreDisplay(scene: QuantumPeaceMakerScene) extends RichGameComponent[QuantumPeaceMakerScene] {
  var score = 0
  var maxCombo = 0
  var combo = 0

  this.setScene(scene)

  this.updateLabel

  this.setX(620)
  this.setY(10)
  this.setZ(5)

  def updateLabel = {
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.RED, "Score: " + score))
    this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 25), Color.WHITE.darker(), "Score: " + score ))
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 18), Color.BLACK, "Score: " + score ))
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.GREEN, "MaxCombo: " + maxCombo))
    //this.setAppearance(new Label(new Font(Font.SANS_SERIF, Font.BOLD, 20), Color.BLUE, "Combo: " + combo))
  
  }

  def sumScore(n: Int) = {
    score += n 
    this.updateLabel
  }
  
  def sumCombo = {
    combo += 1
    this.updateLabel
  }
  def resetCombo = {
    combo = 0
    this.updateLabel
  }
  def sumMaxCombo = {
    if(maxCombo < combo){
      maxCombo = combo
      this.updateLabel
    } 
  }
  
}