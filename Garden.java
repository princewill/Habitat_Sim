import java.util .ArrayList;
/**
 * 
 * @author () 
 * @version ()
 */
public class Garden extends Habitat
{
    private int size;
    private static int default_size = 5;
    
    private ArrayList<Breadcrumb> crumbs;
    private ArrayList<Ant> ants;
    public ArrayList<Pheromone> Pheromones;
    public Breadcrumb crumb1;
    public Breadcrumb crumb2;
    public Breadcrumb crumb3;
    public Breadcrumb crumb4;
    public Ant ant1;
    public Ant ant2;
    public Ant ant3;
    
    /**
     * Default garden with the size 5.
     */
    public Garden()
    {
        this(default_size);     
    }
    
    /**
     * Create a garden with the size specified by the parameter.
     */
    public Garden (int size)
    {
        this.size = size;       
        addOccupants();
    }
    
    /**
     * Accessor to the size of the garden
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Add ant method
     */
    public void addAnt(Ant ant)
    {
         ants.add(ant);          
    }
    
    /**
     * Add crumb method
     */
    public void addBreadcrumb(Breadcrumb crumb)
    {
        crumbs.add(crumb);
    }
    
    /**
     * Add pheromone method
     */
    public void addPheromone(Pheromone pheromone)
    {
         Pheromones.add(pheromone);          
    }
    
    /**
     * Accessor to the ant
     */
    public ArrayList<Ant>  getAnts()
    {
        return ants;
    }
    
    /**
     * Accessor to the crumb
     */
    public ArrayList<Breadcrumb> getBreadcrumbs()
    {
        return crumbs;
    }
    
    /**
     * Accessor to the crumb
     */
    public ArrayList<Pheromone> getPheromones()
    {
        return Pheromones;
    }
    
    /**
     * Test to see if there's a ant at the supplied coordinate
     */
    public boolean hasAntAt(int x,int y)
    {    
        for (int i = 0; i < ants.size(); i++)
        {         
            if( ants.get(i).getX() == x && ants.get(i).getY() == y) return true;           
        }
        return false;
    }
    
    /**
     * Test to see if there's a crumb at the supplied coordinate.
     */
    public boolean hasBreadcrumbAt(int x,int y)
    {        
        Breadcrumb crumb;
        for (int i = 0; i < crumbs.size(); i++)
        {
            crumb = crumbs.get(i);
            if((crumbs.get(i).getX() == x) && (crumbs.get(i).getY() == y)) return true;            
        }
        return false; 
    }    
    
    /**
     * Test to see if there's a pheromone at the supplied coordinate.
     */
    public boolean hasPheromoneAt(int x,int y)
    {        
        Pheromone pheromone;
        for (int i = 0; i < Pheromones.size(); i++)
        {
            pheromone = Pheromones.get(i);
            if((pheromone.getX() == x) && (pheromone.getY() == y)) return true;
            
        }
        return false; 
    } 
    
    /**
     * Test to see if there's a pheromone at the supplied coordinate.
     * 
     * @returns the index of the pheromone if available.
     */
    public int hasPheromoneAtIndex(int x,int y)
    {
        int index = 0;
        for (int i = 0; i < Pheromones.size(); i++)
        {            
            if((Pheromones.get(i).getX() == x) && (Pheromones.get(i).getY() == y))index = i;
        }        
        return index;
    }        
    
    /**
     * Move the crumb, then the ant.
     */
    public void moveInhabitants()
    {
        
        if (crumbs.size() > 0 || Pheromones.size() > 0){
            for(int i = 0; i < ants.size(); i++)
            {
                ants.get(i).move();              
            }
        }
        
        for(int i = 0; i < crumbs.size(); i++)
        {           
            if(crumbs.get(i).isPresent()== false) {crumbs.remove(crumbs.get(i)); i--; }
        }
        
        if (Pheromones.size() > 0)
        {
            for (int i = 0; i < Pheromones.size(); i++)
            {
                Pheromones.get(i).reduceSmell();           
                if(Pheromones.get(i).isPresent()== false)     { Pheromones.remove(Pheromones.get(i));  i--;}
                else if(Pheromones.get(i).getStrength() == 0) { Pheromones.remove(Pheromones.get(i));  i--;}     
            }
        }
    } 
    
    /**
     * Returns a string representation of the garden (as per the illustration in the assignment)
     * - put a "o" at the location of a crumb 
     * - put a "X" at the location of a ant
     * - (if a crumb and a ant are at the same coordinate, only print the ant)
     * - otherwise, just print a dot "."
     */
    public java.lang.String toString()
    {
       String a="";
        for (int j = 0; j < getSize(); j++)
        {
            a += "         ";            
            for (int i = 0; i < getSize(); i++)
            {
                if (i == 0 && j == 0)           a += " H";
                else if (hasPheromoneAt(i,j))   a +=  " " +Pheromones.get(hasPheromoneAtIndex(i,j)).getStrength();
                else if (hasAntAt(i,j))         a += " A";
                else if (hasBreadcrumbAt(i,j))  a += " b";
                else                            a += " .";
             }                
            a += "\n";       
        }
        return a;             
    }
    
    /**
     * Adds the new occupants to the Garden.
     */
     private void addOccupants()
     {
        ants = new ArrayList<Ant>();
        crumbs = new ArrayList<Breadcrumb>();
        Pheromones = new ArrayList<Pheromone>();        
        ant1 = new Ant(0,2,this);
        ant2 = new Ant(1,2,this);
        ant3 = new Ant(1,1,this);
        crumb1 = new Breadcrumb(3,2,this);
        crumb2 = new Breadcrumb(4,2,this);
        crumb3 = new Breadcrumb(3,3,this);
        crumb4 = new Breadcrumb(4,3,this);        
        addAnt(ant1);
        addAnt(ant2);
        addAnt(ant3);
        addBreadcrumb(crumb1);
        addBreadcrumb(crumb2);
        addBreadcrumb(crumb3);
        addBreadcrumb(crumb4); 
    }
}
