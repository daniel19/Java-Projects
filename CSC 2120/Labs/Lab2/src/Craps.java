//import CrapsDice.*;
/**
 * Main Driver to a craps game that calculates the percentage of how many times
 *  a 2, 3, or 12 are rolled.
 *@author Daniel Brown
 */
public class Craps{
    //Total number of times the die will be rolled.
    private static int ROLLS = 752;

    /**
     *Main method that will simulate the rolls of the die.
     */
    public static void main(String[] args){
        int roll = 0;
        int count = 0;
        CrapsDice dice = new CrapsDice();

        for (int i=0; i < ROLLS; i++){
            //Roll the die each loop
            roll = dice.roll();
            if(roll == 2 || roll == 3 || roll == 12){
                count++;
            }
        }

        System.out.println("Number of dice hits: " + count);
        //Calculate percentage of hits
        double percent = (double)count/(double)ROLLS;
        percent *= 100;
        System.out.println("Percentage of dice hits for 2, 3, and 12: " + percent);

    }
}
