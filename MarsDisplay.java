import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class MarsDisplay extends Plot
{
    //variables to store data
    private MarsData md;
    private Map<Double, Integer> longitude,latitude;
    private final List<Double> robinsonX = new ArrayList<>();
    private final List<Double> robinsonY = new ArrayList<>();
    private final Map<Double, Color> colorMap = new TreeMap<>();

    //overriding paint method to draw points to the form
    @Override
    public void paintComponent(Graphics g)
    {
        Point newPoints;
        //calling method to initialise values
        setScaleAndAssignValues();
        if(longitude != null)
        {
            //nesting loop to loop through maps to get all coordinates
            for (Double x : longitude.keySet())
            {
                for (Double y : latitude.keySet())
                {
                    //setting color to each altitude by getting the corresponding color from the map
                    g.setColor(colorMap.get(md.getAltitude(x, y)));
                    //creating a point using the robinson method to get x and y.
                    newPoints = robinson(x, y);
                    //drawing points using fillOval and assigning it with 10 x 10
                    g.fillOval(newPoints.x, newPoints.y, 10, 10);
                }
            }
        }
    }

    //method to set the scale and to assign values to global variables.
    public void setScaleAndAssignValues()
    {
        //creating MarsData object
        md = new MarsData();
        try
        {
            md.readData2D();
            //file dialog to pick for robinson file
            FileDialog fd = new FileDialog(new JFrame(),"Choose file for Robinson data", FileDialog.LOAD);
            fd.setFile("*.csv");
            fd.setVisible(true);
            //reading the robinson file and store the x and y values
            BufferedReader fileReader = new BufferedReader(new FileReader(fd.getDirectory()+fd.getFile()));
            String eachLineInFile;
            while((eachLineInFile = fileReader.readLine()) != null){
                //adding to list
                robinsonX.add(Double.valueOf(eachLineInFile.split(",")[1]));
                robinsonY.add(Double.valueOf(eachLineInFile.split(",")[2]));
            }
            fileReader.close();
            colourMaker(new ArrayList<>(md.getAltitude().keySet()));
            //creating doubleVariables for getter to speed up time in loading
            double minX = md.getMinLongitude();
            double maxX = md.getMaxLongitude();
            double minY = md.getMinLatitude();
            double maxY = md.getMaxLatitude();
            //assigning the map with values from md
            longitude = md.getLongitude();
            latitude = md.getLatitude();
            //setting height scale for form
            this.height = (int) (this.width * (maxY - minY) / (maxX - minX));
            this.setScaleX(minX, maxX);
            this.setScaleY(minY, maxY);
        }
        catch (IOException e)
        {
            System.out.println("FILE NOT FOUND!!!");
        }

    }
    
    //method to create and add color to a map
    public void colourMaker(List<Double> value)
    {
        //looping through the sorted altitude
        for(double altitude : value)
        {
            //getting the index of each altitude
            double index = value.indexOf(altitude) + 1;
            //list size
            double size = value.size();
            //getting ratio
            double z = ( index / size );
            //getting percentage of a particular numbers for the colors
            double r = Math.min(255, 99 + z * 60);
            double g = Math.min(255, 57 + z * 89);
            double b = Math.min(255, 16 + z * 103);
            //creating and adding each color for each altitude into a map.
            colorMap.put(altitude,new Color((int)r,(int)g,(int)b));
        }
    }

    //robinson method to create Robinson Projection
    public Point robinson(double longitude, double latitude)
    {
        //getting sign of the values before converting to abs else all value will be positive and 1/4 of the map would only be drawn
        double longitudeSign = Integer.signum((int)longitude);
        double latitudeSign = -Integer.signum((int)latitude);
        //getting the abs value to get rid of negatives
        longitude = Math.abs(longitude);
        latitude = Math.abs(latitude);
        //variable to calculate the min value and max value that will be placed for indexing
        double low =  Math.max(0 , Math.floor((latitude - 0.1) / 5 ) * 5); //getting the max value
        double high = low + 5;
        double coordinateIndex = (latitude - low) / 5;
        //calculating the index for the interpolation
        low = low/5;
        high = high/5;
        //interpolating the data from the list to get the X and Y coordinate
        double lengthOfParallel = ((robinsonX.get((int)high) - robinsonX.get((int) low))* coordinateIndex)+robinsonX.get((int) low);
        double distanceOfParallel = ((robinsonY.get((int)high)-robinsonY.get((int) low))* coordinateIndex)+robinsonY.get((int) low);
        //creating and return point from the robinson formula
        return new Point
                (
                        // 3.3895000 is th e radius of mars, Robinson formula requires it to be in radians hence the conversion to radian
                        (int)(lengthOfParallel * Math.toRadians(longitude)  * longitudeSign * (((getWidth() - 100) /  3.3895000) / 2 )) + (getWidth()/2 - 5),
                        (int)(distanceOfParallel * latitudeSign * (((getWidth() + 100 ) / 3.3895000) / 2 )) + getHeight()/2
                );
    }
}
