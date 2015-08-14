import java.util.Scanner;
/**
 *The simulator runs.
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public class Simulator
{
    private Habitat habitat;
    
    /**
     *Initializes the jungle (default size) and Garden (default size)
     *And populates it with its inabitants
     */
    public Simulator(Habitat h)
    {               
       habitat = h;
        habitat.print();
    }
    
    /**
     * A given Habitat (Garden, Jungle or City) is instantiated
     * and passed as a parameter to a new Simulator
     * 
     * @param Please input the name of the Habitat to be created
     */
    public static void main(String[] args)
    {
        if(args[0].equals("Jungle"))
        {
            Jungle jungle = new Jungle();
            Simulator simulator = new Simulator(jungle);
            simulator.run();
        }
        else if (args[0].equals("Garden")) 
        {
            Garden garden = new Garden();
            Simulator simulator = new Simulator(garden);
            simulator.run();
        }else if(args[0].equals("City"))
        {
            City city = new City();
            Simulator simulator = new Simulator(city);
            simulator.run();
        }        
    }

    /**
     * use the class Scanner to take the user’s input.
     */
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Input number of moves to be made in the Habitat; '0' to quit");
        num = sc.nextInt();
        while( num != 0)
        {
            multimove(num);
            habitat.print();
            num = sc.nextInt();
        }
    }
        
    /**
     *Calls the move() method k times.
     *param k.
     *
     */
    public void multimove(int k)
    {
        for (int i =0; i < k; i++)
        {
            move();
        }
    }
    
    /**
     * Each simulator step is a call to the habitat to move its population.
     * It then prints the resulting state of the jungle.   
     */
    public void move()
    {
        habitat.moveInhabitants();        
    }
}
