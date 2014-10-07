/**
 * Accord is a subclass of CarModel
 *
 */
public class Accord extends CarModel{
    public Accord(){
        super();
    }
    public double cost() {
        return super.cost() + 23000.99;
    }

    public String toString(){
       return super.toString() + "Accord \r\n";
    }
}
