
/**
 * Write a description of class CardDeck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class CardDeck
{
    // instance variables - replace the example below with your own
    private ArrayList<Card> deck;

    public CardDeck(boolean jokers)
    {
        deck = new ArrayList<Card>();
        
        for(int suite = 1; suite <= 4; suite++)
        {
            for(int num = 1; num <= 13; num++)
            {
                deck.add(new Card(num, suite));
            }
        }
        if(jokers)
        {
            deck.add(new Card(0, 0));//joker
            deck.add(new Card(0, 0));//joker
            deck.add(new Card(0, 0));//joker
        }
    }
    
    public CardDeck()
    {
        deck = new ArrayList<Card>();
        
        for(int suite = 1; suite <= 4; suite++)
        {
            for(int num = 1; num <= 13; num++)
            {
                deck.add(new Card(num, suite));
            }
        }
            deck.add(new Card(0, 0));//joker
            deck.add(new Card(0, 0));//joker
            deck.add(new Card(0, 0));//joker

    }
    
    public void shuffle()
    {
        for(int i = 0; i < 200; i++)
        {
            int num1 = (int)(Math.random() * deck.size());
            int num2 = (int)(Math.random() * deck.size());
            
            Card temp = deck.get(num1);
            deck.set(num1, deck.get(num2));
            deck.set(num2, temp);
        }
    }

    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    
    public String toString()
    {
        return deck.toString();
    }
    
    public ArrayList<Hand> dealAll(int numPeople)
    {
        ArrayList<Hand> toReturn = new ArrayList<Hand>();
        for(int i = 0; i < numPeople; i++)
        {
            toReturn.add(new Hand());
        }
        int rotate = 0;
        while(deck.size() > 0)
        {
            toReturn.get(rotate % numPeople).add(deck.remove(0));
            rotate++;
        }
        return toReturn;
    }
    
    public ArrayList<Hand> deal(int numPeople, int numCards)
    {
        ArrayList<Hand> toReturn = new ArrayList<Hand>();
        for(int i = 0; i < numPeople; i++)
        {
            toReturn.add(new Hand());
        }
        for(int i = 0; i < numCards; i++)
        {
            for(int j = 0; j < numPeople; j++)
            {
                toReturn.get(j).add(deck.remove(0));
            }
        }
        return toReturn;
    }

}
