import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//Cre class
public class Crew
{
    //variables
    private static final String[] RANKS =
            {
                    "Commander","Pilot","Payload Commander","Mission Specialist","Spaceflight Participant"
            }; //contains fixed string array
    //string array to store all nationalities that will be received from files
    private String[] nationalities;
    //list contains Astronaut objects that will be made from each rank and nationalities
    private List<Astronaut> crew = new ArrayList<>();

    //Constructor that receives path and handles exceptions using throws
    public Crew(String nationalitiesSrc) throws FileNotFoundException
    {
        //Reading the file from the path provided
        Scanner fileReader = new Scanner(new File(nationalitiesSrc));
        //initialising i = 0, usage will be mentioned along.
        int i =0;
        //looping through the file to get all lines, if no new line found loop ends.
        while (fileReader.hasNextLine())
        {
            //gets each line amd requests next line
            fileReader.nextLine();
            //adds 1 to itself for each loop to get the total  linea
            i++;
        }
        //closes the file
        fileReader.close();

        //setting size to the string array from the the total loop which is the total lime in file
        nationalities = new String[i];
        //Read the file again
        fileReader = new Scanner(new File(nationalitiesSrc));
        //setting i = 0 to use as index for the array while in a loop
        i=0;

        //looping through the file again
        while (fileReader.hasNextLine())
        {
            //adding each line to the array
            nationalities[i++] = fileReader.nextLine();
        }
        //closing file
        fileReader.close();

        //looping through the array
        for (String eachNationalities : nationalities)
        {
            //i = 0 to reset index
            i = 0;
            //looping through each rank
            for(String eachRANK :RANKS)
            {
                //for every nationalities, add each Rank to it so basically 1 nationalities has 5 ranks
                //.nextInt is used to set random age, 18 being min and 60 being max
                //creating Astronaut  object
                //adding each object to the crew list
                crew.add( new Astronaut(eachNationalities, eachRANK, i++, new Random().nextInt(43) + 18));
            }
        }
    }

    //METHODS
    public void printCrew()
    {
        //loop through crew list amd print values
        for(Astronaut eachAstronaut : crew )
        {
            System.out.println(eachAstronaut);
        }
    }
    public void sortCrew()
    {
        //Sort the list according to the toCompare method overridden
        Collections.sort(crew);
    }
    public void shuffleCrew()
    {
        //Shuffles the list by getting each line and setting it to random lines
        for(int i = crew.size()-1; i>0; i--)
        {
            int r = (int)(Math.random() * i);
            Astronaut eachAstronaut = crew.get(i);
            crew.set(i, crew.get(r));
            crew.set(r, eachAstronaut);
        }
    }
    //Returns Map
    public Map<String,Astronaut> assembleMissionCrew()
    {
        //creating a temp map to return
        Map<String,Astronaut> addToMap = new TreeMap<>();

        //looping until the map has all the rank inside
        while (addToMap.size() < RANKS.length)
        {
            //getting random Astronaut from the crew list
            Astronaut eachAstronaut = crew.get(new Random().nextInt(crew.size()));
            //checking in the Key already exist which is the rank in String
            if(!addToMap.containsKey(eachAstronaut.getRank()))
            {
                //adding to the map if the key does not exist
                addToMap.put(eachAstronaut.getRank(), eachAstronaut);
            }
        }
        //returning the map which will contain 5 items.
        return addToMap;
    }
}
