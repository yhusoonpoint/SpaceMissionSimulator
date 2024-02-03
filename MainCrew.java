import java.io.FileNotFoundException;
import java.util.Scanner;


public class MainCrew
{
    public static void main(String[] args)
    {
        //Variables set to use within loops
        Crew crews;

        //loop to keep asking for for file if previous value is not found
        while(true)
        {
            //Try catch method to catch file exception, used in this case to catch file not found if it attempts to open one.
            try
            {
                System.out.println("Please enter Crew file name:");
                //"C:\\Users\\UNKNOWN AB\\Downloads\\nationalities"
                //Assigning the path url to the list.
                //Scanner is used to read users input and System.in is used to capture the input
                crews = new Crew(new Scanner(System.in).nextLine());
                //break loop if no error is found
                break;
            }
            catch (FileNotFoundException e)
            {
                //print error
                System.out.println(e.getMessage());
            }
        }

        //loop is a start point, since switch statement is being used, loop will be need to break the while loop when quit is pressed
        loop: while (true)
        {
            System.out.println("Please enter: \n 1 to print crew (sorted) \n 2 to print crew (shuffled) \n 3 to assemble and print crew \n quit to quit");
            //using switch statement to check users input and print corresponding information
            //break while loop
            switch (new Scanner(System.in).nextLine())
            {
                case "1" ->
                        {
                            crews.sortCrew();
                            crews.printCrew();
                        }
                case "2" ->
                        {
                            crews.shuffleCrew();
                            crews.printCrew();
                        }
                case "3" ->
                        System.out.println(crews.assembleMissionCrew());
                case "quit" ->
                        {
                            System.out.println("Bye!");
                            break loop;
                        }
                default ->
                        System.out.println("Invalid entry! \n");
            }
        }
    }
}
