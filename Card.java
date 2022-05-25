
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card implements Comparable<Card>
{
    // instance variables - replace the example below with your own
    private int num;
    private int suite;
    
    public static final int SPADE = 1;
    public static final int DIAMOND = 2;
    public static final int HEART = 3;
    public static final int CLUB = 4;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";

    public Card()
    {
        // initialise instance variables
        num = 0;
        suite = 0;
    }
    
    public Card(int n, int s)
    {
        // initialise instance variables
        num = n;
        suite = s;
    }

    public int getNum()
    {
        // put your code here
        return num;
    }
    
    public int getSuite()
    {
        // put your code here
        return suite;
    }
    
    public String toString()
    {
        String ans = "";
        if(num == 0)
        {
            ans += "joker";
        }
        else if(num == 1)
        {
            ans += "ace";
        }
        else if(num <= 10)
        {
            ans += num;
        }
        else if(num == 11)
        {
            ans += "jack";
        }
        else if(num == 12)
        {
            ans += "queen";
        }
        else if(num == 13)
        {
            ans += "king";
        }

        if(suite == 1)
        {
            ans += "\u2660";
        }
        else if(suite == 2)
        {
            //ans += ANSI_RED + "\u2666" + ANSI_RESET;
            ans += "\u2666";
        }
        else if(suite == 3)
        {
            //ans += ANSI_RED +"\u2665"+ ANSI_RESET;
            ans += "\u2665";
        }
        else if(suite == 4)
        {
            ans += "\u2663";
        }
        return ans;
    }
    
    public int getRMPMValue()
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
    
    public void setNum(int n)
    {
        num = n;
    }
    
    public int compareTo(Card other)
    {
        return this.getRMPMValue()-other.getRMPMValue();
    }
    
    public boolean equals(Card other)
    {
        return this.getNum() == other.getNum() && this.getSuite() == other.getSuite();
    }
    
    public boolean equalsIgnoreSuite(Card other)
    {
        return this.getNum() == other.getNum();
    }
}
