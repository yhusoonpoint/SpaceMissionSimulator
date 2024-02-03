import java.util.Objects;

//CLass
public class Astronaut implements Comparable<Astronaut>
{
    //VARIABLES
    private String nationality;
    private String rank;
    private int rankNum;
    private int age;

    //Constructor to assign values to variables
    public Astronaut(String nationality, String rank, int rankNum, int age)
    {
        this.nationality = nationality;
        this.rank = rank;
        this.rankNum = rankNum;
        this.age = age;
    }

    //METHODS

    //Overriding toString to print in a different order
    @Override
    public String toString()
    {
        return rank + " (" + nationality + ", " + age +")";
    }

    //Compare to overridden to print in order of rank number nationalities and age
    @Override
    public int compareTo(Astronaut o)
    {
        //checking in order of rankNum first
        if(rankNum - o.rankNum != 0)
            return rankNum - o.rankNum;
        //then in order of nationalities
        if(nationality.compareToIgnoreCase(o.nationality) != 0)
            return nationality.compareToIgnoreCase(o.nationality);
        //finally by age
        return age - o.age;

    }

    //Overriding to return true if rank nationality and age are identical
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Astronaut)) return false;
        Astronaut astronaut = (Astronaut) o;
        return age == astronaut.age && Objects.equals(nationality, astronaut.nationality) && Objects.equals(rank, astronaut.rank);
    }

    //Overriding hashCode in case of comparison
    @Override
    public int hashCode()
    {
        return Objects.hash(nationality, rank, age);
    }

    //Returns the rank if needed
    public String getRank()
    {
        return rank;
    }

}
