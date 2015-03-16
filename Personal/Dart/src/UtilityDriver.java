public class UtilityDriver{

    /** Static Keyboard class to help read user input.*/
    static final Keyboard keyboard = Keyboard.getKeyboard();
    
    public static void main(String args[]){
        while(display());
    }
    
    /** Display contains all user interactions 
     * */
    public static boolean display(){
        System.out.println("Please select a menu option: \n\n");
        System.out.println("1)Match/Replace String\n2)Determine a Prime number\n3)Determine Armstrong number\n");
        int userChoice = keyboard.readInt("Enter menu number: ");
        
        int number;
        boolean result;
        switch(userChoice){
            case 1:
                String sentence = keyboard.readString("Please enter in your String.");
                String match = keyboard.readString("\nPlease enter in your match string.");
                String replace = keyboard.readString("\nPlease enter in you replacement string.");
                System.out.println("\n\nHere is your returned string. \n " + Utility.returnMatch(sentence,match, replace));
                break;
            case 2:
                number = keyboard.readInt("Please enter in your number.");
                result = Utility.isPrime(number);
                if(result)
                    System.out.println(number + " is a Prime number.");
                else
                    System.out.println(number + " is not a Prime number.");
                break;
            case 3:
                number = keyboard.readInt("Please enter in your number.");
                result = Utility.isArmstrong(number);
                if(result)
                    System.out.println(number + " is an Armstrong number.");
                else
                    System.out.println(number + " is not an Armstrong number.");
                break;
            default:
                break;
        }

        
        String persist = keyboard.readString("\nWould you like to continue?(y/n): ");
        if(persist.equalsIgnoreCase("y"))
            return true;
        return false;
    }
}
