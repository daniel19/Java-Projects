/**
 * Driver for testing and executing the The PartyPlanner and Party classes.
 * @author Daniel Brown
 * @version 11/2/2014
 */
public class PartyPlannerDriver {
    private static Keyboard keyboard = Keyboard.getKeyboard();
    public static void main(String[] args){
        PartyPlanner partyPlanner;
        switch(args.length) {
            case 0:
                partyPlanner = new PartyPlanner();
                break;
            default:
                if(args.length >=2){
                    try {
                        partyPlanner = new PartyPlanner(args[0], Boolean.parseBoolean(args[1]));
                    }catch (PartyPlannerException e){
                        System.out.println("Cannot create a PartyPlanner object: " + e.getMessage());
                        partyPlanner = new PartyPlanner();
                    }
                }else{
                    System.out.println("java -cp .:gui.jar:. PartyPlannerDriver [filename] [boolean]");
                    return;
                }
                break;
        }
        while(doWork(partyPlanner));
    }

    public static boolean doWork(PartyPlanner partyPlanner){
        boolean continueWork = true;
        //Display and get Menu value.
        int menuResult = display();
        //performAction
        switch(menuResult){
            case 1:
                doStatus(partyPlanner);
                break;
            case 2:
                doChangeName(partyPlanner);
                break;
            case 3:
                doInvite(partyPlanner);
                break;
            case 4:
                doFindGuest(partyPlanner);
                break;
            case 5:
                doAccept(partyPlanner);
                break;
            case 6:
                doRegret(partyPlanner);
                break;
            case 7:
                doPriceAdj(partyPlanner);
                break;
            case 8:
                doPay(partyPlanner);
                break;
            case 9:
                doListPaid(partyPlanner);
                break;
            case 10:
                doListUnpaid(partyPlanner);
                break;
            case 11:
                doAvgCost(partyPlanner);
                break;
            case 12:
                doParyNumbers(partyPlanner);
                break;
            case 13:
                doAddParty(partyPlanner);
                break;
            case 14:
                doSort(partyPlanner);
                break;
            case 15:
                doWrite(partyPlanner);
                break;
            default:
                continueWork = false;
                System.out.println("Exiting....");
                break;

        }

        if(continueWork){
            return true;
        }else{
            System.out.println("Thanks for using Kosa Party Planning Services!\n");
            return false;
        }
    }

    public static void doAddParty(PartyPlanner partyPlanner) {
    }
    public static void doSort(PartyPlanner p){

    }

    public static void doWrite(PartyPlanner p){

    }
    public static int display(){
        System.out.println("Menu of Kosa Party Planning Services.");
        System.out.println("Enter your choice for the following options:");
        System.out.println("1. Status\n2. Change Planner Name\n3. Invite\n4. Find Guest\n5. Accept\n6. Regret\n7. Adjust Price\n8. Pay for party\n9. List Paid\n10. List Unpaid\n11. Avg Cost per Person\n12. Number of Parties\n13. Add a Party\n14. Sort\n 15. Write to File\n16. Quit");
        int result = keyboard.readInt("Enter in your choice: ");        
        while( result < 0 || result > 16){
            result = keyboard.readInt("Enter in a choice between 1-16: ");
        }
        return result;
    }

    public static void doStatus(PartyPlanner p){
       System.out.println(p.getState()); 
    }
    public static void doChangeName(PartyPlanner p){
        p.setName(keyboard.readString("Please enter new planner name: "));
    }

    public static void doInvite(PartyPlanner p){
        System.out.println("Processing Invitation....");
        String who = keyboard.readString("Who would you like to invite? ");
        int choice = keyboard.readInt("To all Parties? (1 for yes)" );
        if(choice == 1){
            System.out.println(p.inviteToAll(who));
            return;
        }
        String partyName = keyboard.readString("Enter party name: ");
        String partyDate = keyboard.readString("Enter party date: ");
        if(!p.invite(who, partyName, partyDate))
            System.out.println(who + " was not invited");
        else
            System.out.println(who + " was invited");
    }

    public static void doRegret(PartyPlanner p){
       System.out.println("Processing decline....");
       String who = keyboard.readString("Who is not attending? ");
       String partyName = keyboard.readString("What is the name of the party? ");
       String partyDate = keyboard.readString("What is the date of the party? ");
       if(p.takeRegret(who, partyName, partyDate))
           System.out.println("Regret successful");
       else
           System.out.println("RSVP failed.");      
    }
    
    public static void doFindGuest(PartyPlanner p){
       System.out.println("Processing who is invited...");
       String partyName = keyboard.readString("What is the name of the party? ");
       String partyDate = keyboard.readString("What is the date of the party? ");
       System.out.println("\n" + p.getWhosInvited(partyName, partyDate));  
    }
    
    public static void doAccept(PartyPlanner p){
    System.out.println("Processing accept....");
       String who = keyboard.readString("Who is attending? ");
       String partyName = keyboard.readString("What is the name of the party? ");
       String partyDate = keyboard.readString("What is the date of the party? ");
       if(p.takeAccept(who, partyName, partyDate))
           System.out.println("Accept successful");
       else
           System.out.println("RSVP failed.");      
    }

    public static void doPriceAdj(PartyPlanner p){
      System.out.println("Processing adjustment...");
      int percent = keyboard.readInt("Please enter the % to adjust the price: ");
      String partyName = keyboard.readString("What is the name of the party? ");
      String partyDate = keyboard.readString("What is the date of the party? ");
      if(p.updatePrice(partyName, partyDate, percent))
        System.out.println("The price was adjusted.");
      else 
        System.out.println("The price was not adjusted.");
    }

    public static void doPay(PartyPlanner p){
        System.out.println("Processing payment....");
        String partyName = keyboard.readString("What is the name of the party? ");
        String partyDate = keyboard.readString("What is the date of the party? "); 
        String choice = keyboard.readString("Pay in full? (Y for yes) ");
        if(choice == "Y"){
            System.out.println(p.pay(partyName, partyDate));
            return;
        }

        int months = keyboard.readInt("# of months: ");
        System.out.println(p.pay(partyName, partyDate, months));
    }

   public static void doListUnpaid(PartyPlanner p){
      System.out.println("Processing unpaid jobs....");
      System.out.println(p.getUnpaidParties()); 
   }
   public static void doListPaid(PartyPlanner p){
      System.out.println("Processing unpaid jobs....");
      System.out.println(p.getPaidParties()); 
   }

   public static void doAvgCost(PartyPlanner p){
       System.out.println("Processing Average Cost....");
       
   }
   public static void doParyNumbers(PartyPlanner p){
       System.out.println("Processing party numbers.....");
   }

}


