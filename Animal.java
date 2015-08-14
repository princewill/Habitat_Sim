import java.util .ArrayList;
/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Animal
{
    protected int x;
    protected int y;
    public Habitat h;

    /**
     * Assign the habitat, and set the coordinates as per the parameters
     */
    public Animal(int x, int y, Habitat h)
    {
        this.x = x;
        this.y = y;
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
     
    /**
     * Move one step in the right direction.
     */
    public abstract void move();
    
    /**
     * Tests to see if the move is valid: can't go outside the bounds,
     * and can't jump or move diagonally.
     * 
     * @return true of move ok, false if illegal
     */
    protected abstract boolean canGoTo(int i, int k) ;
    
}