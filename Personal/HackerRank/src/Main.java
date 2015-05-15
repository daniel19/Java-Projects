import structures.*;
import java.util.Random;

public class Main{
    public static  BinaryTree<Integer> tree;
    public static void main(String[] args){
        System.out.println("Welcome to the HackerRank Console\n");
        createTree();
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
}
