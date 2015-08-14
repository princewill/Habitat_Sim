import java.util.Random;
import java.util .ArrayList;
/**
 * Write a description of class Citizen here.
 * 
 * @author 
 * @version 
 */
public class Citizen
{
    protected int x;
    protected int y;
    private City city;
    
    private boolean happy;
    private boolean poor;
    private Random randomGenerator;
    
    private static int numToCheck = 8; 
    private static double ReqPercentage = 37.5; 
    /**
     * Assign the Citizen, and set the coordinates as per the parameters
     */
    public Citizen(int x, int y,City c)
    {
        this.x = x;
        this.y = y;
        city = c;
        this.happy = true;
        
        randomGenerator = new Random();
        this.poor = randomGenerator.nextBoolean();
    }
    
     /**
     * Assign the garden, and give an initial default position (0, 0)
     */
    public Citizen(boolean poor, City c)
    {
        this(0,0,c);
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
     * Returns the state of the citizen
     * 
     * @return    whether or not the citizen is poor
     */
    public boolean isPoor()
    {
        return poor;
    }
    
    /**
     * Returns whether or not Citizen is happy
     */
    public boolean isHappy()
    {
        checkHappy();
        return happy;
    }
    
    /**
     * Mutator that sets the happy variable to false.
     */
    public void MakeUnHappy()
    {
        happy = false;
    }
    
    /**
     * Mutator that sets the happy variable to true.
     */
    public void MakeHappy()
    {
        happy = true;
    }
    
     /**
     * Finds Closest Citizens
     *
     * @return     the Closest Citizens
     */
    public java.util.ArrayList<Citizen> getCloseCitizens()
    {
        ArrayList<Citizen> citizens;
        citizens = new ArrayList<Citizen>();               
        ArrayList<Citizen> close_citizens;
        close_citizens = new ArrayList<Citizen>();        
        Citizen closest_citizen ;
        int Newcheck, Oldcheck, checkX, checkY; //old check == impossible number
        
        for (int p = 0; p < city.getCitizens().size(); p++)
        {
            citizens.add(city.getCitizens().get(p));
        }
        
        for (int check = 0; check <= numToCheck; check++)
        {
            closest_citizen = citizens.get(0);
            Oldcheck = 12;
            for(int i = 0; i < citizens.size() ; i++)
            {
                 checkX = Math.abs( x - citizens.get(i).getX() );
                 checkY = Math.abs( y - citizens.get(i).getY() );
                 Newcheck = checkX + checkY;
                 //(x - closest_citizen.getX() > x-citizens.get(i).getX()) - Was trying to find the one with the closest numerical values
                 if (Newcheck < Oldcheck){  closest_citizen = citizens.get(i); Oldcheck = Newcheck; }
            }
            
            if (check > 0) close_citizens.add(closest_citizen);//because it always sees itself as the closest in the first attempt
            citizens.remove(closest_citizen);
        }
        return close_citizens;
    }
    
    /**
     * If Citizen is not happy calls Make Unhappy
     */
    public void checkHappy()
    {
        ArrayList<Citizen> citizens;
        citizens = new ArrayList<Citizen>();  
        citizens = getCloseCitizens();
        
        double percentage;
        int check = 0;
        if (isPoor())
        {
            for (int i = 0; i < citizens.size(); i++)
            {
                if (citizens.get(i).isPoor()) check += 1; 
            }                     
        }else
        {
            for (int i = 0; i < citizens.size(); i++)
            {
                if (!citizens.get(i).isPoor()) check += 1; 
            } 
        }       
        percentage = ((check * 100)/numToCheck);
       
        if(percentage < ReqPercentage)  MakeUnHappy();
    }
}
