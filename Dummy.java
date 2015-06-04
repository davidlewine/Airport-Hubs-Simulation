import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.util.*;
import java.math.*;
/**
 * Write a description of class Dummy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dummy  extends Actor
{

    public List<Airport> airportList = new ArrayList();
    public List<Airport> hubList = new ArrayList();
    public List<Airport> hubListClicked = new ArrayList();
    public List<Passenger> passengerList = new ArrayList();
    public int[][] firstLeg;  
    public int[][] secondLeg; 
    public Map map;
    public PButton pButton;
    public PlaneCapacityButton capacityButton;
    public RunButton runButton;
    public HubButton hubButton;
    public ScrollButton scrollButton;
    public double numFlights = 0;
    public double totalDistance = 0;
    public double avgDistance = 0;
    public double maxPass = 170;
    public int iterationsA = 0;
     public int iterationsH = 0;
      public int iterationsP = 0;
      public int numPassengers = 0;
      public int numAirports = 0;
      public int numHubs = 0;
      public double firstLegDistance = 0;
      public double secondLegDistance = 0;
      public double legDistance = 0;
      public Airport tempFrom;
      public Airport tempTo;
      public boolean secondLegQ;
      public Output outputWin = new Output();

    
    public Dummy(){
       
       
        
    }
    public void addedToWorld(World east)
        {
           
           map = new Map();
           getWorld().addObject(map,250,400);
           pButton = new PButton();
           getWorld().addObject(pButton,600,250);
           runButton = new RunButton();
           getWorld().addObject(runButton,600,450);
           hubButton = new HubButton();
           getWorld().addObject(hubButton,600,150);
//             scrollButton = new ScrollButton();
//             getWorld().addObject(scrollButton,600,550);
           capacityButton = new PlaneCapacityButton();
           getWorld().addObject(capacityButton,600,350);
           outputWin.setVisible(true);
  

        }
        
        public void setMaxCapacity(){
             maxPass = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter maximum number of passengers per flight (plane capacity)"));
             outputWin.addText("plane capacity: " + maxPass);
    }
    
        public void createPassengers(){
        outputWin.addText (" ");
        outputWin.addText ("######################################### ");
        outputWin.addText ("######################################### ");
        outputWin.addText (" ");
        passengerList.clear();
        numPassengers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of total passengers traveling to all cities"));
        outputWin.addText("passenger total: " + numPassengers);
       
            
            for(int i = 0; i<numPassengers;i++){
                Passenger tempPassenger = new Passenger();
                tempPassenger.number = i+1;
                tempPassenger.start =  Math.floor(Math.random()*airportList.size());
                tempPassenger.end = Math.floor(Math.random()*airportList.size()); 
                while(tempPassenger.end == tempPassenger.start){
                    tempPassenger.end =  Math.floor(Math.random()*airportList.size());
                  }
                passengerList.add(tempPassenger);
               
               System.out.println("passenger " + (i) + ": start: " + tempPassenger.start + "; end: " + tempPassenger.end );
               //(airportList.get(i)).departing.add(tempPassenger);
            }
            
            
        }
        
    public void createAirports(){
        
      int[] xValues = new int[] {463,492,446,481,460,430,397,347,437,409,403,331,319,274,235,203,120,90,128,94,78,35,38,100,178,219,194,
                                178,255,227,297,404,442,384,335,339,248,186,206,102,80,114,340,267,354,330,400,447};
      int[] yValues = new int[] {142,167,198,220,258,289,292,208,317,316,334,220,299,278,251,251,249,255,283,358,394,400,525,504,465,396,
                                403,352,332,358,376,382,395,439,469,509,534,546,587,597,658,668,621,636,682,703,756,281};
      Airport tempAirport; 
      for(int i=0; i<48; i++){
           tempAirport = new Airport(this, airportList.size());
            getWorld().addObject(tempAirport,xValues[i],yValues[i]);
            airportList.add(tempAirport); 
        }//end for
    }//end createAirports  
    
    public void createScrollWindow(){
        
        FrameWorks frameInfo = new FrameWorks();
    }
    
    
        
    public void scheduleFlights(){
        double tempHubDistance = 2000;
        double minHubDistance = 2000;
        double closestHub;
        int passCount = 0;
        double additionalFlights = 0;
        numAirports = airportList.size();
        numHubs = hubListClicked.size();
        hubList = new ArrayList<Airport>();
        
        //Copy hubListClicked to huList.
        //If size of hubListClicked == 0, count all airports as hubs.
        //This has the effect of having no hubs.
        if(numHubs == 0){
            for(Airport ap: airportList){
                hubList.add(ap);
            }
        }
        else{
            for(Airport ap: hubListClicked){
                hubList.add(ap);
            }
        }
        
        //outputWin = new Output();
        

        outputWin.addText ("***************************************** ");
        outputWin.addText (" ");
        outputWin.addText("Number of passengers: " + numPassengers);
        outputWin.addText (" ");
        
//             //list airport locations.
//             Airport tempAirport;
//             Iterator A = airportList.iterator();
//             while(A.hasNext()){
//                 tempAirport = (Airport)A.next();
//                 System.out.println("Airport " + tempAirport.number + ": X: " + tempAirport.getX() + ", Y: " + tempAirport.getY());
//             }
            
        
      
      Airport tempAirport;
      Airport tempHub;
      Passenger tempPassenger;
      
      //clear airport lists
      
      Iterator a0 = airportList.iterator();
        iterationsA = 0;
        while(a0.hasNext()){
          iterationsA = iterationsA + 1;
          tempAirport = (Airport)a0.next();
          tempAirport.arriving.clear();
          tempAirport.departing.clear();
          tempAirport.connecting.clear();
        }//end while a0.hasNext()
        numFlights = 0;
        totalDistance = 0;
        avgDistance = 0;
        //System.out.println("iterations a0 = " + iterationsA);
        
//       //Load get passengers to airports
//       Iterator p0 = passengerList.iterator();
//       while (p0.hasNext()){
//           tempPassenger = (Passenger)p0.next();
//           (airportList.get((int)tempPassenger.start)).departing.add(tempPassenger);
//         }

       //print out hub numbers
        Iterator h0 = hubList.iterator();
            while(h0.hasNext()){
                tempHub = (Airport)h0.next();
                outputWin.addText("hub number: " + tempHub.number);
                
            }
      
       
       //create and initialize arrays of destination-hub combos 
       //firstLeg[n][m] will hold the number of passengers going from airport n to hub m as the first leg of their trip.
       firstLeg = new int[airportList.size()][hubList.size()];     
       secondLeg = new int[hubList.size()][airportList.size()];  
       for (int i = 0; i<airportList.size(); i++){
           for (int j = 0; j<hubList.size(); j++){
               firstLeg[i][j] = 0;
               secondLeg[j][i] = 0;
            }
        }
            
       //get each passenger to correct hub triangulating.
       //triangulating means finding the route with the least total distance (from - hub - to).
       //Not triangulating means using the hub nearest the departure city.
       int bestHubIndex = 0;
       
       Iterator p1 = passengerList.iterator();
           while(p1.hasNext()){
                tempPassenger = (Passenger)p1.next();
                tempTo = airportList.get((int)tempPassenger.end);
                tempFrom = airportList.get((int)tempPassenger.start);
            
                 Iterator h1 = hubList.iterator();
                 bestHubIndex = 0;
                 tempHubDistance = 2000;
                 minHubDistance = 10000;
                 while(h1.hasNext()){
                     tempHub = (Airport)h1.next();
                     firstLegDistance = Math.sqrt(Math.pow((tempHub.getX()-tempFrom.getX()),2) + Math.pow((tempHub.getY()- tempFrom.getY()),2));
                     secondLegDistance = Math.sqrt(Math.pow((tempHub.getX()-tempTo.getX()),2) + Math.pow((tempHub.getY()- tempTo.getY()),2));
                     tempHubDistance = firstLegDistance + secondLegDistance;
                     if (tempHubDistance < minHubDistance){
                         minHubDistance = tempHubDistance;
                         bestHubIndex = hubList.indexOf(tempHub);
                         if (secondLegDistance == 0){
                             secondLegQ = false;
                            }
                            else{
                                secondLegQ = true;
                            }
                     }
                 }//end while h1.hasnext()

                 firstLeg[airportList.indexOf(tempFrom)][bestHubIndex] = firstLeg[airportList.indexOf(tempFrom)][bestHubIndex] +1;
                 if (secondLegQ){
                    secondLeg[bestHubIndex][airportList.indexOf(tempTo)] = secondLeg[bestHubIndex][airportList.indexOf(tempTo)] + 1;
                }
            }//end while p1.hasnext
            // System.out.println("first legs");
           for(int i = 0; i < firstLeg.length; i++){
               for(int j = 0; j<firstLeg[0].length; j++){
                   //System.out.println("" + airportList.get(i).number + " " + hubList.get(j).number + " " + firstLeg[i][j]);
                }
            }
            System.out.println("second legs");
            for(int i = 0; i < secondLeg.length; i++){
               for(int j = 0; j<secondLeg[0].length; j++){
                   //System.out.println(""+ hubList.get(i).number + " " + airportList.get(j).number + " " + secondLeg[i][j]);
                }
            }
         //Calculate number of flights and total distance from first and second leg arrays.
         System.out.println("Triangulating ********************");
           for (int i = 0; i<airportList.size(); i++){
           for (int j = 0; j<hubList.size(); j++){
               legDistance = Math.sqrt(Math.pow((hubList.get(j).getX()-airportList.get(i).getX()),2) + Math.pow((hubList.get(j).getY()- airportList.get(i).getY()),2));
               additionalFlights = Math.ceil(firstLeg[i][j]/maxPass) + Math.ceil(secondLeg[j][i]/maxPass);
               numFlights = numFlights + additionalFlights;
               double distForLeg = additionalFlights * legDistance;
               totalDistance = totalDistance + distForLeg;
               if(additionalFlights > 0){
               System.out.println("tdl" + airportList.get(i).number + " " + hubList.get(j).number + " " + additionalFlights + " " + legDistance + " " + distForLeg);
                }
            }
            } 
            //System.out.println("nf" + numFlights + " td " + totalDistance);
            outputWin.addText(" ");
            outputWin.addText("triangulating");
            outputWin.addText(" ");
            outputWin.addText("Number of flights: " + numFlights);
            outputWin.addText("Total distance flown: " + totalDistance);
            avgDistance = totalDistance/numFlights;
            outputWin.addText("Average distance flown: " + avgDistance);
            outputWin.addText (" ");
            
            for (int j = 0; j<hubList.size(); j++){
                double totalFlights = 0;
  
                for (int i = 0; i<airportList.size(); i++){
                   //System.out.println("Flights from airport #" + i + " = " + Math.ceil(firstLeg[i][j]/maxPass));
                   //System.out.println("Flights to airport #" + i + " = " + Math.ceil(secondLeg[j][i]/maxPass));
                   totalFlights = totalFlights + Math.ceil(firstLeg[i][j]/maxPass) + Math.ceil(secondLeg[j][i]/maxPass);
                    }
                outputWin.addText("Total flights to/from hub " + hubList.get(j).number + " = " + totalFlights);
               
            } 
       
            //calculate data not triangulating (using closest hub to departure city).
             //create and initialize arrays of destination-hub combos     
       firstLeg = new int[airportList.size()][hubList.size()];     
       secondLeg = new int[hubList.size()][airportList.size()];  
       for (int i = 0; i<airportList.size(); i++){
           for (int j = 0; j<hubList.size(); j++){
               firstLeg[i][j] = 0;
               secondLeg[j][i] = 0;
            }
        }
            
        numFlights = 0;
        totalDistance = 0;
        avgDistance = 0;
        
            Iterator p2 = passengerList.iterator();
           while(p2.hasNext()){
                tempPassenger = (Passenger)p2.next();
                tempTo = airportList.get((int)tempPassenger.end);
                tempFrom = airportList.get((int)tempPassenger.start);
            
                 Iterator h2 = hubList.iterator();
                 bestHubIndex = 0;
                 tempHubDistance = 2000;
                 minHubDistance = 10000;
                 while(h2.hasNext()){
                     tempHub = (Airport)h2.next();
                     firstLegDistance = Math.sqrt(Math.pow((tempHub.getX()-tempFrom.getX()),2) + Math.pow((tempHub.getY()- tempFrom.getY()),2));
                     secondLegDistance = Math.sqrt(Math.pow((tempHub.getX()-tempTo.getX()),2) + Math.pow((tempHub.getY()- tempTo.getY()),2));
                     tempHubDistance = firstLegDistance;
                     if (tempHubDistance < minHubDistance){
                         minHubDistance = tempHubDistance;
                         bestHubIndex = hubList.indexOf(tempHub);
                         if (secondLegDistance == 0){
                             secondLegQ = false;
                            }
                            else{
                                secondLegQ = true;
                            }
                     }
                 }//end while h1.hasnext()
                 firstLeg[airportList.indexOf(tempFrom)][bestHubIndex] = firstLeg[airportList.indexOf(tempFrom)][bestHubIndex] +1;
                 if (secondLegQ){
                    secondLeg[bestHubIndex][airportList.indexOf(tempTo)] = secondLeg[bestHubIndex][airportList.indexOf(tempTo)] + 1;
                }
            }//end while p1.hasnext
           
         //Calculate number of flights and total distance from first and second leg arrays.
         System.out.println("not triangulating *********************************");
           for (int i = 0; i<airportList.size(); i++){
           for (int j = 0; j<hubList.size(); j++){
               legDistance = Math.sqrt(Math.pow((hubList.get(j).getX()-airportList.get(i).getX()),2) + Math.pow((hubList.get(j).getY()- airportList.get(i).getY()),2));
               additionalFlights = Math.ceil(firstLeg[i][j]/maxPass) + Math.ceil(secondLeg[j][i]/maxPass);
               numFlights = numFlights + additionalFlights;
               double distForLeg = additionalFlights * legDistance;
               totalDistance = totalDistance + distForLeg;
               if(additionalFlights > 0){
               System.out.println("tdl" + airportList.get(i).number + " " + hubList.get(j).number + " " + additionalFlights + " " + legDistance + " " + distForLeg);
                }
            }
            }
            outputWin.addText(" ");
            outputWin.addText("Not Triangulating ");
            outputWin.addText(" ");
            outputWin.addText("Number of flights: " + numFlights);
            outputWin.addText("Total distance flown: " + totalDistance);
            avgDistance = totalDistance/numFlights;
            outputWin.addText("Average distance flown: " + avgDistance);
            outputWin.addText (" ");
            
            for (int j = 0; j<hubList.size(); j++){
                double totalFlights = 0;
                
                for (int i = 0; i<airportList.size(); i++){
                   //System.out.println("Flights from airport #" + i + " = " + Math.ceil(firstLeg[i][j]/maxPass));
                   //System.out.println("Flights to airport #" + i + " = " + Math.ceil(secondLeg[j][i]/maxPass));
                   totalFlights = totalFlights + Math.ceil(firstLeg[i][j]/maxPass) + Math.ceil(secondLeg[j][i]/maxPass);
                    }
                outputWin.addText("Total flights to/from hub " + hubList.get(j).number + " = " + totalFlights);
                
            } 
            outputWin.addText (" ");
            outputWin.addText("***************************************");
       
       

  }//end void scheduleFlights
            


    /**
     * Act - do whatever the Dummy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       
   
       if (Greenfoot.mouseClicked(map)){
           MouseInfo mouse = Greenfoot.getMouseInfo();
               if (mouse.getButton() == 3){
               Airport tempAirport = new Airport(this, airportList.size());
               getWorld().addObject(tempAirport, mouse.getX(), mouse.getY());
               airportList.add(tempAirport);
               }
        }
        
        if (Greenfoot.mouseClicked(pButton)){
            createPassengers();
        }
        
        if (Greenfoot.mouseClicked(runButton)){
            scheduleFlights();
        }
        
         if (Greenfoot.mouseClicked(hubButton)){
            createAirports();
        }
//         if (Greenfoot.mouseClicked(scrollButton)){
//             createScrollWindow();
//         }
        if (Greenfoot.mouseClicked(capacityButton)){
            setMaxCapacity();
        }
           
    }    
}
