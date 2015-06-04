import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map  extends Actor
{
    public GreenfootImage image; 
    public int width = 500;
    public int height = 800;
    GreenfootImage easternCities = new GreenfootImage("images/eastern_cities.png");
    
    public Map(){
   
    
    
        
       setImage(new GreenfootImage(width,height));
       image = getImage();
       int imageWidth = easternCities.getWidth();
       int imageHeight = easternCities.getHeight();
       GreenfootImage scaledImage = easternCities;
       scaledImage.scale(imageWidth*height/imageHeight, height);
       image.drawImage(scaledImage, 1, 1);
        //System.out.println("map created" );
        
    }





    /**
     * Act - do whatever the map wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
