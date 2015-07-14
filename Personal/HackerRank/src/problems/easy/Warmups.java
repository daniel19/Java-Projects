package problems.easy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Warmups{
    public static void bigSum(long[] numbers){
        long sum =0;
        for(long n : numbers){
            sum+=n;
        }
        
        Arrays.stream(numbers).forEach(System.out::println);
        System.out.println("Sum is: " + sum);
    }

    public static void diagonalDiff(int[][] matrix){
        int squareLength = matrix.length;
        int firstDiagonal=0, secondDiagonal=0;

        for(int i=0; i < squareLength; i++){
            firstDiagonal+=matrix[i][i];
            secondDiagonal+=matrix[i][(squareLength - 1)-i];
        }
        System.out.println(Math.abs(secondDiagonal-firstDiagonal));
    }

    public static void plusMinus(int[] numbers){
        // negative / positive / zeros 
        double negSum=0, zeroSum=0, posSum=0; 

        for(int n : numbers){
            if(n < 0)
                negSum++;
            else if(n == 0)
                zeroSum++;
            else
                posSum++;
        }
       DecimalFormat df = new DecimalFormat("0.000000");

       System.out.println(df.format(BigDecimal.valueOf(negSum/numbers.length)));
       System.out.println(df.format(BigDecimal.valueOf(posSum/numbers.length)));
       System.out.println(df.format(BigDecimal.valueOf(zeroSum/numbers.length)));
    }

    public static void bigFact(BigInteger integer){
        BigInteger product = BigInteger.valueOf(1);
        for(int i=0; integer.compareTo(BigInteger.valueOf(i))  > 0; i++){
            product = product.multiply(BigInteger.valueOf(i+1));
        }  
        System.out.println(product); 
    }
}
