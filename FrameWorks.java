import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class FrameWorks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FrameWorks  extends Actor
{
    public FrameWorks (){
        //make a scrollable frame for the greenfoot frame
           Frame theFrame = new Frame();
           Frame[] frameArray = theFrame.getFrames();
           System.out.println("frames: " + frameArray.length);
           System.out.println(frameArray[0]);
           Component[] frameComponents = frameArray[0].getComponents();
           System.out.println(frameComponents[0]);
         
           System.out.println("components: " + frameComponents.length);
           
           JScrollPane scrollingArea = new JScrollPane();
           JPanel content = new JPanel();
           content.setPreferredSize(new Dimension(500, 700));

        content.setLayout(new BorderLayout());
        content.add(scrollingArea, BorderLayout.CENTER);
           JFrame newFrame = new JFrame();
           newFrame.setContentPane(content);
        newFrame.setTitle("Airport Hubs Output");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        scrollingArea.getViewport().setView(frameComponents[0]); 
        newFrame.setVisible(true);  
        frameArray[0].setVisible(false);
        }
    public void act() 
    {
        // Add your action code here.
    }    
}
