package utility;

public class Operations{
    
    public static String reverseString(String reversable){
        int startLength = reversable.length();
        char[] sequence = new char[startLength];
        while(!reversable.isEmpty()){
            int index = reversable.length(); 
            System.out.print(reversable.charAt(index-1));
            sequence[startLength-index] = reversable.charAt(index-1);
            index--;
            reversable = reversable.substring(0, index);
        }
        return new String(sequence); 
    }
}
