
/**
 * Write a description of class Habitat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Habitat
{   
    
    /**
     * Accessor to the size of the garden
     */
    public abstract int getSize();

    /**
     * Print a string representation of the garden.
     */
    public void print()
    {       
        System.out.println(toString());
    }
    
     /**
     * Returns a string representation of the garden (as per the illustration in the assignment)
     */ 
    public java.lang.String toString()
    {
        String fs = "";
        return fs;
    }
    
    /**
     * Move the occupants of the habitat.
     */
     public abstract void moveInhabitants();
}
