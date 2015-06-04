import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.applet.*;

public class GamePanel extends JPanel
{
   private static final ImageIcon BACKGROUND = new ImageIcon("rsz_aotbackground.jpg");
   ImageIcon playerSprite = new ImageIcon("test sprite.png");
   private BufferedImage myImage;
   private Graphics g;
   private boolean leftPressed = false;
   private boolean rightPressed = false;
   private boolean upPressed = false;
   private boolean inJump = false;
   public Player player;
   public Obstacle obstacle;
   public Obstacle[] obstacles;
   public JumpThread jumpThread;
   private final int playerStartingY = 750;  
   private int playerTempX, playerTempY, currentLevelY;
   

   public GamePanel()
   {   
      player = new Player(10, playerStartingY);  
      currentLevelY = playerStartingY;
      
      MoveThread moveThread = new MoveThread();
      moveThread.start();
      GravityThread gThread = new GravityThread();
      //gThread.start();
      addKeyListener(new Key());
      setFocusable(true);
      
      
      //Obstacles
      obstacles = new Obstacle[11];
      //first set of blocks
      obstacles[0] = new Obstacle("kellybrick.png",200, 700);
      obstacles[1] = new Obstacle("kellybrick.png",200, 740);
      obstacles[2] = new Obstacle("kellybrick.png",200, 768);
      
      //second set of blocks
      obstacles[3] = new Obstacle("kellybrick.png", 240, 700);
      obstacles[4] = new Obstacle("kellybrick.png", 280, 700);
      
      //third set of blocks
      obstacles[5] = new Obstacle("kellybrick.png", 320, 670);
      
     
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(BACKGROUND.getImage(), 0, 0, getWidth(), getHeight(), null); //IT WERKS AND IS SCALED AND STUFF
      //for the untitled png the green starts at (x, 520)
      player.draw(g);
      //tent.draw(g);
      for(int x = 0; x < obstacles.length; x++)
         obstacles[x].draw(g);
   }
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
         {
            rightPressed = true;
            repaint();
         }
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
         {
            leftPressed = true;
            repaint();
         }
         if(e.getKeyCode()==KeyEvent.VK_UP)
         {
            MoveThread moveThread;
            if(upPressed == false)
            {
               upPressed = true;
               if(rightPressed == true)
               {
                  //moveThread = new MoveThread("right");
                  //moveThread.start();
                  jumpThread = new JumpThread("right");
               }
               else if(leftPressed == true)
               {
                  //moveThread = new MoveThread("left");
                  //moveThread.start();
                  jumpThread = new JumpThread("left");
               }
               else
                  jumpThread = new JumpThread("none");
               jumpThread.start();
               moveThread = null;
               jumpThread = null;
            }
         }
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
         {
            rightPressed = false;
         }
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
            leftPressed = false;
         if(e.getKeyCode()==KeyEvent.VK_UP)
            upPressed = false;
      }
   }
   private class JumpThread extends Thread
   {
      private String direction;
      private MoveThread moveThreadInJumpThread;
      public JumpThread(String d)
      {
         super();
         direction = d;
      }
      public void run()
      {
         while(true)
         {
            while(upPressed == true)
            {
               inJump = true;
               for(int x = 1; x <= 200; x++)
               {
                  playerTempY = player.getY() - 1;
                  if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
                     player.jump(direction);
                  repaint();
                  try
                  {
                     sleep(2);
                  }
                  catch(Exception e)
                  {
                     System.out.println("E");
                  }
               }
               inJump = false;
               
               //playerTempY = player.getY() + 1;
               
               // while(playerTempY < playerStartingY && player.inObstacle(obstacles, player.getX(), playerTempY) == false)
               // {
               //    //playerTempY = player.getY() + 1;
               //    //if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
                  // if(playerTempY < playerStartingY)
                     // player.down(direction);
               //    //else
               //    //{
               //       //currentLevelY = player.getY();
               //       //break;
               //    //}
                  // playerTempY = player.getY() + 1;
                  // repaint();
                  // try
                  // {
                     // sleep(2);
                  // }
                  // catch(Exception e)
                  // {
                     // System.out.println(e);
                  // }
               // }
               return;
            }
         }
      }
   }
   private class MoveThread extends Thread
   {
      String direction;
      public MoveThread(/*String dir*/)
      {
         super();
         //direction = dir;
      }
      public void run()
      {
         while(true)
         {
            while(/*direction == "left" && */leftPressed == true)
            {
               playerTempX = player.getX() - 1;
               if(player.inObstacle(obstacles, playerTempX, player.getY()) == false)
                  player.moveLeft();
               repaint();
               while(inJump == false && playerTempY < playerStartingY && player.inObstacle(obstacles, player.getX(), playerTempY) == false)
               {
                  //playerTempY = player.getY() + 1;
                  //if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
                  if(playerTempY < playerStartingY)
                     player.down(direction);
                  //else
                  //{
                     //currentLevelY = player.getY();
                     //break;
                  //}
                  playerTempY = player.getY() + 1;
                  repaint();
                  try
                  {
                     sleep(2);
                  }
                  catch(Exception e)
                  {
                     System.out.println(e);
                  }
               }
               try
               {
                  sleep(3);
               }
               catch(Exception e)
               {
                  System.out.println(e);
               }
            }
            while(/*direction == "right" && */rightPressed == true)
            {
               playerTempX = player.getX() + 1;
               if(player.inObstacle(obstacles, playerTempX, player.getY()) == false)
                  player.moveRight();
               repaint();
               while(inJump == false && playerTempY < playerStartingY && player.inObstacle(obstacles, player.getX(), playerTempY) == false)
               {
                  //playerTempY = player.getY() + 1;
                  //if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
                  if(playerTempY < playerStartingY)
                     player.down(direction);
                  //else
                  //{
                     //currentLevelY = player.getY();
                     //break;
                  //}
                  playerTempY = player.getY() + 1;
                  repaint();
                  try
                  {
                     sleep(2);
                  }
                  catch(Exception e)
                  {
                     System.out.println(e);
                  }
               }
               try
               {
                  sleep(3);
               }
               catch(Exception e)
               {
                  System.out.println(e);
               }
            }
            if(player.facingLeft()== true)
               player.setSprite(player.playerSpriteLeft);
            else if(player.facingRight() == true)
               player.setSprite(player.playerSpriteRight);
            repaint();
            while(inJump == false && playerTempY < playerStartingY && player.inObstacle(obstacles, player.getX(), playerTempY) == false)
            {
                  //playerTempY = player.getY() + 1;
                  //if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
               if(playerTempY < playerStartingY)
                  player.down(direction);
                  //else
                  //{
                     //currentLevelY = player.getY();
                     //break;
                  //}
               playerTempY = player.getY() + 1;
               repaint();
               try
               {
                  sleep(2);
               }
               catch(Exception e)
               {
                  System.out.println(e);
               }
            }
         }         
      }
   }
   private class GravityThread extends Thread
   {
      public GravityThread()
      {
         super();
      }
      public void run()
      {
         // while(true)
         // {
            // playerTempY = player.getY() + 1;
            // while(/*playerTempY < playerStartingY && */upPressed == false && player.inObstacle(obstacles, player.getX(), playerTempY) == false)
            // {
               // if(playerTempY < playerStartingY)
               //    //playerTempY = player.getY() + 1;
               //    //if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
                  // player.down("none");
            //       //else
            //       //{
            //          //currentLevelY = player.getY();
            //          //break;
            //       //}
               // playerTempY = player.getY() + 1;
               // repaint();
               // try
               // {
                  // sleep(2);
               // }
               // catch(Exception e)
               // {
                  // System.out.println(e);
               // }
            // }
         // }
         while(true)
         {
            playerTempY = player.getY() + 1;
            while(inJump == false && playerTempY < playerStartingY && player.inObstacle(obstacles, player.getX(), playerTempY) == false)
            {
                  //playerTempY = player.getY() + 1;
                  //if(player.inObstacle(obstacles, player.getX(), playerTempY) == false)
               if(playerTempY < playerStartingY)
                  player.down("none");
                  //else
                  //{
                     //currentLevelY = player.getY();
                     //break;
                  //}
               playerTempY = player.getY() + 1;
               repaint();
               try
               {
                  sleep(2);
               }
               catch(Exception e)
               {
                  System.out.println(e);
               }
            }
         }
      }
   }
}
