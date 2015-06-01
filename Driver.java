import javax.swing.JFrame;
import java.applet.*;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
public class Driver
{
   private static final int BUFFER_SIZE = 128000;
   private static File soundFile;
   private static AudioInputStream audioStream;
   private static AudioFormat audioFormat;
   private static SourceDataLine sourceLine;
   private static AudioClip audioClip;
   public static void main(String[]args)
   {
      playSound();
      try
      {
         //Thread.sleep(1000);
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      
      //Sound.stop(aud);
   
      JFrame frame = new JFrame("GGWP");
      frame.setSize(768,614);
      frame.setLocation(0,0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
      frame.setContentPane(new GamePanel());
      frame.setVisible(true);
   }
   public static void playSound(){
      Applet applet = new Applet();
   
      try {
         // java.applet.AudioClip clip =
            // java.applet.Applet.newAudioClip(
            // new java.net.URL("file:M:/Computer Science/CS/Final Project Testing Stuffs/Insight.wav"));
         // clip.play();
         audioClip = applet.getAudioClip(new URL("M:/Computer Science/CS/Final Project Testing Stuffs/Insight.wav"),"M:/Computer Science/CS/Final Project Testing Stuffs/Insight.wav");
         audioClip.play();
      } 
      catch (java.net.MalformedURLException murle) {
         System.out.println(murle);
      }
   
   
   }   
}
