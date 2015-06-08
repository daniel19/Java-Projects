package tests;

import org.junit.Test;
import utility.Operations;
import static org.junit.Assert.*;

public class OperationsTest{

    @Test
    public void testReveserString() throws Exception{
        String test = "When she leaves?";
        String reverse = "?sevael ehs nehW";
        assertEquals(reverse, Operations.reverseString(test));
        
        //Check other inputs
    }

    @Test
    public void testOtherOp() throws Exception{
    }
}
