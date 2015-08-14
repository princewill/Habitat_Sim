import java.util .ArrayList;
import java.util .Random;
/**
 
 * @author () 
 * @version ()
 */
public class City extends Habitat
{
    private int size;
    private static int default_size = 10;
    private Citizen poor;
    private Citizen rich;
    private boolean EveryoneHappy;
    private ArrayList<Citizen>citizens;
    /**
     * Default jungle with the size 5.
     */
    public City()
    {
        this(default_size);
    }
    
    /**
     * Create a jungle with the size specified by the parameter.
     */
    public City (int size)
    {
        this.size = size;
        EveryoneHappy = false;
        citizens = new ArrayList<Citizen>();
        addCitizens();
    }
        
    /**
     * Accessor to the size of the Jungle
     */
    public int getSize()
    {
        return size;
    }
    
    /**
     * Accessor to the citizens
     */
    public java.util.ArrayList<Citizen>  getCitizens()
    {
        return citizens;
    }
    
    /**
     * Add citizen method
     */
    public void addCitizens()
    {        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                Citizen citizen = new Citizen(i,j,this);
                citizens.add(citizen);         
            }
        }
    }

    /**
     * Get One unhappy poor citizen and One unhappy rich citizen
     */
    public void getUnhappyCitizens()
    {
        Citizen citizen;
        int checkRich = 0, checkPoor = 0, count = 0;        
        Random randomGenerator;
        randomGenerator = new Random();
        
        while ((checkRich < 1 || checkPoor < 1) && (count < ((citizens.size()) *2)) )
        {
            int index = randomGenerator.nextInt(citizens.size());
            citizen = citizens.get(index);
            
            if(!citizen.isHappy()) {
                if (citizen.isPoor()) {this.poor = citizen; citizen.MakeHappy(); checkPoor++;}
                else                  {this.rich = citizen; citizen.MakeHappy(); checkRich++;}
            }
            count++;
        }
        
    }
    
    /**
     * Test to see if there's a citizen at the supplied coordinate
     */
    public boolean hasPoorCitizenAt(int x,int y)
    {         
        for (int i = 0; i < citizens.size(); i++)
        {
            if(citizens.get(i).isPoor())
            {
                if( citizens.get(i).getX() == x && citizens.get(i).getY() == y) return true ;           
            }
        }
        return false;
    }
    
    /**
     * Test to see if there's a citizen at the supplied coordinate
     */
    public boolean hasRichCitizenAt(int x,int y)
    {         
        for (int i = 0; i < citizens.size(); i++)
        {
            if(!citizens.get(i).isPoor())
            {
                 if( citizens.get(i).getX() == x && citizens.get(i).getY() == y) return true ;  
            }
        }
        return false;
    }
    
    /**
     * Swap the unhappy rich citizen with the unhappy poor citizen.
     * 
     */
    public void moveInhabitants()
    {
       getUnhappyCitizens();
       if(!EveryoneHappy)
       {
            System.out.println("SWAPPED POOR AT:[" + poor.getX() + "," + poor.getY() + "] TO: [" + rich.getX() + "," + rich.getY() + "]");
            swap(poor,rich);
       }
    }  
    
    /**
     * Returns a string representation of the jungle (as per the illustration in the assignment)
     * - put a "o" at the location of a poor 
     * - put a "X" at the location of a rich
     * - (if a poor and a rich are at the same coordinate, only print the rich)
     * - otherwise, just print a dot "."
     */
    public java.lang.String toString()
    {   String a="";
        for (int i = 0; i < getSize(); i++)
        {
            a += "          ";            
            for (int k = 0; k < getSize(); k++)
            {
                if (hasPoorCitizenAt(k,i))   a += " p";
                else if(hasRichCitizenAt(k,i))  a += " r";
             }                
            a += " \n";       
            }
        return a;             
    }   
    
    /**
      * Takes the two Citizens and swaps them.
     */
     public void swap(Citizen A, Citizen B)
     {
         Citizen temp;
         temp = new  Citizen(false,this);
         temp.x = this.poor.getX(); 
         temp.y = this.poor.getY();
          
         poor.x = this.rich.getX();
         poor.y = this.rich.getY();
            
         this.rich.x = temp.x;
         this.rich.y = temp.y;
     }
}