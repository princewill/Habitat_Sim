
/**
 * Write a description of class Parts here.
 * 
 * @author (Princewill Ohuabunwa)
 * 
 */
public class PartsOfGarden
{    
    private int x;
    private int y;
    public Habitat h;

    /**
     * Constructor for objects of class Parts
     */
    public PartsOfGarden(int x, int y, Habitat h)
    {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    /**
     * Constructor for objects of class Parts
     */
    public PartsOfGarden(Habitat h)
    {        
        this.x = 0;
        this.y = 0;
        this.h = h;
    }
    
     /**
     * Returns the value of the x-coordinate   
     * @return     x
     */
    public int getX()
    {        
        return x;
    }
    
    /**
     * Returns the value of the y-coordinate   
     * @return    y
     */
    public int getY()
    {        
        return y;
    }
    
}
