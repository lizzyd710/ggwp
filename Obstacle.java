import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
   
public class Obstacle extends Object
{
   ImageIcon obstacle;
   private int xPos;
   private int yPos;
   final private int length=40;
   final private int width=40;
   private boolean isCollidable;
   private boolean isOnScreen;
   private int[] xPosArray, yPosArray;

   public Obstacle(int x, int y)
   {
      xPos=x;
      yPos=y;
      obstacle = new ImageIcon("rsz_brick.png");
      xPosArray = yPosArray = new int[getWidth()];
      int count = 0; 
      for(int ex = xPos; ex < xPosArray.length; ex++)
      {
         xPosArray[count] = ex;
         count++;
      }
      for(int wy = yPos; wy < yPosArray.length; wy++)
      {
         yPosArray[count] = wy;
         count++;
      }
   }
      
   public int getX()
   {
      return xPos;
   }
   
   public int getY()
   {
      return yPos;
   }
   
   public int getWidth()
   {
      return obstacle.getIconWidth();
   }
   
   public int getHeight()
   {
      return obstacle.getIconHeight();
   }

   public void setXPos(int x)
   {
      xPos=x;    
   }
        
   public void setYPos(int y)
   {
      yPos=y;
   }

   public void draw(Graphics g)
   {
      g.drawImage(obstacle.getImage(), getX(), getY(), obstacle.getIconWidth(), obstacle.getIconHeight(),  null);
   }


   // returns true if any part of the polkadot is inside the bumper
   // public boolean inObstacle(Player player)
   // {
      // for(int x = getX(); x <= getX() + getWidth(); x++)   //starts at upper left corner(x,y)
         // for(int y = getY(); y <= getY() + getHeight(); y++)
            // if(distance(x, y, player.getX()+player.getWidth(), player.getY()+player.getHeight()) <= 0 ) //checks every point on the bumper
               // return true;            
      // return false;
   //}  
   
// returns true if any part of the polkadot is inside the bumper
   public boolean inObstacle(Player player)
   {
      for(int x = getX(); x <= getX() + getWidth(); x++)   //starts at upper left corner(x,y)
         for(int y = getY(); y <= getY() + getHeight(); y++)
            for(int px = player.getX(); px <= player.getX() + player.getWidth(); px++)
               for(int py = player.getY(); py <= player.getY() + player.getHeight(); py++)
                  if(x == px || y == py)
                     return true; //checks every point on the bumper
      //return true;            
      return false;
   }  
   public boolean inObstacle(Player player, int tempX, int tempY)
   {
      for(int x = getX(); x <= getX() + getWidth(); x++)   //starts at upper left corner(x,y)
         for(int y = getY(); y <= getY() + getHeight(); y++)
            for(int px =tempX; px <= tempX + player.getWidth(); px++)
               for(int py = tempY; py <= tempY + player.getHeight(); py++)
                  if(x == px && y == py)
                     return true;
      //return true;            
      return false;
   }  
    
    // returns distance between (x1, y1) and (x2, y2)
   private double distance(double x1, double y1, double x2, double y2)
   {
      return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
   }	
   public int[] getXPosArray()
   {
      return xPosArray;
   }
   public int[] getYPosArray()
   {
      return yPosArray;
   }
   
}
