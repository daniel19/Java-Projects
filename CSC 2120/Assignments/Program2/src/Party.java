/**
 * This class represents a Party and its important features.
 * 
 * @author Martha Kosa
 * @version Oct. 2, 2014
 */
import java.text.*;
import java.io.*;
import java.util.*;

public class Party implements Serializable, Statable
{
    private String name;
    private String host;
    private String date;
    private String location;
    private int maxGuests;
    private int numInvited;
    private ArrayList<String> whosInvited;
    private ArrayList<String> whosAttending;
    private ArrayList<String> whosNotAttending;
    private boolean isPerParty;
    private boolean isPaidFor;
    private double price;
    public static final double DEFAULT_PRICE = 200;
    public static final double DEFAULT_PP_PRICE = 21.75;
    public static final int DEFAULT_GUESTS = 20;
    public static final double NO_SHOW_FEE = 5.75;
    public static final int MAX_MONTHS = 12;
    private static final DecimalFormat FMT = new DecimalFormat("$#,##0.00");
    private static int comparisonField = 1;
    /**
     *Constructor for Party Object
     */    
    public Party(String theName, String who, String theDate, String place, int maxToInvite,
                 boolean perParty, double thePrice) throws PartyException
    {
        if (theName == null || theName.equals("") || who == null || who.equals("") || theDate == null || place == null || place.equals(""))
        {
            throw new PartyException("Null/empty string passed in");
        }
        if (Utilities.invalidDate(theDate))
        {
            throw new PartyException("Invalid date passed in");
        }
        name = theName;
        host = who;
        date = theDate;
        location = place;
       
        isPerParty = perParty;
        isPaidFor = false;
        whosInvited = new ArrayList<String>();
        whosAttending = new ArrayList<String>();
        whosNotAttending = new ArrayList<String>();
        numInvited = 0;
        if (maxToInvite <= 0)
        {
            maxGuests = DEFAULT_GUESTS;
        }
        else
        {
            maxGuests = maxToInvite;
        }
        if (thePrice <= 0)
        {
            if (isPerParty)
            {
                price = DEFAULT_PRICE;
            }
            else
            {
                price = DEFAULT_PP_PRICE;
            }
        }
        else
        {
            price = thePrice;
        }
    }
    /**
    *Sets the paid for boolean attribute. 
    */

    public void setPaid(boolean paid)
    {
        isPaidFor = paid;
    }
    
    /**
     *Name setter
     */
    public String getName()
    {
        return name;
    }
    /**
     * host getter
     */
    public String getHost()
    {
        return host;
    }
    /**
     * Date getter
     */ 
    public String getDate()
    {
        return date;
    }
    /**
     * Location getter
     */ 
    public String getLocation()
    {
        return location;
    }
    /**
     *Guest getter
     */
    public int getMaxGuests()
    {
        return maxGuests;
    }
    /**
     *List of who is invited getter.
     */
    public String getWhosInvited()
    {
        return whosInvited.toString();
    }
    /**
     * List of who is attending getter.
     */
    public String getWhosAttending()
    {
        return whosAttending.toString();
    }
    /**
     * Number of attending getter.
     */
    public int getNumAttending()
    {
        return whosAttending.size();
    }
    /**
     *List of who is not attending getter
     */
    public String getWhosNotAttending()
    {
        return whosNotAttending.toString();
    }
    /**
     * Number of who is not attending.
     */ 
    public int getNumNotAttending()
    {
        return whosNotAttending.size();
    }
    /**List of unknown RSVPS**/ 
    public ArrayList<String> getWhosUnknown()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (String who: whosInvited)
        {
            if (!whosAttending.contains(who) && !whosNotAttending.contains(who))
            {
                result.add(who);
            }
        }
        return result;
    }
   /**Number of unknown RSVPs**/ 
    public int getNumUnknown()
    {
        return getWhosUnknown().size();
    }
    /** Number of who is invited getter. **/ 
    public int getNumInvited()
    {
        return whosInvited.size();
    }
    /** String representation of paty object. **/ 
    public String toString()
    {
        return "Party: " + name + "\nHost : " + host + "\nDate: " + date +
        "\nLocation: " + location + "\nMax Guests: " + maxGuests + "\nAttending: " + whosAttending + "\nNot Attending: " +
        whosNotAttending + "\nUnsure: " + getWhosUnknown() + "\nPrice " + 
        FMT.format(price) + (isPerParty ? "" : " per person") + "\nCost " +
        FMT.format(getCost()) + "\nHas Paid? " + isPaidFor;
    }
   /**Overloaded method to pay for one month.**/ 
    public String pay()
    {
        return pay(1);
    }
    /**Cost getter **/ 
    public double getCost()
    {
        double result;
        if (isPerParty)
        {
            result = price;
        }
        else
        {
            result = price * getNumAttending() + NO_SHOW_FEE * getNumUnknown();
        }
        return result;
    }
    /** Pay for the party installments based on the number of months. **/ 
    public String pay(int numMonths)
    {
        String result;
        if (numMonths <= 0 || numMonths > MAX_MONTHS)
        {
            numMonths = MAX_MONTHS;
        }
        if (isPaidFor)
        {
            result = "Party " + name + " on " + date + " has already been paid for.";
        }
        else
        {
            result = "The cost of party " + name + " is " +
                     FMT.format(getCost() / numMonths);
            if (numMonths > 1)
            {
                result += " per month for " + numMonths + " months.";
            }
            isPaidFor = true;
        }
        return result;
    }
    /** Invite someone to the party.**/ 
    public boolean invite(String who)
    {
        boolean result = false;
        if (!isPaidFor && numInvited < maxGuests)
        {
            if (!whosInvited.contains(who) && numInvited < maxGuests)
            {
                whosInvited.add(who);
                numInvited++; // added by MJK on 11/4/2014
                result = true;
            }
        }
        return result;
    }
    /**Accept RSVP **/ 
    public boolean takeAccept(String who)
    {
        boolean result = false;
        if (!isPaidFor)
        {
            if (whosInvited.contains(who))
            {
                result = true;
                if (!whosAttending.contains(who))
                {
                    result = true;
                    whosAttending.add(who);
                    int whoNotAttend = whosNotAttending.indexOf(who);
                    if (whoNotAttend != -1) // moving someone out of the not attending list
                    
                    {
                        whosNotAttending.remove(whoNotAttend);
                    } 
                }   
            }
        }
        return result;        
    }
   /**Accept denied RSVP*/ 
    public boolean takeRegret(String who)
    {
        boolean result = false;
        if (!isPaidFor)
        {
            if (whosInvited.contains(who))
            {
                if (!whosNotAttending.contains(who))
                {
                    result = true;
                    whosNotAttending.add(who);
                    int whoAttend = whosAttending.indexOf(who);
                    if (whoAttend != -1) // moving someone out of the attend list
                    {
                        whosAttending.remove(whoAttend);
                        numInvited--; // added by MJK on 11/4/2014
                    } 
                }                   
            }
        }
        return result;
    }
    /**Getter method if Party is paid for.**/ 
    public boolean isPaid()
    {
        return isPaidFor;
    }
    /** Change the price of the Party. **/ 
    public boolean updatePrice(int percentage)
    {
        boolean result = false;
        if (!isPaidFor)
        {
            if (Math.abs(percentage) <= 20)
            {
                price = price * (1 + percentage / 100.0);
                result = true;
            }
        }
        return result;
    }
    /** String representation to be written to file.**/ 
    public String toFileString()
    {
        String attending = whosAttending.toString();
        attending = attending.substring(1, attending.length() - 1);
        String notAttending = whosNotAttending.toString();
        notAttending = notAttending.substring(1, notAttending.length() - 1);
        String unknown = getWhosUnknown().toString();
        unknown = unknown.substring(1, unknown.length() - 1);
        int numAttending = getNumAttending();
        int numNotAttending = getNumNotAttending();
        int numUnknown = getNumUnknown();
        StringBuffer result = new StringBuffer("");
        result.append(name + "," + host + "," + date + "," + location + "," + maxGuests + "," +
                      price + "," + isPerParty + "," + isPaidFor + "," +
                      numAttending + ",");
        if (numAttending != 0)
        {
            result.append(attending + ",");
        }
        result.append(numNotAttending + ",");
        if (numNotAttending != 0)
        {
            result.append(notAttending + ",");
        }
        result.append(numUnknown + ",");
        if (numUnknown != 0)
        {
            result.append(unknown);
        }
        return result.toString();
    }
    /** Returns toFileString **/ 
    public String getState()
    {
        return toFileString();
    }
    /** Gets key for hashmap **/ 
    public String getKey()
    {
        return name + date;
    }
}
