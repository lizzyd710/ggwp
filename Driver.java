import javax.swing.JFrame;
import java.applet.*;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
public class Driver
{
   public static void main(String[]args)
   {
      JFrame frame = new JFrame("GGWP");
      frame.setSize(768,614);
      frame.setLocation(0,0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
      frame.setContentPane(new GamePanel());
      frame.setVisible(true);
   }
}
