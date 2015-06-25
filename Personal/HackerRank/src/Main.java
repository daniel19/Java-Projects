import structures.*;
import java.util.Random;
import utility.*;
import problems.easy.*;

public class Main{
    public static  BinaryTree<Integer> tree;
    private static Keyboard keyboard = Keyboard.getKeyboard();

    public static void main(String[] args){
        System.out.println("Welcome to the HackerRank Console\n");
        boolean running = true;
        while(running){
            running = display();
        }
    }

   private static boolean display(){
       clearScreen();

       int choice = keyboard.readInt("Please select a menu option: ");
       switch(choice){
           case 1:
               break;
           case 2:
               break;
           case 3:
               break;
           case 4:
               break;
           default:
               break;
       }       
      
       String willContinue = keyboard.readString("Do you want to continue?(y/n) ");
       if(willContinue.equalsIgnoreCase("y") || willContinue.equalsIgnoreCase("yes"))
           return true;
       
       return false;
   }

   private static void clearScreen(){
       for(int i =0; i < 25; i++){
           System.out.println();
       }
   }
   public static void createTree(){
        tree = new BinaryTree<Integer>();
        Random rand = new Random();
        for (int i = 0; i <51; i++){
            int value = rand.nextInt(50)+1;
            tree.insert(value, tree.getRoot());
        }        
        tree.inorderPrint(tree.getRoot());
   }

  public static void unitTests(){
      Operations.reverseString("New Jack City");
      System.out.println();
  } 
}
