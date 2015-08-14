import java.util .ArrayList;
import java.lang.Math;

public class Breadcrumb extends PartsOfGarden
{
    private boolean alive;
    private Garden g;
    
    /**
     * Assign the garden, and set the coordinates as per the parameters
     */
    public Breadcrumb(int x, int y, Garden g)
    {
        super(x, y, g);
        alive = true;
        this.g = g;
    }
    
    /**
     * Assign the garden, and give an initial default position (0, 0)
     */
    public Breadcrumb( Garden g)
    {
        super(g);
        alive = true;
        this.g = g;
    }
    
    /**
     * Mutator that sets the alive variable to false.
     */
    public void capture()
    {
       alive= false;
    }
    
    /**
     * whether the crumbs is alive
     */
    public boolean isPresent()
    {
       return alive;
    }
}
