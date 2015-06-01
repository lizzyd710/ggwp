/*
*@author Elizabeth Sherrock
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.lang.Thread;
public class Player// extends JPanel
{
   private ImageIcon playerSprite;
   private final ImageIcon playerSpriteRight = new ImageIcon("test sprite.png");
   private final ImageIcon playerSpriteLeft = new ImageIcon("test sprite left.png");
   private final ImageIcon playerSpriteMovingRight = new ImageIcon("test sprite right moving.gif");
   private final ImageIcon playerSpriteMovingLeft = new ImageIcon("test sprite moving left.gif");
   private boolean facingLeft, facingRight;
   private int xPos, yPos;
   
   /**
   *Default player constructer which instantiates a player at X: 10 and Y: 700.
   */
   public Player()
   {
      //t = new Timer(10);
      playerSprite = playerSpriteMovingRight;
      facingRight = true;
      facingLeft = false;
      xPos=10;
      yPos=700;
   }
   /**
   *Class constructor specifying the player's starting X and Y position.
   *@param x The initial X position of the player.
   *@param y The initial Y position of the player.
   */
   public Player(int x, int y)
   {
      playerSprite = playerSpriteRight;
      xPos = x;
      yPos = y;
   }
   /**
   * Returns the height of the player's image.
   *@return The height of the ImageIcon playerSprite in pixels.
   */
   public int getHeight()
   {
      return playerSprite.getIconHeight();
   }
   /**
   *Returns the width of the player's image.
   *@return The width of the ImageIcon playerSprite in pixels.
   */
   public int getWidth()
   {
      return playerSprite.getIconWidth();
   }
   /**
   *Returns the player's current X position.
   *@return The X position of Player.
   */
   public int getX()
   {
      return xPos;
   }
   /**
   *Returns the player's current Y position.
   */
   public int getY()
   {
      return yPos;
   }
   /**
   *Moves the player ten pixels to the left.
   */
   public void moveLeft()
   {
      facingLeft = true;
      facingRight = false;
      playerSprite = playerSpriteMovingLeft;
      xPos -= 15;
   }
   /**
   *Moves the player ten pixels to the right.
   */
   public void moveRight()
   {
      facingLeft = false;
      facingRight = true;
      playerSprite = playerSpriteMovingRight;
      xPos += 15;
   }
   
   public void draw(Graphics g)
   {
      g.drawImage(playerSprite.getImage(), getX(), getY(), playerSprite.getIconWidth(), playerSprite.getIconHeight(),  null);
   }
   /**
   *Sets playerSprite to the file specified.
   *@param fileName The path or file name of the image 
   */
   public void setSprite(String fileName)
   {
      playerSprite = new ImageIcon(fileName);
   }
   /**
   *Sets playerSprite to the ImageIcon passed.
   *@param i The new ImageIcon
   */
   public void setSprite(ImageIcon i)
   {
      playerSprite = i;
   }
   /**
   *Returns true if the character is facing left.
   *@return facingLeft True if the character is facing right, false if facing left.
   */
   public boolean facingLeft()
   {
      return facingLeft;
   }
   /**
   *Returns true if the character is facing right.
   *@return facingRight True if the character is facing right, false if facing left.
   */
   public boolean facingRight()
   {
      return facingRight;
   }
   /**
   *Makes the player 'jump' by changing the Y position.
   *@param direction What direction the Player will jump.
   */
   public void jump(String direction)
   {
      if(facingLeft() == true)
         setSprite(playerSpriteLeft);
      else if(facingRight() == true)
         setSprite(playerSpriteRight);
      if(direction == "left")
         xPos-= 10;
      else if(direction == "right")
         xPos+= 10;
      yPos-= 10;
   }
   /**
   *Brings the player back to the ground after a jump.
   *@param direction What direction the Player will fall.
   */
   public void down(String direction)
   {
      if(facingLeft() == true)
         setSprite(playerSpriteLeft);
      else if(facingRight() == true)
         setSprite(playerSpriteRight);
      if(direction == "left")
         xPos-= 10;
      else if(direction == "right")
         xPos+= 10;
      yPos+= 10;
   }
}