import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.applet.*;

public class GamePanel extends JPanel
{
   private static final ImageIcon BACKGROUND = new ImageIcon("Untitled with changing colors.png");
   ImageIcon playerSprite = new ImageIcon("test sprite.png");
   private BufferedImage myImage;
   private Graphics g;
   private boolean leftPressed = false;
   private boolean rightPressed = false;
   private boolean upPressed = false;
   public Player player;
   public Obstacle obstacle;
   public JumpThread jumpThread;
   private final int playerStartingY = 750;  
   private int playerTempX, playerTempY;
   public GamePanel()
   {   
      player = new Player(10, playerStartingY);  
      obstacle = new Obstacle(500, 725);
      MoveThread moveThread = new MoveThread();
      moveThread.start();
      addKeyListener(new Key());
      setFocusable(true);
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage(BACKGROUND.getImage(), 0, 0, getWidth(), getHeight(), null); //IT WERKS AND IS SCALED AND STUFF
      //for the untitled png the green starts at (x, 520)
      player.draw(g);
      obstacle.draw(g);
   }
   private class Key extends KeyAdapter
   {
      public void keyPressed(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
         {
            rightPressed = true;
            //playerTempX = player.getX() + 10;
            //if(obstacle.inObstacle(player, playerTempX, player.getY()) == false)
            //MoveThread moveThread = new MoveThread("right");
            //moveThread.start();
            //moveThread = null;
               //player.moveRight();
            repaint();
         }
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
         {
            leftPressed = true;
            //playerTempX = player.getX() - 10;
            //if(obstacle.inObstacle(player, playerTempX, player.getY()) == false)
               //player.moveLeft();
            //MoveThread moveThread = new MoveThread("left");
            //moveThread.start();
            //moveThread = null;
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
               for(int x = 1; x <= 200; x++)
               {
                  playerTempY = player.getY() + 1;
                  if(obstacle.inObstacle(player, player.getX(), playerTempY) == false)
                     player.jump(direction);
               //if(direction == "right" && rightPressed == true)
               //{
               //MoveThread moveThreadInJumpThread = new MoveThread("right");
               //moveThreadInJumpThread.start();
               //moveThreadInJumpThread = null;
               //}
               //else if(direction == "left" && leftPressed == true)
               //{
               //MoveThread moveThreadInJumpThread = new MoveThread("left");
               //moveThreadInJumpThread.start();
               //moveThreadInJumpThread = null;
               //}
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
               while(player.getY() < playerStartingY)
               {
                  playerTempY = player.getY() + 1;
                  if(obstacle.inObstacle(player, player.getX(), playerTempY) == false)
                     player.down(direction);
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
         //for(int x = 0; x < 10; x++)
         //{
            while(/*direction == "left" && */leftPressed == true)
            {
               playerTempX = player.getX() - 1;
               if(obstacle.inObstacle(player, playerTempX, player.getY()) == false)
                  player.moveLeft();
               repaint();
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
               if(obstacle.inObstacle(player, playerTempX, player.getY()) == false)
                  player.moveRight();
               repaint();
               try
               {
                  sleep(3);
               }
               catch(Exception e)
               {
                  System.out.println(e);
               }
            }
            repaint();
         //}
         }  //return;
      }
   }
}
