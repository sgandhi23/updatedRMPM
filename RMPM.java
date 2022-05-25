

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class RMPM
{
    private int roundType = 0;
    private int currentValue;
    private ArrayList<ArrayList<Card>> plays;
    ArrayList<Hand> hands;
    boolean clear = false;
    public int numPlayers = 3;
    int whoseTurn = 0;
    String loser = "";
    
    /**
     * Constructor for objects of class RMPM
     */
    public RMPM()//figure out jokers
    {
        System.out.println();
        Scanner kbd = new Scanner(System.in);
        CardDeck cards = new CardDeck();
        cards.shuffle();
        hands = new ArrayList<Hand>();
        plays = new ArrayList<ArrayList<Card>>();
        
        System.out.println("How many players?");
        numPlayers = kbd.nextInt();
        String dummy = kbd.nextLine();
        
        hands = cards.dealAll(numPlayers);
        for(int i = 0; i < numPlayers; i++)
        {
            hands.get(i).sortHand();
            System.out.println("Which name would you like for this hand");
            String name = kbd.nextLine();
            hands.get(i).setName(name);
            System.out.println(hands.get(i));
        }
        
        for(int i = 0; i < hands.size(); i++)
        {
            if(hands.get(i).contains(new Card(1, 1)))
            {
                hands.add(0, hands.remove(i));
            }
        }
        
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        /*
         hands.add(new ArrayList<Card>());
          hands.add(new ArrayList<Card>());
          hands.get(1).add(new Card(4, 1));
           hands.get(1).add(new Card(4, 1));
           hands.get(0).add(new Card(4, 1));
           hands.get(0).add(new Card(4, 1));
           */
        boolean done = false;
        int lastPlayer = -1;
        while(!done)
        {
            
            if(hands.get(whoseTurn % numPlayers).size()==0)
              {
                  System.out.println( hands.get(whoseTurn % numPlayers).getName() + "finished");
                  hands.remove(whoseTurn % numPlayers);
                  numPlayers--;
              }
            //reevaluates done
            System.out.println("Whose turn: " + hands.get(whoseTurn % numPlayers).getName());
            int numDone = 0;
            
            if(hands.size() == 1)
            {
                done = true;
                loser = hands.get(0).getName();
                System.out.println(loser);
            }
            
            System.out.print("Last 3 plays: ");
            for(int i = plays.size()-1; i >= plays.size()-3; i--)
            {
                if(i >= 0)
                {
                    System.out.print(plays.get(i));
                }
            }
            System.out.println();
            System.out.println("hand: " + hands.get(whoseTurn % numPlayers));

            int numP = 0;
            int cardV = 0;
          
          ArrayList<Card> newPlay = new ArrayList<Card>();
          boolean pass = false;
          do //getting valid play
          {
              
                if(lastPlayer == whoseTurn % numPlayers)
                {
                    while(plays.size() > 0)
                    {
                        plays.remove(0);
                    }
                    System.out.println("here2");
                    roundType = 0;
                }
                System.out.println("round type" + roundType);
              System.out.print("Please enter how many cards you'd like to play: ");
            numP = kbd.nextInt();
            if(numP == 0)
            {
                pass = true;
            }
            if(!pass)
            {
                
                 dummy = kbd.nextLine();
                System.out.print("Please enter what number you'd like to play: ");
                 cardV = kbd.nextInt();
                dummy = kbd.nextLine();
                int numHave = cardIndex(hands.get(whoseTurn % numPlayers).getHand(), rmpmValue(cardV)).size();
                ArrayList<Integer> jokerIndex = cardIndex(hands.get(whoseTurn % numPlayers).getHand(), rmpmValue(0));
                boolean validPlay = true;
                if(numP >  numHave && jokerIndex.size() >= 1)//jokers
                {
                    System.out.println("Would you like to play a joker, enter y or n");
                    dummy = kbd.nextLine();
                    if(dummy.equalsIgnoreCase("y") && cardV <= 13)
                    {
                        for(int i = 0; i <= numP-numHave-1; i++)
                        {
                            hands.get(whoseTurn % numPlayers).getHand().get(jokerIndex.get(i)).setNum(cardV);
                        }
                    }
                    else
                    {
                        validPlay= false;
                    }
                }
                numHave = cardIndex(hands.get(whoseTurn % numPlayers).getHand(), rmpmValue(cardV)).size();
                if(validPlay && numHave >= numP)
                  {
                    newPlay = play(hands.get(whoseTurn % numPlayers), rmpmValue(cardV), numP);
                    if(newPlay.size() > 0)
                    {
                        lastPlayer = whoseTurn % numPlayers;
                    }
                   System.out.println("played" + newPlay);
                   System.out.println("plays" + plays);
                   if(hands.get(whoseTurn % numPlayers).getHand().size()==0)
                   {
                       System.out.println("here1");
                       roundType = 0;
                       while(plays.size() > 0)
                        {
                            plays.remove(0);
                        }
                    }
                    }
            }
            }
            while(newPlay.size() == 0 && !pass);
            whoseTurn ++;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        
        kbd.close();
    }
    
    //cardValue is an rmpm value
    public ArrayList<Card> play(Hand h, int cardValue, int numCards)//gets rid of cards from hand, updates plays and returns cards gotten rid of
    {
        ArrayList<Card> ans = new ArrayList<Card>();
        ArrayList<Integer> indexes = cardIndex(h.getHand(), cardValue);
        if(indexes.size() != 0)
        {
            if(isValid(cardValue, numCards))
            {
                if(numCards<=indexes.size())//if hand has correct number of cards
                {
                    System.out.println("made it");
                    for(int i = 0; i < numCards; i++)
                    {
                        int j = indexes.remove(0);
                        ans.add(h.remove(j));
                        indexes = cardIndex(h.getHand(), cardValue);
                    }
                    if(roundType == 1 && !clear && plays.size() != 0)
                    {
                        if(plays.get(plays.size()-1).get(0).getRMPMValue() == cardValue && numPlayers == 2)
                        {
                            System.out.println("Skip you");
                            clear();
                        }
                        else if(plays.get(plays.size()-1).get(0).getRMPMValue() == cardValue)
                        {
                            System.out.println("Skip you");
                            whoseTurn++;
                        }
                    }
                    if(!clear)
                    {
                        plays.add(ans);
                    }
                    else
                    {
                        System.out.println("cleared");
                        roundType = 0;
                        while(plays.size() > 0)
                        {
                            plays.remove(0);
                        }
                        whoseTurn--;
                    }
                }
            }
        }
        clear = false;
        return ans;
    }
    
    //cardValue is rmpm value
    public boolean isValid(int cardValue, int numCards)//sees if the value and number of cards is a valid play
    {
        int oldPlay = -1;
        if(plays.size() >= 1 && plays.get(0).size() != 0)
        {
            oldPlay = plays.get(plays.size()-1).get(0).getRMPMValue();//old play is rmpm value
            System.out.println("oldplay" + oldPlay);
        }
        else
        {
            System.out.println("here3");
            roundType = 0;
        }
    
        if(numCards == 4 && cardValue >= oldPlay)//bomb
        {
            System.out.println("bomb");
            clear();
            return true;
        }
        else if((numCards == roundType || roundType == 0) && cardValue == 13)
        {
            //System.out.println("3s");
            clear();
            return true;
        }
        else if(roundType == 0 && cardValue != 13)//right after a clear
        {
            roundType = numCards;
            return true;
        }
        else if(roundType != 3)//finihs set
        {
            if(roundType == 1)
            {
                if(numCards == 2)
                {
                    if(plays.size() >= 2)
                    {
                        ArrayList<Integer> values = getPrevValues(2);
                        if(values.get(0) == cardValue && values.get(1) == cardValue)
                        {
                            clear();
                            //System.out.println("singles 2 clear");
                            return true;
                        }
                    }
                }
                else if(numCards == 1)
                {
                    if(plays.size() >= 3)
                    {
                        ArrayList<Integer> values = getPrevValues(3);
                        if(values.get(2) == cardValue && values.get(1) == cardValue && values.get(0) == cardValue)
                        {
                            clear();
                            //System.out.println("singles 1 clear");
                            return true;
                        }
                    }
                }
                else if(numCards == 3)
                {
                    if(plays.size() >= 1)
                    {
                        ArrayList<Integer> values = getPrevValues(1);
                        if(values.get(0) == cardValue)
                        {
                            clear();
                            //System.out.println("singles 3 clear");
                            return true;
                        }
                    }
                }
            }
            else if(roundType == 2)
            {
                if(numCards == 2 && oldPlay == cardValue)
                {
                    clear();
                    //System.out.println("doubles 2 clear");
                    return true;
                }
            }
        }
        if(numCards == roundType && cardValue >= oldPlay)//standard rule
        {
            //System.out.println("normal card playing");
            return true;
        }
        System.out.println("cardvalue" + cardValue);
        System.out.println(numCards == roundType);
        System.out.println(cardValue >= oldPlay);
        return false;
    }
    
    public void clear()
    {
        System.out.println("here4");
        roundType = 0;
        clear = true;
    }
    
    //rmpm values
    public ArrayList<Integer> getPrevValues(int n)//gets n previous values
    {
        if(n <= plays.size())
        {
            ArrayList<Integer> ans = new ArrayList<Integer>();
            for(int i = plays.size()-1; i >= plays.size()-n; i--)
            {
                ans.add(plays.get(i).get(0).getRMPMValue());
            }
            return ans;
        }
        return null;
    }
    
    //target is an rmpm value
    public ArrayList<Integer> cardIndex(ArrayList<Card> hand, int target)//finds and return all iindexes of a certain card value
    {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i < hand.size(); i++)
        {
            Card c = hand.get(i);
            if(c.getRMPMValue() == target)
            {
                ans.add(i);
            }
        }
        return ans;
    }
    
    public int rmpmValue(int num)
    {
        if(num > 3)
        {
            return num - 3;
        }
        else if(num == 0)
        {
            return 15;
        }
        else
        {
            return num + 10;
        }
    }
}
