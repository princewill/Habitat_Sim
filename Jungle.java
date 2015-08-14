import java.util .ArrayList;
/**
 * 
 * @author (Princewill Ohuabunwa)
 *
 */
public class Jungle extends Habitat
{
    private int size;
    private static int default_size = 5;
    
    private ArrayList<Prey> preys;
    private ArrayList<Predator> preds;
    public Prey prey1;
    public Prey prey2;
    public Predator pred1;
    public Predator pred2;
    
    /**
     * Default jungle with the size 5.
     */
    public Jungle()
    {
        this(default_size);
    }
    
    /**
     * Creates a jungle with the size specified by the parameter.
     */
    public Jungle (int size)
    {
        this.size = size;
        addAnimals();
    }
        
    /**
     * Accessor to the size of the Jungle
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Add predator method
     */
    public void addPredator(Predator pred)
    {
         preds.add(pred);         
    }
    
    /**
     * Add prey method
     */
    public void addPrey(Prey prey)
    {
        preys.add(prey);
    }
    
    /**
     * Accessor to the predator
     */
    public java.util.ArrayList<Predator>  getPredators()
    {
        return preds;
    }
    
    /**
     * Accessor to the prey
     */
    public java.util.ArrayList<Prey> getPreys()
    {
        return preys;
    }

    /**
     * Move the prey, then the predator.
     */
    public void moveInhabitants()
    {

        for(int i = 0; i < preys.size(); i++)
        {
            if(preys.get(i).isAlive()== false)
            {
                preys.remove(preys.get(i));
                i--;           
            }
        }
        
        for(int i = 0; i < preys.size(); i++)
        {
             preys.get(i).move();                       
        }
        
        if (preys.size() > 0){
            
            for(int i = 0; i < preds.size(); i++)
            {
                preds.get(i).move();
            }
        }
    }    
    
    /**
     * Test to see if there's a predator at the supplied coordinate
     */
    public boolean hasPredatorAt(int x,int y)
    {         
        Predator pred;      
        
        for (int i = 0; i < preds.size(); i++)
        {
            pred = preds.get(i);
            if( pred.getX() == x && pred.getY() == y) return true;
            
        }
        return false;
    }
    
    /**
     * Test to see if there's a prey at the supplied coordinate.
     */
    public boolean hasPreyAt(int x,int y)
    {        
        Prey prey;
        for (int i = 0; i < preys.size(); i++)
        {
            prey = preys.get(i);
            if((prey.getX() == x) && (prey.getY() == y)) return true;
            
        }
        return false; 
    }    
    
    /**
     * Returns a string representation of the jungle (as per the illustration in the assignment)
     * - put a "o" at the location of a prey 
     * - put a "X" at the location of a predator
     * - (if a prey and a predator are at the same coordinate, only print the predator)
     * - otherwise, just print a dot "."
     */
    public java.lang.String toString()
    {
        String a="";
        for (int i = 0; i < getSize(); i++)
        {
            a += "        ";            
            for (int k = 0; k < getSize(); k++)
            {
                if (hasPredatorAt(k,i))   a += " X";
                else if (hasPreyAt(k,i))  a += " o";
                else                      a += " .";
             }                
            a += "\n";       
            }
        return a;             
    } 
    
    /**
     * Adds the new animals to the Jungle.
     */
     private void addAnimals()
     {
        preds = new ArrayList<Predator>();
        preys = new ArrayList<Prey>();

        pred1 = new Predator(1,3,this);
        pred2 = new Predator(3,2,this);
        prey1 = new Prey(2,0,this);
        prey2 = new Prey(2,4,this);
        
        this.addPredator(pred1);
        this.addPredator(pred2);
        this.addPrey(prey1);
        this.addPrey(prey2);
    }
}
