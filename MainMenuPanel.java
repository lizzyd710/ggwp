import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.ImageIcon;
import java.applet.*;
import java.util.*;

public class MainMenuPanel extends JPanel //extend WindowListener & try the method windowOpened inside the Start Listener such as if(windowOpened == true), then blah blah blah or somethi
{
   private static final ImageIcon TEST = new ImageIcon("Untitled.png");
   private static final ImageIcon DERPMARIO = new ImageIcon("spidermandance.gif");
   
   private BufferedImage myImage;
   private Graphics2D myBuffer;
   public static Sound sound;
   public static AudioClip audioclip;
   private JDialog mydialog;
   public static JFrame frame;
   public AudioClip aud;

   public static void main(String[]args)
   {
      frame = new JFrame("MainMenuPanel");
      frame.setSize(768,614);
      frame.setLocation(0,0);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setContentPane(new MainMenuPanel());
      frame.setVisible(true);
   }

   public void paintComponent(Graphics g)
   {
      g.drawImage(TEST.getImage(), 0, 0, getWidth(), getHeight(), null);//for the untitled png the green starts at x, 520
      g.setFont(new Font("monospaced", Font.BOLD, 24));
   }

   public MainMenuPanel()
   {
      setLayout(new BorderLayout());
      
      //Extranous Panels
      JPanel subpanelHolder = new JPanel();
      subpanelHolder.setOpaque(false);
      
      JPanel subpanel = new JPanel();
      subpanel.setOpaque(false);
      subpanel.setLayout(new GridLayout(1,3));
      
      //Buttons
      JButton start = new JButton("Start");
      start.addActionListener(new StartGameListener());
      subpanel.add(start);
      
      JButton settings = new JButton("Settings");
      settings.addActionListener(new SettingsListener());
      settings.setEnabled(false);
      subpanel.add(settings);
   
      JButton exit = new JButton("Quit");
      exit.addActionListener(new QuitListener());
      subpanel.add(exit);
      
      //Instructions Button & Panel
      JButton instructions = new JButton("Instructions");
      instructions.addActionListener(new InstructionsListener());
         
      subpanel.add(instructions);
      
      //Other Stuff
      subpanelHolder.add(subpanel);
      add(subpanelHolder, BorderLayout.SOUTH);
      setOpaque(false);
      
      ImageIcon icon1 = new ImageIcon("MARIO.gif");
      JLabel DERPMARIO = new JLabel("ayyyyy",icon1,JLabel.CENTER);
      DERPMARIO.setVerticalTextPosition(JLabel.BOTTOM);
      DERPMARIO.setHorizontalTextPosition(JLabel.CENTER);
      add(DERPMARIO);
      
      //Sound
      audioclip = sound.getClip("Insight.wav");
      sound.loop(audioclip);
   
      
   }
   
                                    //LISTENERS//
                                    
                                                          
//StartButton   
   private class StartGameListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JFrame game = new JFrame("GamePanel");
         game.setSize(768,614);
         game.setLocation(0,0);
         game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         game.setContentPane(new GamePanel());
         game.setVisible(true);
         
         
      
         frame.dispose();
         setVisible(false);
      
      }
   }
   
//SettingsButton   
   private class SettingsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      
      }
   }
   
//QuitButton 
   private class QuitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   
//InstructionsButton
   private class InstructionsListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         JFrame instruction = new JFrame("InstructionsPanel");
         instruction.setSize(480, 270);
         instruction.setLocation(0,0);
         instruction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         instruction.setContentPane(new InstructionsPanel());
         instruction.setVisible(true);
      }
   }
//Sound Stuff
   
   
}
