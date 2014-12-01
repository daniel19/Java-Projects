/**
 * This program allows the user to interact with a PartyPlanner via various commands.
 * 
 * @author Martha Kosa
 * @version 10.01.2014
 */
import java.text.*;
public class PartyPlannerDriver
{
    public static void main(String[] args)
    {
        PartyPlanner pp = null;
        try
        {
            if (args.length >= 2)
            {
                pp = new PartyPlanner(args[0], Boolean.parseBoolean(args[1]));
            }
            else if (args.length == 1)
            {
                pp = new PartyPlanner(args[0], false);
            }
            else
            {
                pp = new PartyPlanner();
            }
        }
        catch (PartyPlannerException e)
        {
            System.out.println("Problem creating PartyPlanner - exiting program");
            return;
        }
        DecimalFormat fmt = new DecimalFormat("$#,##0.00");
        System.out.println("Welcome to PartyPlannerDriver beta version!!!");
        String choice = menu();
        while (!choice.equalsIgnoreCase("Q"))
        {           
            if (choice.equalsIgnoreCase("S"))
            {
                System.out.println(pp);
            }
            else if (choice.equalsIgnoreCase("N"))
            {
                pp.setPlannerName(Keyboard.readString("Please enter new planner name: "));
            }
            else if (choice.equalsIgnoreCase("I"))
            {
                doInvite(pp);
            }
            else if (choice.equalsIgnoreCase("?"))
            {
                doWhosInvited(pp);
            }
            else if (choice.equalsIgnoreCase("A"))
            {
                doAccept(pp);
            }
            else if (choice.equalsIgnoreCase("R"))
            {
                doRegret(pp);
            }
            else if (choice.equalsIgnoreCase("P"))
            {
                doAdjust(pp);
            }
            else if (choice.equalsIgnoreCase("$"))
            {
                doPay(pp);
            }
            else if (choice.equalsIgnoreCase("D"))
            {
                System.out.println(pp.getPaidParties());
            }
            else if (choice.equalsIgnoreCase("U"))
            {
                System.out.println(pp.getUnpaidParties());
            }
            else if (choice.equalsIgnoreCase("V"))
            {
                System.out.println("The average cost per attendee is " +
                                   fmt.format(pp.getAverageCostPerPerson()));
            }
            else if (choice.equalsIgnoreCase("#"))
            {
                doNumbers(pp);
            }
            else if (choice.equalsIgnoreCase("+"))
            {
                doAddParty(pp);
            }
            else if (choice.equalsIgnoreCase("*"))
            {
                doSortWork(pp);
            }
            else if (choice.equalsIgnoreCase("W"))
            {
                doWrite(pp);
            }
            else
            {
                System.out.println("Invalid choice; please try again!");
            }
            choice = menu();
        }
        System.out.println("Thanks for using PartyPlannerDriver beta version!!!");
    }
    
    private static String menu()
    {
        return Keyboard.readString
                  ("Enter your choice -  S for status, N for planner name change,\nI for invite, ? for who, A for accept,\n" +
                   "R for regret, P for price adjustment, $ for pay,\nD for paid, U for unpaid, V for avg. cost per person,\n" +
                   "# for party numbers, + for add party, * for sort, W for write to file, Q for quit: ");
    }
    
    private static void doAddParty(PartyPlanner pp)
    {
        String choice = Keyboard.readString("Please enter Y or y to add a party, anything else to return to the main menu: ");
        while (choice.equalsIgnoreCase("y"))
        {
            String name = Keyboard.readString("Please enter the name of the party that you want to add: ");
            String host = Keyboard.readString("Please enter the host of the party: ");
            String date = Keyboard.readString("Please enter the date: (yy/mm/dd)");
            String loc = Keyboard.readString("Please enter the location of the party: ");
            int max = Keyboard.readInt("Please enter the maximum number of guests: ");
            double price = Keyboard.readDouble("Please enter the price: ");
            int ans = Keyboard.readInt("Is the price for the whole party? (1 for yes, anything else for no)");
            boolean perParty = (ans == 1);
            try
            {
                boolean success = pp.addToParties(name, host, date, loc, max, perParty, price);
                if (success)
                {
                    System.out.println("Party added successfully");
                }
                else
                {
                    System.out.println("Party not added");
                }
            }
            catch (PartyPlannerException ppe)
            {
                System.out.println(ppe.getMessage());
                System.out.println("Party not added");
            }
            choice = Keyboard.readString("Please enter Y or y to add a party, anything else to return to the main menu: ");
        }
    }
    
    private static void doSortWork(PartyPlanner pp)
    {
        System.out.println("Entering doSortWork");
        int sortField;
        do
        {
            showSortFieldMenu();          
            sortField = Keyboard.readInt("Enter the sort field: ");
        } while (sortField < 1 || sortField > 5);
        int sortAlg;
        do
        {
            showSortAlgorithmMenu();
            sortAlg = Keyboard.readInt("Enter the algorithm number: ");
        } while (sortAlg < 1 || sortAlg > 3);
        String result = pp.sort(sortField, sortAlg);
        System.out.println("Sort results:\n" + result);
        System.out.println("Leaving doSortWork");
    }
    
    private static void showSortFieldMenu()
    {
        System.out.println("1. host (asc)\n" +
           "2. date (desc)\n" +
           "3. maximum number of guests (desc)\n" +
           "4. number attending (asc)\n" +
           "5. cost (asc)\n");
    }    

    private static void showSortAlgorithmMenu()
    {
        System.out.println("1. Selection Sort\n" +
           "2. Insertion Sort\n" +
           "3. Bubble Sort\n");
    }    
    
    private static void doInvite(PartyPlanner pp)
    {
        System.out.println("Processing invite...");
        String who = Keyboard.readString("Who would you like to invite? ");
        int all = Keyboard.readInt("To all parties? (1 for yes, anything else for no): ");
        if (all == 1)
        {
            System.out.println(pp.inviteToAll(who));
        }
        else
        {
            String what = Keyboard.readString("What is the name of the party? ");
            String when = Keyboard.readString("When is the party? ");
            if (pp.invite(who, what, when))
            {
                System.out.println(who + " invited to party " + what + " on " +
                                   when);
            }
            else
            {
                System.out.println(who + " not invited to party " + what + " on " +
                                   when);
            }
        }
    }

    private static void doAccept(PartyPlanner pp)
    {
        System.out.println("Processing accept...");
        String who = Keyboard.readString("Who is attending? ");
        String what = Keyboard.readString("What is the name of the party? ");
        String when = Keyboard.readString("When is the party? ");
        if (pp.takeAccept(who, what, when))
        {
            System.out.println("Accept successful for " + who + " for party " + what +
                               " on " + when);
        }
        else
        {
            System.out.println("Accept unsuccessful for " + who + " for party " + what +
                               " on " + when);
        }            
    }
    
    private static void doRegret(PartyPlanner pp)
    {
        System.out.println("Processing regret...");
        String who = Keyboard.readString("Who is not attending? ");
        String what = Keyboard.readString("What is the name of the party? ");
        String when = Keyboard.readString("When is the party? ");
        if (pp.takeRegret(who, what, when))
        {
            System.out.println("Regret successful for " + who + " for party " + what +
                               " on " + when);
        }
        else
        {
            System.out.println("Regret unsuccessful for " + who + " for party " + what +
                               " on " + when);
        }            
    }
    
    private static void doWhosInvited(PartyPlanner pp)
    {
        System.out.println("Processing who's invited...");
        String what = Keyboard.readString("What is the name of the party? ");
        String when = Keyboard.readString("When is the party? ");
        System.out.println(pp.getWhosInvited(what, when));
    }
    
    private static void doPay(PartyPlanner pp)
    {
        System.out.println("Payment processing...");
        String what = Keyboard.readString("What is the name of the party? ");
        String when = Keyboard.readString("When is the party? ");
        String all = Keyboard.readString("Pay in full? (Y for yes, otherwise no) ");
        if (all.equalsIgnoreCase("Y"))
        {
           System.out.println(pp.pay(what, when));
        }
        else
        {
            System.out.println(pp.pay(what, when,
                               Keyboard.readInt("# of months? ")));
        }
    }
    
    private static void doNumbers(PartyPlanner pp)
    {
        String choice = Keyboard.readString("Please enter M for max. guests, A for # attending, " +
                                            "N for # not attending, and U for # unknown: ");
        String what = Keyboard.readString("What is the name of the party? ");
        String when = Keyboard.readString("When is the party? ");
        if (choice.equalsIgnoreCase("M"))
        {
            System.out.println("The max # of guests for party " + what + " on " + when +
                               " is " + pp.getMaxGuests(what, when));
        }
        else if (choice.equalsIgnoreCase("A"))
        {
            System.out.println("The # of attendees for party " + what + " on " + when +
                               " is " + pp.getNumAttending(what, when));
        }
        else if (choice.equalsIgnoreCase("N"))
        {
            System.out.println("The # of regretters for party " + what + " on " + when +
                               " is " + pp.getNumNotAttending(what, when));
        }
        else if (choice.equalsIgnoreCase("U"))
        {
            System.out.println("The # of unknowns for party " + what + " on " + when +
                               " is " + pp.getNumUnknown(what, when));
        }
        else
        {
            System.out.println("Invalid choice; please try again.");
        }
    }
    
    private static void doAdjust(PartyPlanner pp)
    {
        System.out.println("Adjustment processing...");
        int pct = Keyboard.readInt("Please enter the % to adjust the price: ");
        String what = Keyboard.readString("What is the name of the party? ");
        String date = Keyboard.readString("When is the party? ");
        boolean result = pp.updatePrice(what, date, pct);
        if (result)
        {
            System.out.println("The price was adjusted.");
        }
        else
        {
            System.out.println("The price was NOT adjusted");
        }
    }
    
    private static void doWrite(PartyPlanner pp)
    {
        System.out.println("Write processing...");
        String fileName = Keyboard.readString("Please enter the output file name: ");
        String objectFileChoice = Keyboard.readString("Should the output file be an object file? (Y/N): ");
        boolean isObjectFile = false;
        if (objectFileChoice.equalsIgnoreCase("y"))
        {
            isObjectFile = true;
        }
        try
        {
            if (isObjectFile)
            {
                pp.writeToObjectFile(fileName);
            }
            else
            {
                pp.writeToFile(fileName);
            }
	    System.out.println("Write to " + fileName + " successful.");
        }
        catch (PartyPlannerException ppe)
        {
            System.out.println(ppe.getMessage());
        }
    }
}