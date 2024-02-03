import java.util.Arrays;
import java.util.Random;
//Main class
public class MainRocket
{
    //Main method
    public static void main(String[] args)
    {
        //Creating an array for the payLoad
        int[] randomPayload = new int[10];
        //looping through array
        for(int i = 0; i < randomPayload.length; i++)
        {
            //assigning random value between 0 - 100 to each payLoad
            randomPayload[i] = new Random().nextInt(101);
        }
        //printing array of payload
        System.out.println("PAYLOAD = " + Arrays.toString(randomPayload));
        //creating Rocket object from  Rocket Class
        Rocket spaceShip = new Rocket(randomPayload);
        //printing values of payload
        System.out.println("MAX = " + spaceShip.getMaxWeight());
        System.out.println("MEAN = " + spaceShip.getAverageWeight());
        System.out.println("MIN = " + spaceShip.getMinWeight());
        System.out.println("SUM = " + spaceShip.getLaunchWeight());

    }
}
