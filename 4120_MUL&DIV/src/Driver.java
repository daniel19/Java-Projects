/**
 * Main Driver for handling user inputs and displaying outputs.
 */

import java.util.Scanner;

public class Driver {
   private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Welcome");

        //Display potential options for the user
        System.out.println("This program will show step by step multiplication/division of numbers.");
        System.out.println("It will use the algorithms from ECE 4120.");


        //User selection
        boolean runProgram = true;
        while(runProgram){

            System.out.print("Please enter your choice (1|2):");

            int userChoice = Integer.parseInt(userInput.nextLine());

            switch (userChoice){
                case 1:
                    System.out.println("You chose Multiplication");
                    System.out.print("Please enter in your decimal number:");
                    userChoice = userInput.nextInt();
                    break;
                case 2:
                    System.out.println("You chose Division");
                    System.out.print("Please enter in your decimal number:");
                    userChoice = userInput.nextInt();
                    break;
                default:
                    runProgram = false;
                    break;

            }

        }

    }
}
