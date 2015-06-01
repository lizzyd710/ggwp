import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.ImageIcon;
import java.applet.*;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;

public class InstructionsPanel extends JPanel
{
   private static final ImageIcon TEST = new ImageIcon("instructions.png");

   /*public static void main(String[]args)
   {
      JFrame frame = new JFrame("InstructionsPanel");
      frame.setSize(768,614);
      frame.setLocation(0,0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new InstructionsPanel());
      frame.setVisible(true);
   }*/
   
  
   public void paintComponent(Graphics g)
   {
      g.drawImage(TEST.getImage(), 0, 0, getWidth(), getHeight(), null);//for the untitled png the green starts at x, 520
      g.setFont(new Font("monospaced", Font.BOLD, 24));
   }

   public InstructionsPanel()
   {
      setLayout(new FlowLayout());
      
      //insert instructions here
      
     /* JLabel label = new JLabel("Instjioslkfoiajsdklfiojsdklfdjsioklfji");
      label.setFont(new Font("Serif", Font.BOLD, 100));
      label.setForeground(Color.blue);
      add(label);    */  
   }  
}

//RANDOM NOTE
//LEVELS METHOD (REPAINT METHOD)
