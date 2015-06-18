import structures.*;
import java.util.Random;
import java.util.Scanner;
import utility.Operations;
import problems.easy.*;

public class Main{
    public static  BinaryTree<Integer> tree;
    public static void main(String[] args){
        System.out.println("Welcome to the HackerRank Console\n");
        /*Scanner scanner = new Scanner(System.in);
        while(true){
            createTree();
            System.out.println("Do you want to continue?");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("n")){
                break;
            }
        }
        scanner.close();
        unitTests();

        Regex testRegex = new Regex("                        a                      ");
        String[] tokens = testRegex.tokenizeWithRegex();
        System.out.println(testRegex.findNumberOfTokens(tokens));
        for(String token: tokens){
            System.out.println(token);
        }
        */
        FileIO reader = new FileIO("input01.txt", FileIO.FOR_READING); 
        while(!reader.EOF()){
            String input = reader.readLine();
            if(input != null){
                DataStructures.JavaStack stack = new DataStructures.JavaStack(input);
                System.out.println(stack.isBalanced());
            }
        }
        //DataStructures.JavaStack stack = new DataStructures.JavaStack("}}}}");
        //System.out.println(stack.isBalanced());
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
