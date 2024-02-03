import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//Main class
public class MainSolar
{
    //Main method handling and exception
    public static void main(String[] args) throws InterruptedException
    {
        //Creating a JFrame object
        JFrame sc= new JFrame();
        //setting size to frame
        sc.setSize(1800,800);
        //creating a SolarDisplay object
        SolarDisplay sd = new SolarDisplay(sc.getWidth()/2 , sc.getHeight()/2);
        //Event to check when screen is resized so it can change the position of the sun to the centre
        sc.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //using setter to change x and y of the sun
                sd.setPlanetArray(sc.getWidth()/2 , sc.getHeight()/2);
            }
        });
        //adding the SolarDisplay to the form because it's inheriting from JComponent
        sc.add(sd);
        //Setting the form visible to true to display
        sc.setVisible(true);
        //making the program close when exit is clicked
        sc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //looping infinity for animation
        while (true)
        {
            //adding revalidate to keep focus on form
            sd.revalidate();
            //checking status of timer
            if (sd.isPause())
            {
                //moving the planet and repainting based on the time .
                sd.moveBall();
                sc.repaint();
                //getting the time value from the class
                Thread.sleep(sd.getTimerTime());
            }
        }
    }
}
