
/**
 * Pheromone is what the ant emits.
 * 
 * @author (Princewill Ohuabunwa)
 * 
 */
public class Pheromone extends PartsOfGarden
{    
    private int strength;
    private boolean present;
    private Garden g;
    /**
     * Constructor for objects of class Pheromone.
     */
    public Pheromone(int x, int y, Garden g)
    {
        super(x, y, g);
        present = true;
        strength = 5;
        this.g = g;
    }
    
    /**
     * Reduces the intensity of a Pheromone.
     * 
     */
    public void reduceSmell()
    {
        if (strength > 0) strength --;
    }
    
    /**
     *States whether the peromone is present.
     */
    public boolean isPresent()
    {
       return present;
    }

    /**
     * Mutator that sets the present variable to false.
     */
    public void captureSmell()
    {
        present = false;
    }
    
    /**
     * Returns the intensity of a Pheromone.
     * 
     * @return the strengh of the pheromone.
     */
    public int getStrength()
    {
        return strength;
    }
    
}
