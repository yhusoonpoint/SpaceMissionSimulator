import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//SolarDisplay is inheriting from JComponent to override paintComponent
public class SolarDisplay extends JComponent
{
    private double year;
    //Variables that will be used for each ball
    private double x, y, r, speed, distance;
    private Color color;
    //Variable to start or pause animation
    private boolean pause = true;
    //variable to control animation speed
    private int timerTime = 100;
    //Array to store each planet
    SolarDisplay[] planetArray = new SolarDisplay[5];
    //incrementing the angle for every loop.
    private double angleIncrement;

    //SolarDisplay class
    public SolarDisplay(int height, int width)
    {
        //setting focus on the form so keyboard key can be detected
        this.setFocusable(true);
        //MouseListener to detect when the left button is pressed
        addMouseListener(
                new MouseAdapter()
                {
                    //overriding mouse event
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        super.mousePressed(e);
                        //if left mouse was pressed it checks the status of the timer and turn it either on or off
                        if (e.getButton() == MouseEvent.BUTTON1)
                            setPause(!isPause());
                    }
                });
        //KeyboardListener to detect when + or - is pressed
        addKeyListener(
                new KeyAdapter()
                {
                    //overriding the key event
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        //Checking what key was pressed
                        if (e.getKeyChar() == 43)//+
                        {
                            //speeding up animation by reduce the sleep value
                            //setting min value to 0
                            if (timerTime == 0)
                                timerTime = 2;
                            timerTime -= 2;
                        }
                        else if (e.getKeyChar() == 45)//-
                        {
                            //slowing down animation by increase the speed value
                            timerTime += 2;
                        }
                    }
                });
        //Creating each planets and assigning them to the array
        planetArray[0] = new SolarDisplay(height - 25,width - 25,50,0,0,0, Color.yellow);
        planetArray[1] = new SolarDisplay(x,y,20,1.607,0.2409,0.387,Color.orange);
        planetArray[2] = new SolarDisplay(x,y,20,1.174,0.616,0.723,Color.yellow);
        planetArray[3] = new SolarDisplay(x,y,20, 1.0,1.0,1.0,Color.blue);
        planetArray[4] = new SolarDisplay(x,y,20,0.802,1.9,1.524,Color.red);

    }

    //changing the position of planet when the screen is resized
    public void setPlanetArray(int x, int y)
    {
        this.planetArray[0].x = x-25;
        this.planetArray[0].y = y-25;
    }

    //Constructor to create planetArray of type SolarDisplay
    public SolarDisplay(double x, double y, double r, double speed, double year, double distance, Color color)
    {
        //assigning global variable with parameters
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.year = year;
        this.r = r;
        this.distance = distance;
        this.color = color;
    }

    //returns if timer is true or false
    public boolean isPause()
    {
        return pause;
    }

    //set value of timer changes the variable of pause to true or false
    public void setPause(boolean pause)
    {
        this.pause = pause;
    }

    //returns value fpr sleep
    public int getTimerTime()
    {
        return timerTime;
    }

    //method to move ball around
    public void moveBall()
    {
        angleIncrement+=0.01;
        //for loop starting from 1 to ignore the sun planet but loop other planets
        for(int i =1; i<5; i++)
        {
            // calculating the angle based on speed (1/T) and multiplying it by 1 revolution (2 * PI) 2PIR/T
            double angle = 2 * Math.PI * ((angleIncrement % ( planetArray[i].year)) / ( planetArray[i].year));
            //creating coordinates for each planets. adding the coordinate of sun + 15 to initialise the middle position,
            //multiplying distance by 150 to assign a radius which is the default space multiplied by the distance of each and finally multiplying by the angle corresponding
            planetArray[i].x = planetArray[0].x + 15 + (planetArray[i].distance * 150) * Math.cos(angle);
            planetArray[i].y = planetArray[0].y + 15 + (planetArray[i].distance * 150)* Math.sin(angle);
        }
    }

    //overriding paintComponent to print planet
    @Override
    public void paintComponent(Graphics g)
    {
        //looping through the planetArray
        for(SolarDisplay x : planetArray)
        {
            //setting the colour to the color assigned
            g.setColor(x.color);
            //drawing each planet based on there properties
            g.fillOval((int)x.x,(int)x.y,(int)x.r,(int)x.r);
        }
    }
}
