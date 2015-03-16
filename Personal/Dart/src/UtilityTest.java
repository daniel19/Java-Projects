import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class UtilityTest {

    @Test
    public void testReturnMatch() throws Exception {
        String sentence = "Hello World";
        String match = "World";
        String replace = "Universe";
        assertEquals("Hello Universe", Utility.returnMatch(sentence, match, replace)); 
    }

    @Test
    public void testIsPrime() throws Exception {
        //read in a file of first 100,000 primes
        FileIO fileReader = new FileIO("primes-to-100k.txt", FileIO.FOR_READING);
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        while(!fileReader.EOF()){
           try{
        	   primeList.add(Integer.parseInt(fileReader.readLine())); 
           }catch(NumberFormatException ex){
        	   System.out.print(ex.getMessage());
           }
        }

        for(int number : primeList){
            assertEquals(true, Utility.isPrime(number));
        }
    }

    @Test
    public void testIsArmstrong() throws Exception {
        FileIO fileReader = new FileIO("arm.txt", FileIO.FOR_READING);
        ArrayList<Integer> armList = new ArrayList<Integer>();
        while(!fileReader.EOF()){
        	try{
        		armList.add(Integer.parseInt(fileReader.readLine()));
        	}catch(NumberFormatException ex){
          	  	System.out.print(ex.getMessage());
            }
        }
        for(int number : armList){
        	assertEquals(true, Utility.isArmstrong(number));
        }
    }
}
