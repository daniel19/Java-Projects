public final class Utility{
    
    private Utility(){
    }

    /**
     *Replace a given string with desired substring at the last occurence of a specified
     *substring. 
     *@param sentence - source String that contains match.
     *@param match - desired substring to be replaced.
     *@param replace - desired substring to be inserted in place of match. 
     */
    public static String returnMatch(String sentence, String match, String replace){
        int index = sentence.lastIndexOf(match);
        if(index == -1)
            return sentence;
        return sentence.substring(0,index) + replace + sentence.substring(index+match.length());
    }

    /**
     * Determines if a number is a prime number. 
     * Function disregards even numbers greater than two.
     * @param number - variable to test 
     */
    public static boolean isPrime(int number){
        number = Math.abs(number);
        //check if number is an even number greater than 2
        if(number%2 == 0 && number > 2)
            return false;
        //we only need to do half of the work to check for divisibility.
        for(int i =3; i*i<= number; i+=2){
            if(number%i == 0)
                return false;
        }
        return true;
    }

    /**
     *Determines if a number qualifies to be an Armstrong number.
     *@param number - variable to test
     * */
    public static boolean isArmstrong(int number){
        Integer numberAsInteger = new Integer(number);
        String numberAsString = numberAsInteger.toString();
        
        int sum = 0;
        for(char c : numberAsString.toCharArray()){
            int localNumber = c -'0'; //converts char to int based on ASCII 0-9 
            sum += Math.pow(localNumber, numberAsString.length());
            System.out.println("Digit: " + localNumber + " Running Sum for " + localNumber + "**" + numberAsString.length() + " sum:" + sum);
        } 
        System.out.println("\n\n");
        return sum ==  number;
    }
}
