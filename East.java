import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class East here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class East  extends World
{

//     GreenfootImage easternCities = new GreenfootImage("eastern_cities.png");
    public int width = 800;
    public int height = 800;
    /**
     * Constructor for objects of class East.
     * 
     */
    
    public East()
    {    
        // Create a new world with 20x20 cells with a cell size of 10x10 pixels.
        super(800, 800, 1);
//         GreenfootImage image = new GreenfootImage(800,800);
//         int imageWidth = easternCities.getWidth();
//         int imageHeight = easternCities.getHeight();
//         GreenfootImage scaledImage = easternCities;
//         scaledImage.scale(imageWidth*height/imageHeight, height);
//         image.drawImage(scaledImage, 1, 1);
//         setBackground(image);
        setPaintOrder(Airport.class, Map.class);
        populate();
         
    }
    
    private void populate()
    {

        addObject(new Dummy(), 800, 1);
        //FrameWorks frameInfo = new FrameWorks();
        
   
    } 
    
    
        
}
