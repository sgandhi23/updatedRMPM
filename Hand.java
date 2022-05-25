
/**
 * Write a description of class Hand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
public class Hand implements Comparable<Hand>
{
    // instance variables - replace the example below with your own
    private ArrayList<Card> hand;
    private int status=0;//rich 1, medium 0, poor -1
    private String name;

    /**
     * Constructor for objects of class Hand
     */
    public Hand(ArrayList<Card> h)
    {
        hand = h;
    }
    
    public Hand()
    {
        hand = new ArrayList<Card>();
    }

    public void setName(String s)
    {
        name = s;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setStatus(int i)
    {
        status = i;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public void sortHand()
    {
        Collections.sort(hand);
    }
    
    public int compareTo(Hand other)
    {
        return this.status-other.status;
    }
    
    public void add(Card c)
    {
        hand.add(c);
    }
    
    public String toString()
    {
        return hand.toString();
    }
    
    public Card remove(int index)
    {
        return hand.remove(index);
    }
    
    public int size()
    {
        return hand.size();
    }
    
    public boolean contains(Card other)
    {
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).equals(other))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean containsIgnoreSuite(Card other)
    {
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).equalsIgnoreSuite(other))
            {
                return true;
            }
        }
        return false;
    }
}
