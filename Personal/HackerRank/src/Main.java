import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import problems.easy.Kelly;
import problems.easy.algorithms.Sherlock;

import structures.BinaryTree;

import utility.FileIO;
import utility.FileIOException;
import utility.Keyboard;
import utility.Operations;

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
       printMenu();
       int choice = keyboard.readInt("Please select a menu option: ");
       switch(choice){
           case 1:
               one();
               break;
           case 2:
               problem(22);
               break;
           case 3:
               sherlock();
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
   
   private static void sherlock(){
        String filename = keyboard.readString("\nEnter filename that contains data for the Sherlock problem: ");
        try{
            FileIO file = new FileIO(filename, FileIO.FOR_READING);
            int tests = Integer.parseInt(file.readLine());
            List<int[]> arrays = new ArrayList<>();
            for(int i = 0; i < 2*tests; i++){
               if(i % 2 == 0){
                  System.out.println(i);
                  String[] elements = file.readLine().split(" ");
                  int[] test = Arrays.asList(elements).stream().mapToInt(Integer::parseInt).toArray(); 
                  arrays.add(test);
               }
            }

            for(int[] test : arrays){
                Sherlock s = new Sherlock(test);
                if(s.appeaseWatson()){
                    System.out.print("YES: ");
                     Arrays.stream(test).forEach(System.out::println);
                }else{
                    System.out.println("NO: ");
                    Arrays.stream(test).forEach(System.out::println);
                }
            }

        }catch(FileIOException e){
            System.out.println(e.getMessage());
        }
   }
   
   private static void problem(int n){
        System.out.println(n);
  //      intList.add(n);
        if(n == 1){
            return;
        }else if(n % 2 != 0){
            problem(3*n+1);
        }else{
            problem(n/2);
        }
    }

   private static void clearScreen(){
       for(int i =0; i < 25; i++){
           System.out.println();
       }
   }
   
   private static void printMenu(){
       System.out.println("1) 100-The 3n+1 problem\n2)TEST FUNC\n3)Sherlock");
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

 //MARK: - Switch Fucntions  
 public static void one(){
    String filename = keyboard.readString("Enter filename that contains data for the 3n+1 problem: ");
    try{
        FileIO file = new FileIO(filename, FileIO.FOR_READING);
        while(!file.EOF()){
            String line = file.readLine();
            if(line != null){
                String[] numbers = line.split(" ");
                Kelly uva = new Kelly(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                System.out.println(uva.getCycleLength());
            }
        }
    }catch(FileIOException e){
        System.out.println(e.getMessage());
    }
 }

}
