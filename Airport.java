import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
import java.util.*;
/**
 * Write a description of class airport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Airport  extends Actor
{
    public GreenfootImage image;  
    public Dummy dummy;
    public int number;
    public boolean hub = false;
    public List<Passenger> departing = new ArrayList();
    public List<Passenger> arriving = new ArrayList();
    public List<Passenger> connecting = new ArrayList();
    public int passCount = 0;

    public Airport(Dummy dummy, int number){
        this.dummy = dummy;
        this.number = number;
        setImage(new GreenfootImage(30,30));
        image = getImage();
        image.setColor(Color.ORANGE);
        image.setTransparency(50);
        image.fillOval(1,1,29,29);
        image.setTransparency(255);
        image.setColor(Color.BLACK);
        Font font = image.getFont();
        image.setFont(font.deriveFont(18.0F));
        image.drawString(""+number, 5, 25);
        //System.out.println("airport created" );

     
    }

    /**
     * Act - do whatever the airport wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse.getButton() == 1){
                if(hub){
                    image.clear();
                    image.setColor(Color.ORANGE);
                    image.fillOval(1,1,29,29);
                    image.setColor(Color.BLACK);
                    Font font = image.getFont();
                    image.setFont(font.deriveFont(18.0F));
                    image.drawString(""+number, 5,25);
                    dummy.hubListClicked.remove(this);
                    hub = false;
                }
                else{
                    image.clear();
                    image.setColor(Color.RED);
                    image.fill();
                    image.setColor(Color.BLACK);
                    Font font = image.getFont();
                    image.setFont(font.deriveFont(18.0F));
                    image.drawString(""+number, 5,25);
                    dummy.hubListClicked.add(this);
                    //System.out.println("hubs: " + dummy.hubList);
                    hub = true;
                } 
            }
            
        }

        if (Greenfoot.mouseDragged(this)){
            MouseInfo mouse = Greenfoot.getMouseInfo();
            setLocation(mouse.getX(), mouse.getY());
        }
        
    }//end act() 

}//end Airport()
