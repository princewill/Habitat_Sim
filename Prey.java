import java.util .ArrayList;
import java.lang.Math;
/**
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public class Prey extends Animal
{    
    private boolean alive;
    private Jungle j;
    
    /**
     * Assign the jungle, and set the coordinates as per the parameters
     */
    public Prey(int x, int y, Jungle j)
    {
        super(x, y, j);
        this.j = j;
        alive = true;
    }
    
    /**
     * Assign the jungle, and give an initial default position (0, 0)
     */
    public Prey( Jungle j)
    {
        this(0,0,j);
    }
    
    /**
     * Mutator that sets the alive variable to false.
     */
    public void capture()
    {
        alive = false;
    }
    
    /**
     * whether the prey is alive
     */
    public boolean isAlive()
    {
       return alive;
    }
    
    /**
     * Finds Closest Predator
     *
     * @return     the Closest Predator
     */
    public Predator Closepred()
    {
        ArrayList<Predator> preds;
        preds = new ArrayList<Predator>();    
        preds = j.getPredators(); 
        
        Predator Closestpred ;        
        int Newcheck, Oldcheck = 12, checkX, checkY; //old check == impossible number
        Closestpred = preds.get(0);
        for(int i = 0; i < preds.size() ; i++)
        {
             checkX = Math.abs( getX() - preds.get(i).getX() );
             checkY = Math.abs( getY() - preds.get(i).getY() );
             Newcheck = checkX + checkY;
             
             if (Newcheck < Oldcheck){  Closestpred = preds.get(i); Oldcheck = Newcheck; }
        }
        return Closestpred;
    }
    
    /**
     * Move one step away from the predator.
     * Remember that you CAN move diagonally.        
     */
    public void move()
    {   
        Predator pred = Closepred();
         
        int px = pred.getX();
        int py = pred.getY();
        int dx = x - px; 
        int dy = y - py;
        int tempx = x; 
        int tempy = y;
        
        if ((dx > 0) && (x + 1 < j.getSize())) tempx++; 
        if ((dx < 0) && (x - 1 >= 0)) tempx--;
        if ((dx == 0) && (x + 1 < j.getSize())) tempx++; 
        else if ((dx == 0) && (x - 1 >= 0)) tempx--;
        
        if ((dy > 0) && (y + 1 <j.getSize())) tempy++; 
        if ((dy < 0) && (y - 1 >= 0))tempy--;
        if ((dy == 0) && (y + 1 < j.getSize())) tempy++;         
        else if ((dy == 0) && (y - 1 >= 0))tempy--;        
        
        //only move if new position feasible
        if ((canGoTo(tempx, tempy))) {x = tempx; y = tempy;}    
    }
    
     /**
     * Tests to see if the move is valid: can't go outside the bounds.
     * 
     * @return true of move ok, false if illegal
     */
    protected boolean canGoTo(int i, int k) 
    {
        ArrayList<Predator> preds;
        preds = new ArrayList<Predator>(); 
        preds = j.getPredators(); 

        ArrayList<Prey> preys;
        preys = new ArrayList<Prey>(); 
        preys = j.getPreys();         
        for (int c = 0; c < preys.size(); c++)
        {
            if (preys.get(c).getX() == i && preys.get(c).getY() == k) return false;
        }
        for (int c = 0; c < preys.size(); c++)
        {
            if (preds.get(c).getX() == i && preds.get(c).getY() == k) return false;
        }
        
        boolean b = ((i >= 0) && (k >= 0) && (i < h.getSize()) && (k < h.getSize())) ;  
                //&& ((Math.abs(x-i) + Math.abs(y-h)) == 1) );
        return b;
    }
    
}
