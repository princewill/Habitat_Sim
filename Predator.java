import java.util .ArrayList;
/**
 *
 * @author (Ohuabunwa Princewill)
 *
 */
public class Predator extends Animal 
{
    private Jungle j;
    
    /**
     * Assign the jungle, and set the coordinates as per the parameters
     */
    public Predator(int x, int y, Jungle j)
    {
        super(x, y, j);
        this.j = j;
    }
    
     /**
     * Assign the jungle, and give an initial default position (0, 0)
     */
    public Predator( Jungle j)
    {
        this(0,0,j);
    }
    
     /**
     * Finds Closest Prey
     *
     * @return     the Closest Prey
     */
    public Prey Closeprey()
    {
        ArrayList<Prey> preys;
        preys = new ArrayList<Prey>(); 
        preys = j.getPreys(); 
        
        Prey Closestprey ;        
        int Newcheck, Oldcheck = 12, checkX, checkY; //old check == impossible number
        Closestprey = preys.get(0);
        for(int i = 0; i < preys.size() ; i++)
        {
             checkX = Math.abs( getX() - preys.get(i).getX() );
             checkY = Math.abs( getY() - preys.get(i).getY() );
             Newcheck = checkX + checkY;             
             if (Newcheck < Oldcheck){ Closestprey = preys.get(i);  Oldcheck = Newcheck; }
        }
        return Closestprey;
    }
    
    /**
     * Move one step in the general direction of the prey.
     * Can only move horizontally or vertically (no diagonals).      
     */
    public void move()
    {
       int px = Closeprey().getX();
       int py = Closeprey().getY();
       int dx = getX() - px; 
       int dy = getY() - py;
       int tempx = getX(); 
       int tempy = getY();
       
       if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) tempx--;
            if (dx < 0) tempx++;
       }
       else {
            if (dy > 0) tempy--;
            if (dy < 0) tempy++;
            //if dy == 0 don't move!
       }
        //only move if new position feasible
       if ((canGoTo(tempx, tempy))) {x = tempx; y = tempy;}
       
       if ( getX() == Closeprey().getX() && getY() == Closeprey().getY() ) Closeprey().capture();       
    }

    /**
     * Tests to see if the move is valid: can't go outside the bounds,
     * and can't jump or move diagonally.
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
            if (preds.get(c).getX() == i && preds.get(c).getY() == k) return false;
        }
        boolean b = ((i >= 0) && (k >= 0) && (i < h.getSize()) && (k < h.getSize()) 
                && ((Math.abs(getX()-i) + Math.abs(getY()-k)) == 1) );
        return b;
    }
}

