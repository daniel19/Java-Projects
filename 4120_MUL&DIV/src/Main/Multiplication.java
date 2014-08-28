package Main;

/**
 * Main.Multiplication class to handle brunt of the algorithm work
 */
public class Multiplication {

    public int decimalValue;
    private String binaryValue;

    public Multiplication(int d){
        decimalValue = d;
        binaryValue = Integer.toBinaryString(d);
    }

    public String getBinaryValue(){
        return binaryValue;
    }

    /*
    * TODO:Create methods for each step in the algorithm; Create way to highlight changed values in strings compared
    *
    * */
}
