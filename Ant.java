import java.util .ArrayList;
/**
 *The Ant is an animal that has coordinates in a garden. 
 *It can move one cell to the left, right, up or down.
 * 
 * @author () 
 * @version ()
 */
public class Ant extends Animal
{    
    private Garden g;
    /**
     * Assign the garden, and set the coordinates as per the parameters
     */
    public Ant(int x, int y, Garden g)
    {
        super(x, y, g);
        this.g = g;
    }
    
     /**
     * Assign the garden, and give an initial default position (0, 0)
     */
    public Ant( Garden g)
    {
        this(0,0,g);
    }
   
    
     /**
     * Finds Closest Breadcrumb
     *
     * @return     the Closest Breadcrumb
     */
    public Breadcrumb Closecrumb()
    {
        ArrayList<Breadcrumb> crumbs;
        crumbs = new ArrayList<Breadcrumb>(); 
        crumbs = g.getBreadcrumbs(); 
        
        Breadcrumb Closestcrumb ;        
        int Newcheck, Oldcheck = 12, checkX, checkY; //old check == impossible number
        
        Closestcrumb = crumbs.get(0);
        for(int i = 0; i < crumbs.size() ; i++)
        {
             checkX = Math.abs( getX() - crumbs.get(i).getX() );
             checkY = Math.abs( getY() - crumbs.get(i).getY() );
             Newcheck = checkX + checkY;             
             if (Newcheck < Oldcheck){ Closestcrumb = crumbs.get(i);  Oldcheck = Newcheck;}
        }
        return Closestcrumb;
    }
    
     /**
     * Finds Strongest smelling pheromone.
     *
     * @return Strongest smelling pheromone.
     */
    public Pheromone StrongestSmell()
    {
        ArrayList<Pheromone> pheromones;
        pheromones = new ArrayList<Pheromone>();  
        pheromones = g.getPheromones(); 
        
        Pheromone pheromone, StrongestSmell ;        
        int distanceX, distanceY, distance, Newcheck, Oldcheck = 15; //old check == impossible number
        
        StrongestSmell = pheromones.get(0);        
        for(int i = 0; i < pheromones.size() ; i++)
        {
             pheromone = pheromones.get(i);
             distanceX = Math.abs( getX() - pheromone.getX() );
             distanceY = Math.abs( getY() - pheromone.getY() );
             distance = distanceX + distanceY;
             Newcheck = pheromone.getStrength() - distance;
             
             if (Newcheck > Oldcheck){
                 StrongestSmell = pheromone;
                 Oldcheck = Newcheck;
             }
        }
        return StrongestSmell;
    }    
    
     
    /**
     * Move one step in the general direction of the crumb.
     * Can only move horizontally or vertically (no diagonals).      
     */
    public void move()
    {
       ArrayList<Pheromone> pheromones;
       pheromones = new ArrayList<Pheromone>();
       pheromones = g.getPheromones();
       
       if (pheromones.size() > 0)  moveToPheromone();
       else                        moveToCrumb(); 
    }
    
    /**
     * Move one step in the general direction of the crumb.
     * Can only move horizontally or vertically (no diagonals).      
     */
    public void moveToCrumb()
    {
       ArrayList<Pheromone> pheromones;
       pheromones = new ArrayList<Pheromone>();
       pheromones = g.getPheromones();
       Breadcrumb crumb = Closecrumb();
  
       int cx = crumb.getX();
       int cy = crumb.getY();
       int dx = getX() - cx; 
       int dy = getY() - cy;
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
       if ((canGoTo(tempx, tempy))) 
       {
           x = tempx; 
           y = tempy;
           //Take crumb, emit pheromone and Go home
           if ( getX() == crumb.getX() && getY() == crumb.getY() ) 
           {
               crumb.capture(); 
               emit();
               GoHome();
           }
           if (pheromones.size() > 0){
               //Return home when you find location of the smell
               if ( getX() == StrongestSmell().getX() && getY() == StrongestSmell().getY() )  GoHome();
           }
        }
    }
    
    /**
     * Move one step in the general direction of the Pheromone.
     * Can only move horizontally or vertically (no diagonals).      
     */
    public void moveToPheromone()
    {
       Pheromone pheromone = StrongestSmell();
  
       int px = pheromone.getX();
       int py = pheromone.getY();
       int dx = x - px; 
       int dy = y - py;
       int tempx = x; 
       int tempy = y;
   
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
       if ((canGoTo(tempx, tempy))) 
       {
           x = tempx; 
           y = tempy;
           //Return home when you find location of the smell and capture pheromone 
           if ( getX() == pheromone.getX() && getY() == pheromone.getY() )  {GoHome();  StrongestSmell().captureSmell();}
        }
    }

    /**
     * makes a new pheromonen .   
     */
    public void emit()
    {
       Pheromone pheromone;
       
       pheromone = new Pheromone(x,y, g);
       g.addPheromone(pheromone); 
    }
    
    
    /**
     * makes the Ant go back home.
     */
    public void GoHome()
    {
        x = 0; 
        y = 0;
    }
    
      /**
     * Tests to see if the move is valid: can't go outside the bounds,
     * and can't jump or move diagonally.
     * 
     * @return true of move ok, false if illegal
     */
    protected boolean canGoTo(int i, int j) 
    {
        ArrayList<Ant> ants;
        ants = new ArrayList<Ant>(); 
        ants = g.getAnts(); 
        
        for (int c = 0; c < ants.size(); c++)
        {
            if (ants.get(c).getX() == i && ants.get(c).getY() == j) return false;
        }
        boolean b = ((i >= 0) && (j >= 0) && (i < g.getSize()) && (j < g.getSize())
                && ((Math.abs(getX()-i) + Math.abs(getY()-j)) == 1) );
        return b;
    }
}

