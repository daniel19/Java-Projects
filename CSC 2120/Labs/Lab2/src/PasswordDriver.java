//import Keyboard.*;
//import Password.*;
/**
 *Driver Class for Fallout 3 Password Hack
 *@author Daniel Brown
 *@param none
 */
public class PasswordDriver{
   /**
    *Main Program that uses static methods to interact with user.
    */ 
    public static void main(String[] args){
       Password myPass = new Password();
       addWords(myPass);
       guessWords(myPass);
    }
    /**
     * addWords grabs user input and adds them to the created Password instance.
     */
    public static void addWords(Password p){
        String word = "";
        while(! word.equals("q")){
             word = Keyboard.readString("Enter a password: ");
            p.addWord(word);
        }
    }
    /**
     *guessWords compares the words in the list of words the user has entered.
     */
    public static void guessWords(Password p){
        // Four tries to determine correct password
        while(p.getNumberOfPasswordsLeft() > 1){
           // String userGuess = Keyboard.readString("You should guess: ");
            System.out.println("You should guess: " + p.getOriginalWord(p.bestGuess()));
            System.out.println("Index of word to guess (1-based from original list): " + p.bestGuess());

           int charMatches = Keyboard.readInt("Number of character matches: ");
           p.guess(p.bestGuess(), charMatches);

           System.out.println(p.toString());

        }
    }
}
