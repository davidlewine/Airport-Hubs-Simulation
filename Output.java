

import java.awt.*;
import javax.swing.*;


public class Output  extends JFrame {


    JTextArea resultArea = new JTextArea(6, 20);
    


    String text;


    /**
     * Constructor for objects of class Output
     */
    public Output()
    {
         //... Set textarea's initial text, scrolling, and border.
        resultArea.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        JScrollPane scrollingArea = new JScrollPane(resultArea);
        
        //... Get the content pane, set layout, add to center
        JPanel content = new JPanel();
        content.setPreferredSize(new Dimension(400, 400));

        content.setLayout(new BorderLayout());
        content.add(scrollingArea, BorderLayout.CENTER);
        
        //... Set window characteristics.
        this.setContentPane(content);
        this.setTitle("Airport Hubs Output");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        addText("Instructions:\n\n"+
                  "1) Click on Run button at bottom of window.\n" +
                  "     If program freezes, click Run button again.\n" +
                  "2) Add terminals.\n" +
                  "     Click terminal icon button to add preset terminals.\n" +
                  "     Right click on map to add custom airports.\n"+
                  "     Airports can be dragged to new locations.\n"+
                  "     To remove terminals you must remove them all by clicking the reset button at bottom of window.\n"+
                  "3) Select at least one hub.\n"+
                  "     Left Click on a terminal to toggle it to a hub.\n" +
                  "     A hub is indicated by a red square.\n" + 
                  "4) Set number of passengers traveling.\n" +
                  "     Click on passenger icon button.\n"+
                  "     Departure and destination cities are set randomly.\n"+
                  "     Departure and destination cities are changed ONLY when the number of passengers is reset by clicking passenger icon button.\n"+
                  "5) Set the passenger capacity of the plane.\n"+
                  "     Click on the seat icon button.\n" +
                  "6) Calculate flights.\n"+
                  "     Click on plane icon button.\n"+
                  "********************************\n"
                  );

    }

    public void addText(String newText){
        text = newText;
        resultArea.append(text + "\n");
    }
        
        
}
