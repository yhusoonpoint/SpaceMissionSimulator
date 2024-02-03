import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

//main MarsData Class
public class MarsData
{
    //Variables
    private Double[][] arrayOfMars; //Array to hold altitude
    //List to hold the lines from the file
    private final List<String> fileResult = new ArrayList<>();
    //Variables to hold min and max values to be used for accessing information
    private Double minAltitude, maxAltitude, minLongitude, maxLongitude, minLatitude, maxLatitude;
    //Variable to count the Row need for arrayOfMars
    private int arrayRowLength;
    //Map created as a solution to store negative indexed into the array
    private final TreeMap<Double,Integer> longitude = new TreeMap<>();
    private final TreeMap<Double,Integer> latitude = new TreeMap<>();
    private final TreeMap<Double,Integer> altitude = new TreeMap<>();

    //Method with an exception handler to put data from file into appropriate data structure
    public void readData2D() throws IOException
    {
        //Calling a method to read the data from file into an array
        readCSV();
        //initialising i  to use for indexing
        int i = 0;
        //Variables to store current and previous value
        Double previousValue = 0.0;
        Double currentValue;
        //Assigning size to arrayOfMars using the Values from list size and row length
        arrayOfMars= new Double[arrayRowLength][fileResult.size()/ arrayRowLength];
        //looping through each line in the list
        for(String eachLine : fileResult)
        {
            //assigning current value to the longitude
            currentValue = Double.parseDouble(eachLine.split(",")[0]);
            //checking if the current and previous value are not same so it can reset the index to 0, this is used to index the coordinate to the array from the map
            if (!currentValue.equals(previousValue))
                i = 0;
            //adding each values to their appropriate map.
            longitude.put(Double.valueOf(eachLine.split(",")[0]), longitude.size()-1);
            altitude.put(Double.valueOf(eachLine.split(",")[2]), altitude.size());
            //if the value of the longitude is changed then the index resets so the latitude can start from 0
            latitude.put(Double.valueOf(eachLine.split(",")[1]), i);
            //assigning the altitude to the array by using the value of the index used in the map.
            //for example -179 will have an index of 0, -90 will have and index of 0 and so on
            arrayOfMars[longitude.size()-1][i] =  Double.parseDouble(eachLine.split(",")[2]);
            previousValue = currentValue;
            //incrementing i
            i++;
        }
        //if statement was used in readCSV to check for the min and max values but after  checking how long it ran, it was longer and found out this was quicker through testing
        minLongitude = Collections.min(longitude.keySet());
        maxLongitude = Collections.max(longitude.keySet());
        minLatitude = Collections.min(latitude.keySet());
        maxLatitude = Collections.max(latitude.keySet());
        minAltitude = Collections.min(altitude.keySet());
        maxAltitude = Collections.max(altitude.keySet());
    }

    //method to read data from file with exception handler
    public void readCSV() throws IOException
    {
        //creating a file dialog so it's easy to pick for mars data
        FileDialog fd = new FileDialog(new JFrame(),"Choose file for Mars data", FileDialog.LOAD);
        fd.setFile("*.csv");
        fd.setVisible(true);
        //Reading the file from the path provided
        //file can be changed to small medium or large
        BufferedReader fileReader = new BufferedReader(new FileReader(fd.getDirectory()+fd.getFile()));
        //this will be used to count the Row size
        double previousLength = 0.0;
        //store each line from file
        String eachLineInFile;
        //looping through the file to get all lines, if no new line found loop ends.
        while ((eachLineInFile = fileReader.readLine()) != null) //gets each line and requests next line
        {
            //add each line to the list
            fileResult.add(eachLineInFile);
            //used to increment Row length
            if(Double.parseDouble(eachLineInFile.split(",")[0] )!= previousLength)
            {
                arrayRowLength += 1;
                previousLength = Double.parseDouble(eachLineInFile.split(",")[0]);
            }
        }
        //closes the file
        fileReader.close();
    }

    //Method that returns altitude as double.
    public double getAltitude(Double longitude, Double latitude)
    {
        //when a value is received, floorKey is used to get the nearest key from the map and the value of the key is return and  inserted into arrayOfMars to get the altitude because the map is used to index the array.
        return arrayOfMars[this.longitude.get(this.longitude.floorKey(longitude))][this.latitude.get(this.latitude.floorKey(latitude))];
    }

    //METHODS
    //getters to get the values from a different class because they are set to private
    public Double getMinAltitude()
    {
        return minAltitude;
    }

    public Double getMaxAltitude()
    {
        return maxAltitude;
    }

    public Double getMinLongitude()
    {
        return minLongitude;
    }

    public Double getMaxLongitude()
    {
        return maxLongitude;
    }

    public Double getMinLatitude()
    {
        return minLatitude;
    }

    public Double getMaxLatitude()
    {
        return maxLatitude;
    }

    public TreeMap<Double, Integer> getLongitude()
    {
        return longitude;
    }

    public TreeMap<Double, Integer> getLatitude()
    {
        return latitude;
    }

    public TreeMap<Double, Integer> getAltitude() {
        return altitude;
    }
}
