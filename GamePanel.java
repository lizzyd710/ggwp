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
   public GamePanel()
   {   
      player = new Player();  
      obstacle = new Obstacle(500, 700);
      
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
            MoveThread moveThread = new MoveThread("right");
            moveThread.start();
            moveThread = null;
            //player.moveRight();
            //if(obstacle.inObstacle(player))
               //player.setX(player.getX()-10);
            repaint();
         }
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
         {
            leftPressed = true;
            player.moveLeft();
            if(obstacle.inObstacle(player))
               player.setX(player.getX()+10);
            repaint();
         }
         if(e.getKeyCode()==KeyEvent.VK_UP)
         {
            if(upPressed == false)
            {
               upPressed = true;
               if(rightPressed == true)
                  jumpThread = new JumpThread("right");
               else if(leftPressed == true)
                  jumpThread = new JumpThread("left");
               else
                  jumpThread = new JumpThread("none");
               jumpThread.start();
               jumpThread = null;
            }
         }
      }
      public void keyReleased(KeyEvent e)
      {
         if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            rightPressed = false;
         if(e.getKeyCode()==KeyEvent.VK_LEFT)
            leftPressed = false;
         if(e.getKeyCode()==KeyEvent.VK_UP)
            upPressed = false;
      }
   }
   private class JumpThread extends Thread
   {
      private String direction;
      public JumpThread(String d)
      {
         super();
         direction = d;
      }
      public void run()
      {
         for(int x = 1; x <= 20; x++)
         {
            player.jump(direction);
            //player.setX(player.getX() + 1);
            repaint();
            try
            {
               sleep(20);
            }
            catch(Exception e)
            {
               System.out.println("E");
            }
         }
         while(player.getY() < 700)
         {
            player.down(direction);
            repaint();
            try
            {
               sleep(20);
            }
            catch(Exception e)
            {
               System.out.println(e);
            }
         }
         return;
      }
   }
   private class MoveThread extends Thread
   {
      String direction;
      public MoveThread(String dir)
      {
         super();
         direction = dir;
      }
      public void run()
      {
         if(direction == "left")
            player.moveLeft();
         else if(direction == "right")
            player.moveRight();
      }
   }
}
