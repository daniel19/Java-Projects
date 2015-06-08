import structures.*;
import java.util.Random;
import java.util.Scanner;
import utility.Operations;

public class Main{
    public static  BinaryTree<Integer> tree;
    public static void main(String[] args){
        System.out.println("Welcome to the HackerRank Console\n");
        Scanner scanner = new Scanner(System.in);
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
