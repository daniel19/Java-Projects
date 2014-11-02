/**
 * Driver for testing and executing the The PartyPlanner and Party classes.
 * @author Daniel Brown
 * @version 11/2/2014
 */
public class PartyPlannerDriver {
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
                }
                break;
        }

        while(doWork());
    }

    public static boolean doWork(){
        boolean continueWork = true;
        //Display and get Menu value.
        int menuResult = display();
        //performAction
        switch(menuResult){

        }

        if(continueWork){
            return true;
        }else{
            System.out.println("Thanks for using Kosa Party Planning Services!\n");
            return false;
        }
    }

    public static int display(){
        System.out.println("Menu of Kosa Party Planning Services.");
        Keyboard keyboard = Keyboard.getKeyboard();


    }
}
