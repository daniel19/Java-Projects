/**
 * Accord is a subclass of CarModel
 *
 */
public class Accord extends CarModel{
    public Accord(){
        super();
    }
    public double cost() {
        return 23000.99;
    }

    public String toString(){
       return super.toString() + "Accord \r\n";
    }
}
