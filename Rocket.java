import java.util.Arrays;
import java.util.stream.IntStream;

public class Rocket
{
    //Creating private array int
    private int[] payload;

    //create a constructor and assigning
    public Rocket(int[] payload)
    {
        this.payload = payload;
    }

    //Method to return sum of
    public int getLaunchWeight()
    {
        //Using IntStream to sum all alternately, we could loop though payload and add them all
        return IntStream.of(payload).sum();
    }

    //method to get average weight
    public double getAverageWeight()
    {
        //dividing sum from length to get average
        return getLaunchWeight()/payload.length;
    }

    //method to return max weight
    public int getMaxWeight()
    {
        //returns the max value in the list
        //another way could be sorting the array and getting the last value.
        //another way could be looping through and comparing values
        return Arrays.stream(payload).max().getAsInt();
    }

    //method to return min weight
    public int getMinWeight(){
        //returns the min value in the list
        //another way could be sorting the array and getting the last value.
        //another way could be looping through and comparing values
        return Arrays.stream(payload).min().getAsInt();
    }

    //printing the countdown
    public void printCountdown(int countDownValue)
    {
        //checking if the value is less than so it doesn't print
        if (countDownValue < 1)
        {
            System.out.println("Invalid time.");
        }
        else
            {
            //print from the given number until 1
            for (int i = countDownValue; i > 0; i--)
            {
                System.out.println(i);
            }
            System.out.println("Lift off!");
        }
    }

}
