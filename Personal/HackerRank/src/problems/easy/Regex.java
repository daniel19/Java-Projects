package problems.easy;

import java.util.Arrays;
/*Given a string, find the number of words in that string. For this problem a word
 * is defined by a string of one or more english alphabets. 
 *
 * There are multiple ways to tokenize a string in Java, learn how to tokenize a 
 * string in Java and demonstrate your skill by solving this problem!
 */
import java.util.List;

public class Regex{
    /*Inputt format
     * A string is not more than 40 thousand characters long. The string can be defined 
     * by the following regex: [A-Za-z !,?.\\_'@]+
     */
     private String inputString;
     //private String regex = "A-za-z !,?._'@";
     private String regex = "[^a-zA-Z]+";
     
     public Regex(String inputString){
        this.inputString = inputString;
     }   

    /**
     * This method will use java's builtin regex methods.
     */
     public String[] tokenizeWithRegex(String input, String regex){
        List<String> tokens = Arrays.asList(input.split(regex));
        for(int i =0; i < tokens.size(); i++){
            if(tokens.get(i).length() > 0)
               tokens.remove(i); 
        }
        return tokens.toArray(new String[tokens.size()]);
     }
     
     public int  findNumberOfTokens(String[] tokens){
         int count =0;
         for(int i=0; i<tokens.length; i++){
            if(tokens[i].length() > 0)
                count++;
        }
        return count;
     } 
     public String[] tokenizeWithRegex(){
       return tokenizeWithRegex(inputString, regex);
     }  

}

