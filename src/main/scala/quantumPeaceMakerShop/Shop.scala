package quantumPeaceMakerShop

import java.awt.event.MouseAdapter
import java.awt.Graphics
import java.awt.Color
import java.awt.Font

class Shop extends MouseAdapter{
  
  var B1 = 1000;
  var B2 = 1000;
  var B3 = 1000;
   
  def render(g:Graphics){
     g.setColor(Color.WHITE)
     g.setFont(new Font("arial", 0, 50))
     g.drawString("SHOP", 400, 50)
   
    //BOX 1 
     g.setFont(new Font("arial", 0, 12))
     g.drawString("Upgrade Health", 110, 120)
     g.drawString("Cost" + B1, 110, 140)
     g.drawRect(100, 100, 100, 80)
     
    //BOX 2 
     g.drawString("Upgrade speed", 250, 120)
     g.drawString("Cost" + B2, 260, 50)
     g.drawRect(250, 100, 100, 80)
    
     //BOX 3 
     g.drawString("Refill Health", 410, 120)
     g.drawString("Cost" + B3, 410, 50)
     g.drawRect(400, 100, 100, 80)
    
   
   }
  
}