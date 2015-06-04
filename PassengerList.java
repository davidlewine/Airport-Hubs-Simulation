import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.util.*;
// import java.awt.*;
// import java.awt.event.*;
/**
 * Write a description of class PassengerList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PassengerList  extends Actor
{

    public List<Passenger> passengerList = new ArrayList();
    
    public void AddedToWorld(World east){
        
        int numPassengers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of passengers"));
        for(int i = 0; i<numPassengers;i++){
        }
    }
            
    /**
     * Act - do whatever the PassengerList wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
