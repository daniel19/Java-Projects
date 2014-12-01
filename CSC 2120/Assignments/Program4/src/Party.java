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
    
    public void setPaid(boolean paid)
    {
        isPaidFor = paid;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getHost()
    {
        return host;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public int getMaxGuests()
    {
        return maxGuests;
    }
    
    public String getWhosInvited()
    {
        return whosInvited.toString();
    }
    
    public String getWhosAttending()
    {
        return whosAttending.toString();
    }
    
    public int getNumAttending()
    {
        return whosAttending.size();
    }
    
    public String getWhosNotAttending()
    {
        return whosNotAttending.toString();
    }
    
    public int getNumNotAttending()
    {
        return whosNotAttending.size();
    }
    
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
    
    public int getNumUnknown()
    {
        return getWhosUnknown().size();
    }
    
    public int getNumInvited()
    {
        return whosInvited.size();
    }
    
    public String toString()
    {
        return "Party: " + name + "\nHost : " + host + "\nDate: " + date +
        "\nLocation: " + location + "\nMax Guests: " + maxGuests + "\nAttending: " + whosAttending + "\nNot Attending: " +
        whosNotAttending + "\nUnsure: " + getWhosUnknown() + "\nPrice " + 
        FMT.format(price) + (isPerParty ? "" : " per person") + "\nCost " +
        FMT.format(getCost()) + "\nHas Paid? " + isPaidFor;
    }
    
    public String pay()
    {
        return pay(1);
    }
    
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
    
    public boolean invite(String who)
    {
        boolean result = false;
        if (!isPaidFor && getNumAttending() + getNumUnknown() < maxGuests)
        {
            if (!whosInvited.contains(who))
            {
                whosInvited.add(who);
                result = true;
            }
        }
        return result;
    }
    
    public boolean takeAccept(String who)
    {
        boolean result = false;
        if (!isPaidFor)
        {
            if (whosInvited.contains(who))
            {
                if (!whosAttending.contains(who))
                {
                    int whoNotAttend = whosNotAttending.indexOf(who);
                    int totalPossible = getNumAttending() + getNumUnknown();
                    if (whoNotAttend != -1) // moving someone out of the not attending list
                    {
                        if (totalPossible < maxGuests)
                        {
                            whosNotAttending.remove(whoNotAttend);
                            result = true;
                            whosAttending.add(who);
                        }
                    } 
                    else
                    {
                        if (totalPossible <= maxGuests)
                        {
                            result = true;
                            whosAttending.add(who);
                        }
                    }                    
                }   
            }
        }
        return result;        
    }
    
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
                    }
                }                   
            }
        }
        return result;
    }
    
    public boolean isPaid()
    {
        return isPaidFor;
    }
    
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
    
    public String getState()
    {
        return toFileString();
    }
    
    public String getKey()
    {
        return name + date;
    }
}
