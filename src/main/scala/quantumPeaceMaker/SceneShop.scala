package quantumPeaceMaker

import com.uqbar.vainilla.GameComponent
import com.uqbar.vainilla.appearances.Label
import java.awt.Font
import java.awt.Color
import com.uqbar.vainilla.events.constants.Key
import ar.pablitar.vainilla.commons.components.RichGameComponent
import com.uqbar.vainilla.DeltaState
import java.awt.Graphics
import java.awt.Graphics2D
import com.uqbar.vainilla.appearances.Rectangle

class SceneShop(scene:QuantumPeaceMakerScene) extends EventDrivenGameComponent[QuantumPeaceMakerScene] {
  this.setScene(scene)
  var B1 = 1000;
  var B2 = 1000;
  var B3 = 1000;
  
//  val gameOverLabel = new Label(new Font(Font.SERIF, Font.BOLD, 48), Color.RED, "Game Over!", "Press enter to restart")
//  
//  this.setAppearance(gameOverLabel)
//  
//  this.setX(this.getGame.getDisplayWidth / 2 - gameOverLabel.getWidth / 2)
//
//  this.setY(300)
  this.setZ(10)
  this.setAppearance(new Rectangle(Color.DARK_GRAY, this.getGame().getDisplayWidth(), this.getGame().getDisplayHeight()))
  
   override def render(g:Graphics2D){
    super.render(g)
     g.setColor(Color.WHITE)
     g.setFont(new Font("arial", 0, 50))
     g.drawString("SHOP", 300, 50)
   
    //BOX 1 
     g.setFont(new Font("arial", 0, 12))
     g.drawString("Upgrade Health", 110, 120)
     //g.drawString("Cost" + B1, 110, 140)
     g.drawRect(110, 100, 100, 80)
     
    //BOX 2 
     g.drawString("Upgrade speed", 250, 120)
     //g.drawString("Cost" + B2, 260, 50)
     g.drawRect(250, 100, 100, 80)
    
     //BOX 3 
     g.drawString("Refill Health", 410, 120)
     //g.drawString("Cost" + B3, 410, 50)
     g.drawRect(400, 100, 100, 80)
    
   }
  
  def destroyShop(){
    this.destroy()
  }
  
 // withEvents(List(
 //   (Key.ENTER, () => this.getScene)))
        
}