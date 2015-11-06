import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import problems.easy.Kelly;
import problems.easy.Warmups;
import problems.easy.algorithms.Sherlock;
import problems.easy.algorithms.EvenTree;
import problems.hard.Implementation;
import problems.hard.RedditDaily;

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
                break; case 2: problem(22); break; case 3: sherlock(); break; case 4: bigSum(); break; case 5: squareMatrix(); break;
            case 6:
                plusMinus();
                break; case 7: bigFact(); break;
            case 8:
                rotateMatrix(); break; case 9: dailys(); break;
            case 10:
                evenTree(); break; default: break;
        }

        String willContinue = keyboard.readString("Do you want to continue?(y/n) ");
        willContinue.
        if(willContinue.equalsIgnoreCase("y") || willContinue.equalsIgnoreCase("yes"))
            return true;

        return false;
    }

    private static void evenTree(){
        try{
            String filename = keyboard.readString("Enter in file for EvenTree: ");
            FileIO file = new FileIO(filename, FileIO.FOR_READING);
            int n, m;
            String[] firstLine = file.readLine().split(" ");
            n = Integer.parseInt(firstLine[0]);
            m = Integer.parseInt(firstLine[1]);
            List<String> listOfEdges = new ArrayList<>();
            while(!file.EOF()){
                String line = file.readLine();
                if(line != null && !line.isEmpty()){
                    listOfEdges.add(line);
                }
            }
            EvenTree tree = new EvenTree(n, m, listOfEdges);
            System.out.println(tree.decompose());
        }catch(FileIOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void dailys(){
        FileIO file = new FileIO("files/enable1.txt", FileIO.FOR_READING);
        int count = 0;
        while(!file.EOF()){
            String word = file.readLine();
            if(word == null)
                break;
            String[] words = {"snond", "rrizi", "eaing"};
            for(String w : words){
                RedditDaily.Fortune f = new RedditDaily.Fortune(word, w);
                if(f.analyze() && w.equals(words[0])){
                    count++;
                }
            }
        }
        System.out.println("Count: " + count);
    }

    private static void rotateMatrix(){
        String filename = keyboard.readString("Please enter in filename for rotateMatrix: ");
        try{
            FileIO file = new FileIO(filename, FileIO.FOR_READING);
            String[] args = file.readLine().split(" ");
            int rows = Integer.parseInt(args[0]), columns = Integer.parseInt(args[1]);
            int numberOfRotations = Integer.parseInt(args[2]);
            int[][] matrix = new int[rows][columns];
            for(int i=0; i< rows; i++){
                String[] elements = file.readLine().split(" ");
                for(int j=0; j < columns; j++){
                    matrix[i][j] = Integer.parseInt(elements[j]);
                }
            }
            Implementation.rotation(matrix, numberOfRotations, Implementation.ROTATE_LEFT);
        }catch(FileIOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void bigFact(){
        int input = keyboard.readInt("Please enter an integer: ");
        System.out.print("The result is: ");
        Warmups.bigFact(BigInteger.valueOf(input));
    }

    private static void plusMinus(){
        String filename = keyboard.readString("Please enter in filename for plusMinus: ");
        try{
            FileIO file = new FileIO(filename, FileIO.FOR_READING);
            int arrayLength = Integer.parseInt(file.readLine());
            int[] array = Arrays.asList(file.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
            Arrays.stream(array).forEach(System.out::println);
            Warmups.plusMinus(array);
        }catch(FileIOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void squareMatrix(){
        String filename = keyboard.readString("Please enter in filename for squareMatrix: ");
        try{
            FileIO file = new FileIO(filename, FileIO.FOR_READING);
            int arrayLength = Integer.parseInt(file.readLine());
            //long[] numbers = Arrays.asList(file.readLine().split(" ")).stream().mapToLong(Long::parseLong).toArray();
            int[][] matrix = new int[arrayLength][arrayLength];
            for(int i=0; i< arrayLength; i++){
                String[] elements = file.readLine().split(" ");
                for(int j=0; j < arrayLength; j++){
                    matrix[i][j] = Integer.parseInt(elements[j]);
                }
            }
            Warmups.diagonalDiff(matrix);
        }catch(FileIOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void bigSum(){
        String filename = keyboard.readString("Please enter in filename for big SUM: ");
        try{
            FileIO file = new FileIO(filename, FileIO.FOR_READING);
            int arrayLength = Integer.parseInt(file.readLine());
            long[] numbers = Arrays.asList(file.readLine().split(" ")).stream().mapToLong(Long::parseLong).toArray();
            System.out.println("There are " + arrayLength + " numbers to be added");

            Warmups.bigSum(numbers);

        }catch(FileIOException ex){
            System.out.println(ex.getMessage());
        }
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
        System.out.println("1) 100-The 3n+1 problem\n2)TEST FUNC\n3)Sherlock\n4)Big Sum\n10)Even Tree\n");
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
